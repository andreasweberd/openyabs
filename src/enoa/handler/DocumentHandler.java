/*
 *  This file is part of YaBS.
 *
 *      YaBS is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *
 *      YaBS is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *
 *      You should have received a copy of the GNU General Public License
 *      along with YaBS.  If not, see <http://www.gnu.org/licenses/>.
 */
package enoa.handler;

import ag.ion.bion.officelayer.document.DocumentDescriptor;
import ag.ion.bion.officelayer.document.DocumentException;
import ag.ion.bion.officelayer.document.IDocument;
import ag.ion.bion.officelayer.filter.IFilter;
import ag.ion.bion.officelayer.filter.MSOffice97Filter;
import ag.ion.bion.officelayer.filter.PDFFilter;
import ag.ion.bion.officelayer.filter.TextFilter;
import ag.ion.bion.officelayer.form.IFormComponent;
import ag.ion.bion.officelayer.text.ITextDocument;
import ag.ion.bion.officelayer.text.ITextField;
import ag.ion.bion.officelayer.text.ITextFieldService;
import ag.ion.bion.officelayer.text.IVariableTextFieldMaster;
import ag.ion.bion.officelayer.text.TextException;
import ag.ion.noa.NOAException;
import com.sun.star.awt.XTextComponent;
import com.sun.star.beans.XPropertySet;
import com.sun.star.form.XFormComponent;
import com.sun.star.uno.UnoRuntime;
import enoa.connection.NoaConnection;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpv5.db.objects.Template;
import mpv5.logging.Log;
import mpv5.utils.files.FileDirectoryHandler;

/**
 * This class OpenOffice Documents IO
 */
public class DocumentHandler {

