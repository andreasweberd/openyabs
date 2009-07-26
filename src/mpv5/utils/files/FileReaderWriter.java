/*
 *  This file is part of MP.
 *  
 *      MP is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *  
 *      MP is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *  
 *      You should have received a copy of the GNU General Public License
 *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */
package mpv5.utils.files;

/**
 *
 * 
 */
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mpv5.logging.Log;
import mpv5.utils.arrays.ArrayUtilities;

public class FileReaderWriter {

    private BufferedReader datenstrom;
    private char[] charArray;
    private File filer;
    private FileWriter fw;
    private BufferedWriter bw;
    private String zeilenumbruch = "\r\n";

    /**
     * A reader/writer helper class for textfiles
     * @param file
     */
    public FileReaderWriter(File file) {
        this.filer = file;
    }

    /**
     * A reader/writer helper class for textfiles
     * @param file
     */
    public FileReaderWriter(String file) {
        filer = new File(file);
    }

    /**
     * Reads a specific line number
     * @param skipsign If a line starts with this, skip it. 
     *                 Skip none if null.
     * @return The specified line
     */
    public String read1Line(String skipsign) {

        BufferedReader in = null;
        String s = null;
        boolean reading = true;

        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(filer)));
            if (skipsign != null) {
                s = skipsign;
                while (reading) {
                    if (s.startsWith(skipsign)) {
                        s = in.readLine();
                    } else {
                        reading = false;
                    }
                }
            } else {
                s = in.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(FileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(FileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return s;
    }

    /**
     * Read a file into an array (line by line)
     * @return The array representing the file
     */
    public String[] readLines() {
        BufferedReader in = null;
        @SuppressWarnings("unchecked")
        ArrayList<String> arr = new ArrayList();
        try {
            in = new BufferedReader(new InputStreamReader(new FileInputStream(filer)));
            String s = in.readLine();

            while (s != null) {
                arr.add(s);
                s = in.readLine();
            }
        } catch (IOException ex) {
            Log.Debug(this, ex.getMessage());
            return null;
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(FileReaderWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return ArrayUtilities.listToStringArray(arr);
    }

    /**
     * Appends the String Array to a file (new line per array field) or creates it if needed
     * 
     * @param text
     * @return true is writing successful
     */
    public boolean write(String[] text) {

        for (int i = 0; i < text.length; i++) {
            String string = text[i];
            if (string!=null) {
                if (!write(string)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Appends the String to a file (new line) or creates it if needed
     * 
     * @param text
     * @return true is writing successful
     */
    public boolean write(String text) {
        FileWriter out;
        try {
            text = zeilenumbruch + text;
            out = new FileWriter(filer, true);
            BufferedWriter writer = new BufferedWriter(out);
            writer.write(text);
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Appends the String Array to a file (new line per array field) or creates it if needed.
     * <li/>Starts from the beginning of the file.
     * <li/>Skips empty lines.
     * @param lines
     * @return
     */
    public boolean write0(String[] lines) {
       for (int i = 0; i < lines.length; i++) {
            String string = lines[i];
            if (string!=null && string.length()>0) {
                if (!write0(string)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Appends the String to a file or creates it if needed. Writes from the beginning of the file.
     * @param text
     * @return
     */
    public boolean write0(String text) {
       FileWriter out;
        try {
            String[] txt = this.readLines();
        } catch (Exception e) {
            try {
                filer.createNewFile();
            } catch (IOException ex) {
                Log.Debug(e);
            }
        }
        try {
            out = new FileWriter(filer, true);
            BufferedWriter writer = new BufferedWriter(out);
            writer.write(text);
            writer.newLine();
            writer.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Creates a file and writes the String into it - \
     * deletes existing files!
     * 
     * @param text
     * @return true is writing successful
     */
    public boolean writeOnce(String text) {
        try {

            if (filer.exists()) {
                filer.delete();
            }

            fw = new FileWriter(filer);
            bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Reads a whole text file into a String
     * @return a string with text of file
     */
    public String read() {
        try {
            int charArrayLaenge;
            //Wie heist die Datei?
            File dateiHandle = filer;
            //Wie lange ist diese Datei?
            long datenStromLaenge = dateiHandle.length() + 1;
            charArrayLaenge = (int) datenStromLaenge - 1;
            charArray = new char[charArrayLaenge];
            try {
                FileReader dateiInhalt = new FileReader(dateiHandle);
                datenstrom = new BufferedReader(dateiInhalt);
                datenstrom.read(charArray, 0, charArrayLaenge);
            } catch (IOException e) {
                System.out.println(e.toString());
            }
            datenstrom.close();
        } catch (Exception ex) {
            Log.Debug(ex);
            return "\n" + filer.getPath() + " not found :-( \n";

        }
        return new String(charArray);
    }

    /**
     * Try to delete the file (may fail, though)
     */
    public void deleteFile() {
        filer.delete();
    }

    /**
     * Write one line representing the given array
     * @param text
     * @param sep The separator between the array items
     * @return
     */
    public boolean writeLine(String[] text, String sep) {
        String string = "";
        for (int i = 0; i < text.length; i++) {
            string += text[i] + sep;
        }
        if (!writeOnce(string.substring(0, string.length() - sep.length()))) {
            return false;
        }
        return true;
    }

    /**
     * Flush the file (make it an empty file)
     */
    public void flush() {
        writeOnce("");
    }
}
