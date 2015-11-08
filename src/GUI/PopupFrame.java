package GUI;

import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by IIIS on 11/8/2015.
 */
public class PopupFrame extends JFrame {
    String UserInput;
    public static int ADD_CLASS=0;
    public static int ADD_FUNC=1;

    public PopupFrame(String FrameName,String Instruction,
                      DefaultTreeModel treeModel, JTree tree,
                      FuncClass RootClass, int ClassOrFunc){
        super(FrameName);

        setSize(270,110);
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        setLocation(screenSize.width/4,screenSize.height/4);
        JLabel jLabel=new JLabel(Instruction);
        JTextField jTextField=new JTextField(10);
        JButton jButtonAffirm=new JButton("确认");
        JButton jButtonCancel=new JButton("取消");

        JPanel upPane=new JPanel();
        JPanel botPane=new JPanel();
        upPane.add(jLabel);
        upPane.add(jTextField);
        botPane.add(jButtonAffirm);
        botPane.add(jButtonCancel);


        JSplitPane jSPane=new JSplitPane();

        jSPane.setOneTouchExpandable(true);
        jSPane.setContinuousLayout(true);
        jSPane.setPreferredSize(new Dimension(270, 110));
        jSPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jSPane.setTopComponent(upPane);
        jSPane.setBottomComponent(botPane);
        jSPane.setDividerSize(3);
        jSPane.setDividerLocation(30);

        setContentPane(jSPane);

        setVisible(true);


        //jTextField.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        UserInput=jTextField.getText();
        //    }
        //});

        Document doc=new DefaultStyledDocument();
        jTextField.setDocument(doc);

        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                UserInput=jTextField.getText();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                UserInput=jTextField.getText();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                UserInput=jTextField.getText();
            }
        });

        //addWindowListener(new WindowAdapter() {
        //    public void windowClosing(WindowEvent e) {
        //        System.exit(0);
        //    }
        //});

        jButtonAffirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

                DefaultMutableTreeNode parentNode=null;
                if(UserInput.equals(null)) {
                    if(ClassOrFunc==PopupFrame.ADD_CLASS) {
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("new class");

                        newNode.setAllowsChildren(true);
                        TreePath parentPath=tree.getSelectionPath();
                        parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());

                        treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());

                        RootClass.addSubClass(treePath2String(parentPath), "new class");
                        tree.scrollPathToVisible(new TreePath(newNode.getPath()));
                    }
                    else if(ClassOrFunc==PopupFrame.ADD_FUNC){
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("new class");

                        newNode.setAllowsChildren(true);
                        TreePath parentPath=tree.getSelectionPath();
                        parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());

                        treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());

                        RootClass.addMathFunc(treePath2String(parentPath), "new class");
                        tree.scrollPathToVisible(new TreePath(newNode.getPath()));
                    }
                }
                else{
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(UserInput);

                    newNode.setAllowsChildren(true);
                    TreePath parentPath=tree.getSelectionPath();
                    parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());

                    treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());
                    if(ClassOrFunc==PopupFrame.ADD_CLASS) {
                        RootClass.addSubClass(treePath2String(parentPath), UserInput);
                    }
                    else if(ClassOrFunc==PopupFrame.ADD_FUNC){
                        RootClass.addMathFunc(treePath2String(parentPath), UserInput);
                    }
                    tree.scrollPathToVisible(new TreePath(newNode.getPath()));
                }
            }
        });

        jButtonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public String getUserInput() {
        return UserInput;
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
