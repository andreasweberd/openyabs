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
package mpv5.db.objects;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import mpv5.db.common.Context;
import mpv5.db.common.DatabaseObject;
import mpv5.db.common.NodataFoundException;
import mpv5.db.common.QueryHandler;
import mpv5.globals.Messages;
import mpv5.logging.Log;
import mpv5.ui.dialogs.subcomponents.ControlPanel_Groups;
import mpv5.ui.dialogs.subcomponents.ControlPanel_ProductGroups;
import mpv5.ui.frames.MPView;
import mpv5.ui.panels.MPControlPanel;

/**
 *
 *
 */
public class ProductGroup extends DatabaseObject {

    private String description = "";
    private String defaults = "";
    private String hierarchypath = "";
    private int productgroupsids;

    public ProductGroup() {
        setContext(Context.getProductGroup());
    }

    public ProductGroup(String name) {
        this();
        setCname(name);
    }

    /**
     * @return the description
     */
    public String __getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the defaultvalue
     */
    public String __getDefaults() {
        return defaults;
    }

    /**
     * @param defaultvalue the defaultvalue to set
     */
    public void setDefaults(String defaultvalue) {
        this.defaults = defaultvalue;
    }

    @Override
    public String toString() {
        return __getCname();
    }

    @Override
    public boolean delete() {
        try {
            List<ProductGroup> childs = DatabaseObject.getReferencedObjects(this, Context.getProductGroup());
            for (int i = 0; i < childs.size(); i++) {
                DatabaseObject databaseObject = childs.get(i);
                if (!databaseObject.delete()) {
                    return false;
                }
            }
        } catch (NodataFoundException ex) {
            mpv5.logging.Log.Debug(ex);//Logger.getLogger(ProductGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            return super.delete();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public JComponent getView() {
        return new ControlPanel_ProductGroups(this);
    }

    @Override
    public mpv5.utils.images.MPIcon getIcon() {
        return null;
    }

    /**
     * @return the hierarchypath
     */
    public String __getHierarchypath() {
        Set<Integer> used = new HashSet<Integer>();
        hierarchypath = "";
        int intp = __getIDS();
        boolean notUsed = used.add(intp);
        while (intp > 0 && notUsed) {
            try {
                ProductGroup p = (ProductGroup) getObject(Context.getProductGroup(), intp);
                hierarchypath = p + Group.GROUPSEPARATOR + hierarchypath;
                intp = p.__getProductgroupsids();
            } catch (NodataFoundException ex) {
                break;
            }
        };
        return hierarchypath;
    }

    /**
     * @param hierarchypath the hierarchypath to set
     */
    public void setHierarchypath(String hierarchypath) {
        this.hierarchypath = hierarchypath;
    }

    /**
     * @return the productgroupsids
     */
    public int __getProductgroupsids() {
        return productgroupsids;
    }

    /**
     * Returns 'All Group' or the first group found if 'All group' is gone, and
     * if all are deleted, creates a default group
     *
     * @return A group, never NULL
     */
    public static ProductGroup getDefault() {
        try {
            return (ProductGroup) DatabaseObject.getObject(Context.getProductGroup(), 1);
        } catch (NodataFoundException ex) {
            Log.Debug(ex);
            try {
                return (ProductGroup) DatabaseObject.getObjects(Context.getProductGroup()).get(0);
            } catch (NodataFoundException nodataFoundException) {
                ProductGroup g = new ProductGroup();
                g.setCname("All Group#");
                g.save();
                return g;
            }
        }
    }

    /**
     * @param productgroupsids the productgroupsids to set
     */
    public void setProductgroupsids(int productgroupsids) {
        this.productgroupsids = productgroupsids;

    }

    /**
     * Create a tree model
     *
     * @param data
     * @param rootNode
     * @return
     */
    public static DefaultTreeModel toTreeModel(ArrayList<ProductGroup> data, ProductGroup rootNode) {

        DefaultMutableTreeNode node1 = null;
        if (data.size() > 0) {
            DatabaseObject clone = rootNode.clone();
            clone.ReadOnly(true);
            clone.setCname("/");
            node1 = new DefaultMutableTreeNode(clone);
//            data.remove(rootNode);//remove root if in list
            try {
                mpv5.YabsViewProxy.instance().setWaiting(true);
                node1 = addToParents(node1, data);

            } catch (Exception e) {
                Log.Debug(e);
            } finally {
                mpv5.YabsViewProxy.instance().setWaiting(false);
            }
        }
        DefaultTreeModel model = new DefaultTreeModel(node1);
        return model;
    }

    @SuppressWarnings("unchecked")
    private static DefaultMutableTreeNode addToParents(DefaultMutableTreeNode firstnode, ArrayList<ProductGroup> dobjlist) {

        for (int i = 0; i < dobjlist.size(); i++) {
            ProductGroup dobj = dobjlist.get(i);

            if (dobj.__getProductgroupsids() <= 0 && firstnode.isRoot()) {
//                Log.Debug(ArrayUtilities.class, "Node is root child, adding it to root and removing it from the list.");
                firstnode.add(new DefaultMutableTreeNode(dobj));
                dobjlist.remove(dobj);//First level groups
                i--;
            } else {
                int parentid = dobj.__getProductgroupsids();
                if (((ProductGroup) firstnode.getUserObject()).__getIDS().intValue() == parentid) {
//                    Log.Debug(ArrayUtilities.class, "Node is child of parentnode, adding and removing it from the list.");
                    firstnode.add(new DefaultMutableTreeNode(dobj));
                    dobjlist.remove(dobj);
                    i--;
                } else {
//                    Log.Debug(ArrayUtilities.class, "Node is no child of parentnode, iterating over the parent node..");
                    @SuppressWarnings("unchecked")
                    Enumeration<TreeNode> nodes = firstnode.children();
                    while (nodes.hasMoreElements()) {
                        addToParents((DefaultMutableTreeNode) nodes.nextElement(), dobjlist);
                    }
                }
            }
        }
        return firstnode;
    }

    /**
     *
     * @return True if the group has no parent group
     */
    public boolean isRoot() {
        return __getGroupsids() == 0;
    }

    @Override
    public void ensureUniqueness() {
        if (!QueryHandler.instanceOf().clone(Context.getProductGroup()).checkUniqueness("cname", getCname())) {
            throw new UnsupportedOperationException(Messages.VALUE_ALREADY_EXISTS + " " + getCname());
        }
    }

    @Override
    public void onBeforeSave() {
        if (isRoot() || Objects.equals(getDefault().ids, ids)) {
            return;
        }
        try {
            ProductGroup parent = (ProductGroup) getObject(Context.getProductGroup(), productgroupsids);
            if (!parent.__getHierarchypath().contains(getDefault().toString())) {
                //must be root of all
                productgroupsids = getDefault().ids;
            }
            //all good
        } catch (NodataFoundException ex) {
            Logger.getLogger(ProductGroup.class.getName()).log(Level.SEVERE, null, ex);
            productgroupsids = getDefault().ids;
        }
    }
}
