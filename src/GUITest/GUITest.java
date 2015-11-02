package GUITest;

import MathFunc.*;

import javax.swing.*;
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
        RootClass.setObjectName("rootClass");
        RootClass.readAll();

        JFrame f=new JFrame("Ê÷×é¼þ²âÊÔ");
        Container contentPane=f.getContentPane();
        JTree tree=new JTree(RootClass.getHashtable());
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
