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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by IIIS on 11/4/2015.
 *
 * @author jwt625
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



        JTree tree=new JTree(RootNode);
        //tree.setEditable(true);
        //tree.addMouseListener(new MouseHandle());
        final DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();

        //treeModel=(DefaultTreeModel)tree.getModel();
        treeModel.addTreeModelListener(this);


        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setViewportView(tree);



        JButton b1=new JButton("增加函数类");
        JButton b2=new JButton("增加函数");
        JButton b3=new JButton("删除");

        Container con=new Container();
        con.setLayout(new FlowLayout());
        con.add(b1);con.add(b2);

        Container con2=new Container();
        con2.setLayout(new FlowLayout());
        con2.add(b3);

        JSplitPane subsubJSP=new JSplitPane();
        subsubJSP.setOneTouchExpandable(true);
        subsubJSP.setContinuousLayout(true);
        subsubJSP.setPreferredSize(new Dimension(37,400));
        subsubJSP.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        subsubJSP.setDividerSize(3);
        subsubJSP.setLeftComponent(con);
        subsubJSP.setRightComponent(con2);
        subsubJSP.setDividerLocation(300);


        JPanel jPanel=new JPanel();
        JScrollPane scrollPaneRight=new JScrollPane();
        scrollPaneRight.setViewportView(jPanel);

        JSplitPane subJSplitPane=new JSplitPane();
        subJSplitPane.setOneTouchExpandable(true);
        subJSplitPane.setContinuousLayout(true);
        subJSplitPane.setPreferredSize(new Dimension(447,400));
        subJSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        subJSplitPane.setTopComponent(subsubJSP);
        subJSplitPane.setBottomComponent(scrollPaneRight);
        subJSplitPane.setDividerSize(3);
        subJSplitPane.setDividerLocation(40);


        JSplitPane jSplitPane=new JSplitPane();
        jSplitPane.setOneTouchExpandable(true);
        jSplitPane.setContinuousLayout(true);
        jSplitPane.setPreferredSize(new Dimension(600,400));
        jSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPane.setLeftComponent(scrollPane);
        jSplitPane.setRightComponent(subJSplitPane);
        jSplitPane.setDividerSize(3);
        jSplitPane.setDividerLocation(150);


        JFrame f=new JFrame("GUI测试");
        Container contentPane=f.getContentPane();

        JMenuBar menuBar1=new JMenuBar();
        f.setJMenuBar(menuBar1);
        JMenu menu1=new JMenu("文件");
        JMenuItem item1=new JMenuItem("打开");
        JMenuItem item2=new JMenuItem("保存");
        menu1.add(item1);
        menu1.add(item2);
        menuBar1.add(menu1);


        //Container con2=new Container();
        //con2.setLayout(new FlowLayout());
        //JButton b4=new JButton("保存更改");
        //con2.add(b4);

        //contentPane.add(con,BorderLayout.SOUTH);
        //contentPane.add(con2,BorderLayout.NORTH);

        contentPane.add(jSplitPane,BorderLayout.CENTER);

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
                DefaultMutableTreeNode newNode=new DefaultMutableTreeNode("new class");
                newNode.setAllowsChildren(true);
                TreePath parentPath=tree.getSelectionPath();
                //System.out.print(parentPath.getPath()[0]+" "+parentPath.getPath()[1]+"\n");
                //System.out.print(parentPath.getPath()[0].toString());

                //RootClass.print();
                parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());

                treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());

                RootClass.addSubClass(treePath2String(parentPath),"new class");
                //RootClass.outputFile();

                tree.scrollPathToVisible(new TreePath(newNode.getPath()));

            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode parentNode=null;
                DefaultMutableTreeNode newNode=new DefaultMutableTreeNode("new func");
                newNode.setAllowsChildren(true);
                TreePath parentPath=tree.getSelectionPath();
                //System.out.print(parentPath.getPath()[0]+" "+parentPath.getPath()[1]+"\n");

                parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());

                treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());

                RootClass.addMathFunc(treePath2String(parentPath),"new func");
                //RootClass.outputFile();

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
                            RootClass.deleteMathFunc(Path, selectionNode.toString());
                            //RootClass.outputFile();
                        }
                        else if(RootClass.hasSubClass(Path,selectionNode.toString())){
                            RootClass.deleteSubClass(Path,selectionNode.toString());
                            //RootClass.outputFile();
                        }

                            //System.out.print(selectionNode.toString()+"\n");
                    }
                }
            }
        });

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RootClass.outputFile();
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
