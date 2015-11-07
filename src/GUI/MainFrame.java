package GUI;

import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by IIIS on 11/7/2015.
 */
public class MainFrame extends JFrame {
    MainFrame(DefaultTreeModel treeModel, JTree tree, FuncClass RootClass,String FrameName){

        super(FrameName);

        setJMenuBar(new MainMenu(RootClass));

        getContentPane().add(new MainPane(treeModel, tree, RootClass),BorderLayout.CENTER);


        pack();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
}
