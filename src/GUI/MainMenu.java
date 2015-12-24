package GUI;

import MathFunc.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Menu of the GUI
 * Created by jwt625 on 11/7/2015.
 */
public class MainMenu extends JMenuBar {
    MainMenu(JTree tree, FuncClass RootClass, MainPane mainPane) {
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
        JMenuItem addFuncClassItem = new JMenuItem("��Ӻ�����");
        JMenuItem addMathFuncItem = new JMenuItem("��Ӻ���");
        JMenuItem addVarRangeItem = new JMenuItem("��ӱ�����Χ");
        JMenuItem addParaItem = new JMenuItem("��Ӳ���");
        JMenuItem deleteItem = new JMenuItem("ɾ��");
        menu2.add(addFuncClassItem);
        menu2.add(addMathFuncItem);
        menu2.addSeparator();
        menu2.add(addVarRangeItem);
        menu2.add(addParaItem);
        menu2.addSeparator();
        menu2.add(deleteItem);
        add(menu2);

        JMenu menu3 =  new JMenu("����");
        JMenuItem searchFunc = new JMenuItem("��������");
        menu3.add(searchFunc);
        add(menu3);

        addFuncClassItem.setEnabled(false);
        addMathFuncItem.setEnabled(false);
        addVarRangeItem.setEnabled(false);
        addParaItem.setEnabled(false);
        deleteItem.setEnabled(false);

        final DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();

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

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                deleteItem.setEnabled(true);
                TreePath treePath = tree.getSelectionPath();
                try {
                    DefaultMutableTreeNode selectionNode =
                            (DefaultMutableTreeNode) treePath.getLastPathComponent();
                    MathObject selectionNodeObject = (MathObject) selectionNode.getUserObject();

                    if (selectionNodeObject instanceof FuncClass) {
                        addFuncClassItem.setEnabled(true);
                        addMathFuncItem.setEnabled(true);
                        addVarRangeItem.setEnabled(false);
                        addParaItem.setEnabled(false);
                    } else {
                        addFuncClassItem.setEnabled(false);
                        addMathFuncItem.setEnabled(false);
                        addVarRangeItem.setEnabled(true);
                        addParaItem.setEnabled(true);
                    }
                } catch (NullPointerException eNull) {
                    eNull.printStackTrace();
                    addFuncClassItem.setEnabled(true);
                    addMathFuncItem.setEnabled(true);
                    addVarRangeItem.setEnabled(false);
                    addParaItem.setEnabled(false);
                    deleteItem.setEnabled(false);
                }

            }
        });

        addFuncClassItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopupFrame("��Ӻ�����", treeModel, tree, PopupFrame.ADD_CLASS, mainPane);
            }
        });

        addMathFuncItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopupFrame("��Ӻ���", treeModel, tree, PopupFrame.ADD_FUNC, mainPane);
            }
        });

        addParaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopupFrame("��Ӳ���", treeModel, tree, PopupFrame.ADD_PARA, mainPane);

            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int UserChoice = JOptionPane.showConfirmDialog(deleteItem, "��ȷ��ɾ����", "", JOptionPane.YES_NO_OPTION);
                if (UserChoice == 0) {
                    TreePath treePath = tree.getSelectionPath();
                    if (treePath != null) {
                        DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();

                        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectionNode.getParent();
                        MathObject selectionNodeObject = (MathObject) selectionNode.getUserObject();
                        if (parent != null) {
                            treeModel.removeNodeFromParent(selectionNode);
                            FuncClass parentFuncClass = (FuncClass) parent.getUserObject();

                            if (selectionNodeObject instanceof MathFunc) {
                                parentFuncClass.deleteMathFunc(selectionNode.toString());
                                //RootClass.outputFile();
                            } else if (selectionNodeObject instanceof FuncClass) {
                                parentFuncClass.deleteSubClass(selectionNode.toString());
                                //RootClass.outputFile();
                            }

                            addFuncClassItem.setEnabled(false);
                            addMathFuncItem.setEnabled(false);
                            addParaItem.setEnabled(false);
                            deleteItem.setEnabled(false);
                            //System.out.print(selectionNode.toString()+"\n");
                        }
                    }
                }
            }
        });

        addVarRangeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreePath parentPath = tree.getSelectionPath();
                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
                VarRangePara newVarRangePara = new VarRangePara();
                int result = JOptionPane.showConfirmDialog(addVarRangeItem, "�����Ƿ����趨������", "��ӱ�����Χ����", JOptionPane.YES_NO_CANCEL_OPTION);
                try {
                    if (result == 0) {
                        newVarRangePara.setHasStep(true);
                        ((MathFunc) parentNode.getUserObject()).addVarRangePara(newVarRangePara);
                    } else if (result == 1) {
                        ((MathFunc) parentNode.getUserObject()).addVarRangePara(newVarRangePara);
                    }
                    mainPane.setParaAndCodePane((MathFunc) parentNode.getUserObject());
                } catch (ClassCastException CCE) {
                    CCE.printStackTrace();
                }
            }
        });

        searchFunc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showConfirmDialog(menu3, "clicked!");
                new PopupSearchFrame(mainPane, RootClass);
            }
        });
    }
}
