package GUITest;

import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by IIIS on 11/4/2015.
 */
public class TreeListener implements TreeModelListener {
    //String nodeName=null;
    //DefaultTreeModel treeModel;
    public TreeListener(){

        FuncClass RootClass=new FuncClass();
        RootClass.setUpperPath(System.getProperty("user.dir")+"\\root");
        RootClass.setObjectName("rootClass");
        RootClass.readAll();
        DefaultMutableTreeNode RootNode = RootClass.getTreeNode();

        JFrame f=new JFrame("���������");

        Container contentPane=f.getContentPane();

        JTree tree=new JTree(RootNode);
        tree.setEditable(true);
        //tree.addMouseListener(new MouseHandle());
        final DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();

        //treeModel=(DefaultTreeModel)tree.getModel();
        treeModel.addTreeModelListener(this);


        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setViewportView(tree);
        JButton b1=new JButton("������");
        JButton b2=new JButton("���Ӻ���");
        JButton b3=new JButton("ɾ��");
        Container con=new Container();
        con.setLayout(new FlowLayout());
        con.add(b1);con.add(b2);con.add(b3);
        contentPane.add(con,BorderLayout.SOUTH);

        contentPane.add(scrollPane,BorderLayout.CENTER);

        f.pack();
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode parentNode=null;
                DefaultMutableTreeNode newNode=new DefaultMutableTreeNode("new node");
                newNode.setAllowsChildren(true);
                TreePath parentPath=tree.getSelectionPath();
                //System.out.print(parentPath.getPath()[0]+" "+parentPath.getPath()[1]+"\n");
                //System.out.print(parentPath.getPath()[0].toString());

                //RootClass.print();
                parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());

                treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());

                RootClass.addSubClass(treePath2String(parentPath),"new node");
                RootClass.outputFile();

                tree.scrollPathToVisible(new TreePath(newNode.getPath()));

            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode parentNode=null;
                DefaultMutableTreeNode newNode=new DefaultMutableTreeNode("new node");
                newNode.setAllowsChildren(true);
                TreePath parentPath=tree.getSelectionPath();
                //System.out.print(parentPath.getPath()[0]+" "+parentPath.getPath()[1]+"\n");

                parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());

                treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());

                RootClass.addMathFunc(treePath2String(parentPath),"new node");
                RootClass.outputFile();

                tree.scrollPathToVisible(new TreePath(newNode.getPath()));

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
                            RootClass.deleteMathFunc(Path,selectionNode.toString());
                            RootClass.outputFile();
                        }
                        else if(RootClass.hasSubClass(Path,selectionNode.toString())){
                            RootClass.deleteSubClass(Path,selectionNode.toString());
                            RootClass.outputFile();
                        }

                            //System.out.print(selectionNode.toString()+"\n");
                    }
                }
            }
        });


        //RootClass.outputFile();
    }

    public static void main(String[] args){
        new TreeListener();
    }
    @Override
    public void treeNodesChanged(TreeModelEvent e) {
        TreePath treePath= e.getTreePath();
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();

    }

    @Override
    public void treeNodesInserted(TreeModelEvent e) {

    }

    @Override
    public void treeNodesRemoved(TreeModelEvent e) {

    }

    @Override
    public void treeStructureChanged(TreeModelEvent e) {

    }
    //class MouseHandle extends MouseAdapter{
    //    @Override
    //    public void mousePressed(MouseEvent e) {
    //        super.mousePressed(e);
    //        try{
    //            JTree tree = (JTree) e.getSource();
    //            int rowLocation = tree.getRowForLocation(e.getX(),e.getY());
    //            TreePath treePath = tree.getPathForRow(rowLocation);
    //            TreeNode treeNode = (TreeNode) treePath.getLastPathComponent();
    //            nodeName=treeNode.toString();
    //        }catch(NullPointerException ne){}
    //    }
    //}
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
