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

        JMenu menu2 = new JMenu("编辑");
        JMenuItem addFuncClassItem = new JMenuItem("添加函数类");
        JMenuItem addMathFuncItem = new JMenuItem("添加函数");
        JMenuItem addVarRangeItem = new JMenuItem("添加变量范围");
        JMenuItem addParaItem = new JMenuItem("添加参数");
        JMenuItem deleteItem = new JMenuItem("删除");
        menu2.add(addFuncClassItem);
        menu2.add(addMathFuncItem);
        menu2.addSeparator();
        menu2.add(addVarRangeItem);
        menu2.add(addParaItem);
        menu2.addSeparator();
        menu2.add(deleteItem);
        add(menu2);

        JMenu menu3 =  new JMenu("搜索");
        JMenuItem searchFunc = new JMenuItem("搜索函数");
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
                new PopupFrame("添加函数类", treeModel, tree, PopupFrame.ADD_CLASS, mainPane);
            }
        });

        addMathFuncItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopupFrame("添加函数", treeModel, tree, PopupFrame.ADD_FUNC, mainPane);
            }
        });

        addParaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopupFrame("添加参数", treeModel, tree, PopupFrame.ADD_PARA, mainPane);

            }
        });

        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int UserChoice = JOptionPane.showConfirmDialog(deleteItem, "你确定删除吗？", "", JOptionPane.YES_NO_OPTION);
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
                int result = JOptionPane.showConfirmDialog(addVarRangeItem, "变量是否需设定步长？", "添加变量范围参数", JOptionPane.YES_NO_CANCEL_OPTION);
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