    /**
     * All known OO file extensions
     */
    public static final String EXTENSION = ".*ott$|.*sxw$|.*doc$|.*xls$|.*odt$|.*ods$|.*pps$|.*odt$|.*ppt$|.*odp$";
    /**
     * A FileFilter looking for OO files
     */
    public static FileFilter OFFICE_FILE_FILTER = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            return pathname.getName().matches(EXTENSION);
        }
    };
    private final NoaConnection connection;
    private final DocumentDescriptor descriptor;
    private IDocument document;
    private ITextFieldService textFieldService;
    private ITextField[] placeholders;
    private TableHandler tablehandler;
    private File file;

    /**
     * Creates a new (hidden) Document Handler on top of the given connection
     * @param connection The OO connection to use
     */
    public DocumentHandler(NoaConnection connection) {
        if (connection != null) {
            this.connection = connection;
            descriptor = DocumentDescriptor.DEFAULT_HIDDEN;
        } else {
            throw new NullPointerException("Connection cannot be null");
        }
    }

    /**
     * Load an existing document into the Document Handler
     * @param file The file to load
     * @param asTemplate If true, the file is treatened as template (.ott)
     * @throws Exception Any error thrown
     */
    public void loadDocument(File file, boolean asTemplate) throws Exception {
        if (!OFFICE_FILE_FILTER.accept(file)) {
            throw new UnsupportedOperationException("The file extension must match: " + EXTENSION);
        }
        if (asTemplate) {
            descriptor.setAsTemplate(asTemplate);
        }
        this.file = file;
        descriptor.setHidden(true);
        clear();
    }

    /**
     * Creates a new, empty text document (.odt)
     * @throws Exception
     */
    public void newTextDocument() throws Exception {
        IDocument d = connection.getDocumentService().constructNewDocument(IDocument.WRITER, descriptor);
        document = (ITextDocument) d;
    }

    /**
     * Save the given document to the physical location of the given file.
     * @param file
     * @throws DocumentException
     */
    public synchronized void saveAs(File file) throws DocumentException {

        document.reformat();
        document.update();

        if (file.getName().split("\\.").length < 2) {
            throw new UnsupportedOperationException("The file must have an extension: " + file);
        }

        IFilter filter = null;
        String extension = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
        if (extension.equalsIgnoreCase(".pdf")) {
            filter = PDFFilter.FILTER;
        } else if (extension.equalsIgnoreCase(".doc")) {
            filter = MSOffice97Filter.FILTER;
        } else if (extension.equalsIgnoreCase(".txt")) {
            filter = TextFilter.FILTER;
        }

        if (filter != null) {
            document.getPersistenceService().export(file.getPath(), filter);
        } else {
            throw new UnsupportedOperationException("File extension not supported: " + extension);
        }
    }

    /**
     * Close the underlying doc
     */
    public void close() {
        document.close();
    }

    /**
     * Fill the Form Fields of the template with values
     * @param data
     * @throws Exception
     * @throws NOAException
     */
    public synchronized void fillFormFields(HashMap<String, Object> data) throws Exception, NOAException {
        Log.Debug(this, "Looking for form fields in: " + document);
        IFormComponent[] formComponents = document.getFormService().getFormComponents();
        Iterator<String> keys = data.keySet().iterator();
        String key = null;
        while (keys.hasNext()) {

            // get column name
            key = keys.next();
//            Log.Debug(this, "Found key: " + key + " [" + data.get(key) + "]");
            for (int i = 0; i < formComponents.length; i++) {

                XFormComponent xFormComponent = formComponents[i].getXFormComponent();
                XTextComponent xTextComponent = formComponents[i].getXTextComponent();
                XPropertySet propertySet = (XPropertySet) UnoRuntime.queryInterface(XPropertySet.class,
                        xFormComponent);

                if (propertySet != null && propertySet.getPropertySetInfo().hasPropertyByName("Name")) {
                    String n = propertySet.getPropertyValue("Name").toString();
//                    Log.Debug(this, "Found form field: " + n);
                    if (n.equalsIgnoreCase(key) || key.endsWith(n)) {
                        Log.Debug(this, "Form field matches key: " + key + " [" + data.get(key) + "]");
                        xTextComponent.setText(String.valueOf(data.get(key)));
                    }
                }
            }
//            textDocument.getTextFieldService().refresh();
        }
    }

    /**
     * Fill the Placeholder Fields of the template with values
     * @param data
     * @throws Exception
     * @throws NOAException
     */
    public synchronized void fillPlaceholderFields(HashMap<String, Object> data) throws Exception, NOAException {
        Log.Debug(this, "Looking for placeholder fields in: " + document);
        Iterator<String> keys = data.keySet().iterator();
        String key = null;
        String[] placehrepr = new String[0];
        if (textFieldService == null || placeholders == null) {
            textFieldService = ((ITextDocument) document).getTextFieldService();
            placeholders = textFieldService.getPlaceholderFields();
            placehrepr = new String[placeholders.length];
            for (int i = 0; i < placeholders.length; i++) {
                placehrepr[i] = placeholders[i].getDisplayText();
            }
        }

        while (keys.hasNext()) {
            key = keys.next();
            try {
                for (int i = 0; i < placehrepr.length; i++) {
                    String placeholderDisplayText = placehrepr[i];
//                    Log.Debug(this, "Check placeholder key: " + placeholderDisplayText);
                    if (placeholderDisplayText.equalsIgnoreCase(key) || placeholderDisplayText.equalsIgnoreCase("<" + key + ">")) {
                        Log.Debug(this, "Found placeholder key: " + key + " [" + data.get(key) + "]");
                        placeholders[i].getTextRange().setText(String.valueOf(data.get(key)));
                    }
                }
            } catch (java.lang.Exception ex) {
                Log.Debug(ex);
            }
        }
    }

    /**
     * Fill the Variable Text Fields of the template with values
     * @param data
     * @throws Exception
     * @throws NOAException
     */
    public synchronized void fillTextVariableFields(HashMap<String, Object> data) throws Exception, NOAException {
        Log.Debug(this, "Looking for variable fields in: " + document);
        Iterator<String> keys = data.keySet().iterator();
        String key = null;
        IVariableTextFieldMaster x;

        while (keys.hasNext()) {
            // get column name
            key = keys.next();
            if (textFieldService == null) {
                textFieldService = ((ITextDocument) document).getTextFieldService();
            }
            x = textFieldService.getVariableTextFieldMaster(key);

            if (x != null) {
                ITextField[] variables = x.getVariableTextFields();
                for (int i = 0; i < variables.length; i++) {
                    XPropertySet xPropertySetField = (XPropertySet) UnoRuntime.queryInterface(XPropertySet.class, variables[i].getXTextContent());
                    if (xPropertySetField.getPropertyValue("CurrentPresentation").toString().equalsIgnoreCase(key)) {
                        Log.Debug(this, "Found variable key: " + key + " [" + data.get(key) + "]");
                        xPropertySetField.setPropertyValue("Content", data.get(key));
                    }
                }
            }
        }
    }

    /**
     * Export a file to another format/file. Supported target formats:
     * <li>pdf
     * <li>odt
     * <li>txt
     * @param source The file to export
     * @param target The target file
     * @return The target file
     * @throws DocumentException
     */
    public File export(File source, File target) throws DocumentException {
        if (target.getName().split("\\.").length < 2) {
            throw new UnsupportedOperationException("The file must have an extension: " + target);
        }

        IFilter filter = null;
        String extension = target.getName().substring(target.getName().lastIndexOf("."), target.getName().length());
        if (extension.equalsIgnoreCase(".pdf")) {
            filter = PDFFilter.FILTER;
        } else if (extension.equalsIgnoreCase(".doc")) {
            filter = MSOffice97Filter.FILTER;
        } else if (extension.equalsIgnoreCase(".txt")) {
            filter = TextFilter.FILTER;
        }

        if (filter != null) {
            NoaConnection.getConnection().getDocumentService().loadDocument(source.getAbsolutePath()).getPersistenceService().export(target.getPath(), filter);
        } else {
            throw new UnsupportedOperationException("File extension not supported: " + extension);
        }

        return target;
    }

    /**
     * Print the document directly
     */
    public void print() {
        try {
            document.getPrintService().print();
        } catch (DocumentException ex) {
            Log.Debug(ex);
        }
    }

    /**
     * Fill the tables in the document
     * @param data
     * @throws TextException
     */
    public synchronized void fillTables(HashMap<String, Object> data) {
        fillTables(data, null);
    }

    public void fillTables(HashMap<String, Object> data, Template template) {

        Log.Debug(this, "Looking for tables in: " + document);
        try {
            for (Iterator<String> it = data.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                if (key.contains(TableHandler.KEY_TABLE)) {//Table found
                    Log.Debug(this, "Table: " + key);
                    @SuppressWarnings("unchecked")
                    List<String[]> value = (List<String[]>) data.get(key);
                    if (tablehandler == null) {
                        if (key.contains(".")) {
                            key = key.substring(key.lastIndexOf(".") + 1);
                        }
                        Log.Debug(this, "Table identifier: " + key);
                        tablehandler = new TableHandler((ITextDocument) document, key);
                    }
                    for (int i = 0; i < value.size(); i++) {
                        String[] strings = value.get(i);
                        if (template != null) {
                            strings = refactorRow(template, strings);
                        }
                        for (int j = 0; j < strings.length; j++) {
                            String cellValue = strings[j];
                            if (!cellValue.contains("://")) {
                                tablehandler.setValueAt(cellValue, j, i);
                            } else {
                                tablehandler.setHyperlinkAt("Link", cellValue, j, i);
                            }
                        }
                    }
                }
            }
        } catch (Exception textException) {
            Log.Debug(this, textException);
        }


    }
    public static final String linkstartidentifier = "[url]";
    public static final String linkendidentifier = "[/url]";

    /**
     * Reset the doc
     * @throws DocumentException
     */
    public void clear() throws DocumentException {
        try {
            document = connection.getDocumentService().loadDocument(file.getPath(), descriptor);
        } catch (NOAException ex) {
            Log.Debug(ex);
        }
        textFieldService = null;
        placeholders = null;
        tablehandler = null;
    }

    /**
     * Set the images
     * @param data
     */
    public void setImages(HashMap<String, Object> data) {
    }

    private String[] refactorRow(Template template, String[] possibleCols) {
        String format = template.__getFormat();
        int[] intcols;
        try {
            String[] cols = format.split(",");
            intcols = new int[cols.length];
            for (int i = 0; i < intcols.length; i++) {
                String string = cols[i];
                intcols[i] = Integer.valueOf(string).intValue();
            }
        } catch (Exception ex) {
            Log.Debug(this, "An error occured, using default format now. " + ex.getMessage());
            intcols = new int[possibleCols.length];
            for (int i = 0; i < intcols.length; i++) {
                intcols[i] = i + 1;
            }
        }
        String[] form = new String[intcols.length];
        for (int i = 0; i < intcols.length; i++) {
            try {
                form[i] = possibleCols[intcols[i] - 1];
            } catch (Exception e) {
                Log.Debug(this, "To much columns in the format definition: " + e);
            }
        }

        return form;
    }
}
