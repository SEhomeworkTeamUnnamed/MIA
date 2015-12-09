package GUI.GUIsmall;

import GUI.MainPane;
import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by 张思源 on 2015/12/8.
 */
public class testframe extends JFrame {
    testframe(/*DefaultTreeModel treeModel, JTree tree, FuncClass RootClass, */String FrameName){

        super(FrameName);

        //getContentPane().add(new MainPane(treeModel, tree, RootClass), BorderLayout.CENTER);
        getContentPane().add(new testpane());

        pack();
        setVisible(true);
        //设置Frame位置
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        setLocation(screenSize.width/4,screenSize.height/4);


        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //int option =
                //        JOptionPane.showConfirmDialog(null, "是否保存更改?", "",
                //                JOptionPane.YES_NO_CANCEL_OPTION);
                //System.out.print(option);
                System.exit(0);
            }
        });
    }


    public static void main(String[] args){
        new testframe("hhh");
    }
}
