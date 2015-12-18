package GUI;

import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.*;

/**
 * Created by jwt625 on 11/4/2015.
 * ********
 * first try on GUI, useless
 * ********
 *
 * @author jwt625
 */
public class TreeListener implements TreeModelListener {
    //String nodeName=null;
    //DefaultTreeModel treeModel;
    public TreeListener(FuncClass RootClass,JTree tree){

        final DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();




        //RootClass.outputFile();
    }


    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        TreePath treePath= e.getTreePath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();

    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {

    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {

    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {

    }
    //class MouseHandle extends MouseAdapter{
    //    @Override
    //    public void mousePressed(MouseEvent e) {
    //        super.mousePressed(e);
    //        try{
    //            JTree tree = (JTree) e.getSource();
    //            int rowLocation = tree.getRowForLocation(e.getX(),e.getY());
    //            TreePath treePath = tree.getPathForRow(rowLocation);
    //            TreeNode treeNode = (TreeNode) treePath.getLastPathComponent();
    //            nodeName=treeNode.toString();
    //        }catch(NullPointerException ne){}
    //    }
    //}
    public String[] treePath2String(TreePath treePath){
        int len = treePath.getPathCount();
        String[] path=new String[len];
        //String p = "";
        for (int i = 0; i < len; i++) {
            path[i]=treePath.getPathComponent(i).toString();
            //p=p+treePath.getPathComponent(i).toString();
        }
        //System.out.print(p+"\n");
        return path;
    }

}
