package GUITest;

import MathFunc.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by IIIS on 11/2/2015.
 */
public class GUITest {
    public GUITest(){

        FuncClass RootClass=new FuncClass();
        RootClass.setUpperPath(System.getProperty("user.dir")+"\\root");
        RootClass.setObjectName("Mathematica Input Assistant");
        RootClass.readAll();
        DefaultMutableTreeNode RootNode = RootClass.getTreeNode();

        JFrame f=new JFrame("Ê÷×é¼þ²âÊÔ");

        Container contentPane=f.getContentPane();

        JTree tree=new JTree(RootNode);
        //tree.setEditable(true);

        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setViewportView(tree);
        contentPane.add(scrollPane);

        f.pack();
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        RootClass.outputFile();
    }
    public static void main(String [] args){
        new GUITest();
    }
}
