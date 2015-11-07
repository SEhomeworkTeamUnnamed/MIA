package GUI;

import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by IIIS on 11/7/2015.
 */
public class MainFrame extends JFrame {
    MainFrame(DefaultTreeModel treeModel, JTree tree, FuncClass RootClass,String FrameName){
        super(FrameName);

        JMenuBar menuBar1=new JMenuBar();
        JMenu menu1=new JMenu("文件");
        JMenuItem item1=new JMenuItem("打开");
        JMenuItem item2=new JMenuItem("保存");
        menu1.add(item1);
        menu1.add(item2);
        menuBar1.add(menu1);
        setJMenuBar(menuBar1);

        Container contentPane=getContentPane();
        contentPane.add(new MainPane(treeModel, tree, RootClass),BorderLayout.CENTER);


        pack();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RootClass.outputFile();
            }
        });
    }
}
