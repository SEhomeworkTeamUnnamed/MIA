package GUI;

import MathFunc.FuncClass;
import MathFunc.MathFunc;
import MathFunc.VarRangePara;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IIIS on 11/7/2015.
 */
public class MainMenu extends JMenuBar {
    MainMenu(JTree tree,FuncClass RootClass) {
        super();

        JMenu menu1 = new JMenu("�ļ�");
        JMenuItem item1 = new JMenuItem("��");
        JMenuItem item2 = new JMenuItem("����");
        JMenuItem item3 = new JMenuItem("���Ϊ..");
        JMenuItem item4 = new JMenuItem("�˳�");
        menu1.add(item1);
        menu1.add(item2);
        menu1.add(item3);
        menu1.addSeparator();
        menu1.add(item4);
        add(menu1);

        JMenu menu2 = new JMenu("�༭");
        JMenuItem item21 = new JMenuItem("��ӱ�����Χ");
        menu2.add(item21);
        add(menu2);

        final DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFile openFile = new OpenFile("���ѱ�������ݿ��ļ���(��C��ͷ)");
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
                OpenFile openFile = new OpenFile("���Ϊ..", RootClass.getUpperPath());
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

        item21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreePath parentPath=tree.getSelectionPath();
                DefaultMutableTreeNode parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());
                VarRangePara newVarRangePara = new VarRangePara();
                int result = JOptionPane.showConfirmDialog(item21, "�����Ƿ����趨������", "��ӱ�����Χ����", JOptionPane.YES_NO_CANCEL_OPTION);
                try {
                    if (result == 0) {
                        newVarRangePara.setHasStep(true);
                        ((MathFunc) parentNode.getUserObject()).addVarRangePara(newVarRangePara);
                    } else if (result == 1) {
                        ((MathFunc) parentNode.getUserObject()).addVarRangePara(newVarRangePara);
                    }
                }catch (ClassCastException CCE){
                    CCE.printStackTrace();
                }
            }
        });
    }
}
