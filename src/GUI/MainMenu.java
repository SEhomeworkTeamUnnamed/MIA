package GUI;

import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IIIS on 11/7/2015.
 */
public class MainMenu extends JMenuBar {
    MainMenu(JTree tree,FuncClass RootClass) {
        super();

        JMenu menu1 = new JMenu("文件");
        JMenuItem item1 = new JMenuItem("打开");
        JMenuItem item2 = new JMenuItem("保存");
        JMenuItem item3 = new JMenuItem("另存为..");
        JMenuItem item4 = new JMenuItem("退出");
        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.addSeparator();
        menu1.add(item4);
        add(menu1);

        final DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFile openFile = new OpenFile("打开已保存的数据库文件夹(以C开头)");
                int result = openFile.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    RootClass.clearAll();
                    RootClass.readAll(
                            openFile.getSelectedFile().getParent(),
                            openFile.getSelectedFile().getName().substring(1)
                    );
                    RootClass.print();
                    treeModel.setRoot(RootClass.getTreeNode());
                    //tree.setModel((new JTree(RootClass.getTreeNode())).getModel());
                }
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RootClass.outputFile();
            }
        });

        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFile openFile = new OpenFile("另存为..", RootClass.getUpperPath());
                int result = openFile.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {

                }
            }
        });

        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.exit(0);
            }
        });
    }
}
