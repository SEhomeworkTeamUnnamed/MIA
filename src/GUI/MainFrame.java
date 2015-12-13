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
    MainMenu mainMenu;
    MainPane mainPane;

    MainFrame(JTree tree, FuncClass RootClass,String FrameName){

        super(FrameName);
        mainMenu = new MainMenu(tree,RootClass);
        mainPane = new MainPane(tree);

        setJMenuBar(mainMenu);

        getContentPane().add(mainPane, BorderLayout.CENTER);

        pack();
        setVisible(true);
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        setLocation(screenSize.width/4,screenSize.height/4);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //int option =
                //        JOptionPane.showConfirmDialog(null, "�Ƿ񱣴����?", "",
                //                JOptionPane.YES_NO_CANCEL_OPTION);
                //System.out.print(option);
                System.exit(0);
            }
        });

    }
}
