package GUI;

import MathFunc.FuncClass;

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
    public RightTopPane(DefaultTreeModel treeModel, JTree tree, FuncClass RootClass){
        super();

        JButton b1=new JButton("添加函数类");
        JButton b2=new JButton("添加函数");
        JButton b22=new JButton("添加参数");//method not implement yet
        JButton b3=new JButton("删除");
        b1.setEnabled(false);
        b2.setEnabled(false);
        b22.setEnabled(false);
        b3.setEnabled(false);

        Container con=new Container();
        con.setLayout(new FlowLayout());
        con.add(b1);con.add(b2);con.add(b22);

        Container con2=new Container();
        con2.setLayout(new FlowLayout());
        con2.add(b3);


        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setPreferredSize(new Dimension(37, 400));
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        setDividerSize(3);
        setLeftComponent(con);
        setRightComponent(con2);
        setDividerLocation(300);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                b3.setEnabled(true);
                TreePath treePath=tree.getSelectionPath();
                try {
                    DefaultMutableTreeNode selectionNode =
                            (DefaultMutableTreeNode) treePath.getLastPathComponent();
                    String[] Path = treePath2String(treePath.getParentPath());
                    if (RootClass.getIndexOfClass(Path, selectionNode.toString()) >= 0) {
                        b1.setEnabled(true);
                        b2.setEnabled(true);
                        b22.setEnabled(false);
                    } else {
                        b1.setEnabled(false);
                        b2.setEnabled(false);
                        b22.setEnabled(true);
                    }
                }catch(NullPointerException eNull){
                    b1.setEnabled(true);
                    b2.setEnabled(true);
                    b22.setEnabled(false);
                    b3.setEnabled(false);
                }

            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopupFrame("","请输入函数类名：",treeModel,tree,RootClass,PopupFrame.ADD_CLASS);
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

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopupFrame("","请输入函数名：",treeModel,tree,RootClass,PopupFrame.ADD_FUNC);
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

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreePath treePath=tree.getSelectionPath();
                if(treePath!=null){
                    DefaultMutableTreeNode selectionNode=(DefaultMutableTreeNode)treePath.getLastPathComponent();

                    TreeNode parent=(TreeNode)selectionNode.getParent();
                    if(parent!=null){
                        treeModel.removeNodeFromParent(selectionNode);

                        String[] Path=treePath2String(treePath.getParentPath());

                        if(RootClass.hasMathFunc(Path,selectionNode.toString())){
                            RootClass.deleteMathFunc(Path, selectionNode.toString());
                            //RootClass.outputFile();
                        }
                        else if(RootClass.hasSubClass(Path,selectionNode.toString())){
                            RootClass.deleteSubClass(Path,selectionNode.toString());
                            //RootClass.outputFile();
                        }

                        b1.setEnabled(false);
                        b2.setEnabled(false);
                        b22.setEnabled(false);
                        b3.setEnabled(false);
                        //System.out.print(selectionNode.toString()+"\n");
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
