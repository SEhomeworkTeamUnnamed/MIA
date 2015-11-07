package GUI;

import MathFunc.*;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Created by IIIS on 10/28/2015.
 *
 * @author jwt625
 */
public class Main {
    Main(){
        FuncClass RootClass=new FuncClass();
        RootClass.setUpperPath(System.getProperty("user.dir")+"\\root");
        RootClass.setObjectName("rootClass");
        RootClass.readAll();
        DefaultMutableTreeNode RootNode = RootClass.getTreeNode();

        JTree tree=new JTree(RootNode);
        //tree.setEditable(true);
        //tree.addMouseListener(new MouseHandle());
        final DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();

        treeModel.addTreeModelListener(new GUI.TreeListener(RootClass,tree));

        new MainFrame(treeModel,tree,RootClass,"GUI≤‚ ‘ modified");

    }
    public static void main(String[] args){
        new Main();
    }

}
