package GUI;

import MathFunc.FuncClass;
import MathFunc.MathFunc;
import MathFunc.MathObject;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IIIS on 11/7/2015.
 */
public class RightTopPane extends JSplitPane {
    public RightTopPane(JTree tree){
        super();
        final DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();

        JButton addFuncClassButt=new JButton("添加函数类");
        JButton addMathFuncButt=new JButton("添加函数");
        JButton addParaButt=new JButton("添加参数");//method not implement yet
        JButton deleteButt=new JButton("删除");
        addFuncClassButt.setEnabled(false);
        addMathFuncButt.setEnabled(false);
        addParaButt.setEnabled(false);
        deleteButt.setEnabled(false);

        Container con=new Container();
        con.setLayout(new FlowLayout());
        con.add(addFuncClassButt);con.add(addMathFuncButt);con.add(addParaButt);

        Container con2=new Container();
        con2.setLayout(new FlowLayout());
        con2.add(deleteButt);


        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setPreferredSize(new Dimension(37, 400));
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setDividerSize(3);
        setLeftComponent(con);
        setRightComponent(con2);
        setDividerLocation(350);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                deleteButt.setEnabled(true);
                TreePath treePath=tree.getSelectionPath();
                try {
                    DefaultMutableTreeNode selectionNode =
                            (DefaultMutableTreeNode) treePath.getLastPathComponent();
                    MathObject selectionNodeObject = (MathObject)selectionNode.getUserObject();
                    String[] Path = treePath2String(treePath.getParentPath());

                    if (selectionNodeObject instanceof FuncClass) {
                        addFuncClassButt.setEnabled(true);
                        addMathFuncButt.setEnabled(true);
                        addParaButt.setEnabled(false);
                    } else {
                        addFuncClassButt.setEnabled(false);
                        addMathFuncButt.setEnabled(false);
                        addParaButt.setEnabled(true);
                    }
                }catch(NullPointerException eNull){
                    addFuncClassButt.setEnabled(true);
                    addMathFuncButt.setEnabled(true);
                    addParaButt.setEnabled(false);
                    deleteButt.setEnabled(false);
                }

            }
        });

        addFuncClassButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopupFrame("添加函数类", treeModel, tree, PopupFrame.ADD_CLASS);
                //DefaultMutableTreeNode parentNode=null;
                //DefaultMutableTreeNode newNode=new DefaultMutableTreeNode("new class");
                //newNode.setAllowsChildren(true);
                //TreePath parentPath=tree.getSelectionPath();
                ////System.out.print(parentPath.getPath()[0]+" "+parentPath.getPath()[1]+"\n");
                ////System.out.print(parentPath.getPath()[0].toString());
//
                ////RootClass.print();
                //parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());
//
                //treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());
//
                //RootClass.addSubClass(treePath2String(parentPath),"new class");
                ////RootClass.outputFile();
//
                //tree.scrollPathToVisible(new TreePath(newNode.getPath()));

            }
        });

        addMathFuncButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopupFrame("添加函数", treeModel, tree, PopupFrame.ADD_FUNC);
                //DefaultMutableTreeNode parentNode=null;
                //DefaultMutableTreeNode newNode=new DefaultMutableTreeNode("new func");
                //newNode.setAllowsChildren(true);
                //TreePath parentPath=tree.getSelectionPath();
                ////System.out.print(parentPath.getPath()[0]+" "+parentPath.getPath()[1]+"\n");
//
                //parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());
//
                //treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());
//
                //RootClass.addMathFunc(treePath2String(parentPath),"new func");
                ////RootClass.outputFile();
//
                //tree.scrollPathToVisible(new TreePath(newNode.getPath()));

            }
        });

        addParaButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopupFrame("添加参数", treeModel, tree, PopupFrame.ADD_PARA);

            }
        });

        deleteButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane jOptionPane=new JOptionPane();
                int UserChoice = jOptionPane.showConfirmDialog(deleteButt, "你确定删除吗？","", JOptionPane.YES_NO_OPTION);
                if(UserChoice == 0) {
                    TreePath treePath = tree.getSelectionPath();
                    if (treePath != null) {
                        DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode) treePath.getLastPathComponent();

                        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) selectionNode.getParent();
                        MathObject selectionNodeObject = (MathObject) selectionNode.getUserObject();
                        if (parent != null) {
                            treeModel.removeNodeFromParent(selectionNode);

                            String[] Path = treePath2String(treePath.getParentPath());
                            FuncClass parentFuncClass = (FuncClass)parent.getUserObject();

                            if (selectionNodeObject instanceof MathFunc) {
                                parentFuncClass.deleteMathFunc(selectionNode.toString());
                                //RootClass.outputFile();
                            } else if (selectionNodeObject instanceof FuncClass) {
                                parentFuncClass.deleteSubClass(selectionNode.toString());
                                //RootClass.outputFile();
                            }

                            addFuncClassButt.setEnabled(false);
                            addMathFuncButt.setEnabled(false);
                            addParaButt.setEnabled(false);
                            deleteButt.setEnabled(false);
                            //System.out.print(selectionNode.toString()+"\n");
                        }
                    }
                }
            }
        });
    }


    public String[] treePath2String(TreePath treePath){
        int len = treePath.getPathCount();
        String[] path=new String[len];
        //String p = "";
        for (int i = 0; i < len; i++) {
            path[i]=treePath.getPathComponent(i).toString();
            //p=p+treePath.getPathComponent(i).toString();
        }
        //System.out.print(p+"\n");
        return path;
    }

}
