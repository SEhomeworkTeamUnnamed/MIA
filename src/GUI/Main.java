package GUI;

import MathFunc.*;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

/**
 * Created by jwt625 on 10/28/2015.
 *
 * @author jwt625
 */
public class Main {
    Main(){
        FuncClass RootClass=new FuncClass(System.getProperty("user.dir")+"\\root","Mathematica Input Assistant");

        JTree tree=new JTree(RootClass.getTreeNode());
        final DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();

        new MainFrame(tree,RootClass,"Mathematica Input Assistant");

    }
    public static void main(String[] args){
        new Main();
    }

}
