package GUI;

import MathFunc.FuncClass;
import MathFunc.MathFunc;
import org.omg.CORBA.COMM_FAILURE;

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
    String NoteInput;
    public static int ADD_CLASS=0;
    public static int ADD_FUNC=1;
    JButton jButtonAffirm;
    JButton jButtonCancel;
    PopupPane popupPane;
    JTextArea jTextArea;


    class LowerPopupPane extends JPanel{
        LowerPopupPane(){
            setLayout(new FlowLayout());

            jButtonAffirm=new JButton("确认");
            jButtonCancel=new JButton("取消");
            add(jButtonAffirm);
            add(jButtonCancel);

        }

    }

    class UpperPopupPane extends JPanel{
        UpperPopupPane(int ClassOrFunc){
            setLayout(new FlowLayout());

            JLabel jLabel;
            if(ClassOrFunc == PopupFrame.ADD_CLASS) {
                jLabel = new JLabel("请输入函数类名：");
            }
            else{
                jLabel = new JLabel("请输入函数名：");
            }
            JTextField jTextField=new JTextField(10);
            add(jLabel);
            add(jTextField);

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

        }

    }

    class MidPopupPane extends JPanel{
        MidPopupPane(int ClassOrFunc){
            setLayout(new GridLayout(1,2));

            JLabel jLabel;
            if(ClassOrFunc == PopupFrame.ADD_CLASS) {
                jLabel = new JLabel("请输入函数类的说明：");
            }
            else{
                jLabel = new JLabel("请输入函数的说明：");
            }
            jTextArea = new JTextArea(3,10);
            jTextArea.setLineWrap(true);

            add(jLabel);
            add(new JScrollPane(jTextArea));
        }
    }

    class PopupPane extends JPanel{
        LowerPopupPane botPane;
        MidPopupPane midPopupPane;
        UpperPopupPane upPane;
        PopupPane(int ClassOrFunc){
            setPreferredSize(new Dimension(320, 200));
            setLayout(new FlowLayout());

            upPane = new UpperPopupPane(ClassOrFunc);
            midPopupPane = new MidPopupPane(ClassOrFunc);
            botPane = new LowerPopupPane();

            Box vBox = Box.createVerticalBox();
            vBox.add(upPane);
            vBox.add(Box.createVerticalStrut(10));
            vBox.add(midPopupPane);
            vBox.add(Box.createVerticalStrut(10));
            vBox.add(botPane);

            add(vBox);
        }
    }

    public PopupFrame(String FrameName,
                      DefaultTreeModel treeModel, JTree tree,
                      FuncClass SelectedClass, int ClassOrFunc){
        super(FrameName);

        setSize(320,200);
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        setLocation(screenSize.width/4,screenSize.height/4);


        popupPane = new PopupPane(ClassOrFunc);

        setContentPane(popupPane);

        setVisible(true);


        //jTextField.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        UserInput=jTextField.getText();
        //    }
        //});



        //addWindowListener(new WindowAdapter() {
        //    public void windowClosing(WindowEvent e) {
        //        System.exit(0);
        //    }
        //});

        jButtonAffirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultMutableTreeNode parentNode=null;
                try{
                    NoteInput = jTextArea.getText();
                }catch(Exception ex){
                    ex.printStackTrace();
                    NoteInput = "";
                }
                try{
                    if(UserInput.equals(null))
                    {
                        JOptionPane jOptionPane=new JOptionPane();
                        JOptionPane.showMessageDialog(jButtonAffirm,"请输入正确的英文名称！");
                    }
                    else{
                        setVisible(false);
                        TreePath parentPath=tree.getSelectionPath();
                        parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());
                        String parentClassName = ((FuncClass) parentNode.getUserObject()).getObjectName();
                        String newUpperPath = ((FuncClass) parentNode.getUserObject()).getUpperPath()+"\\C"+parentClassName;


                        if(ClassOrFunc==PopupFrame.ADD_CLASS) {
                            FuncClass newSubClass=new FuncClass(newUpperPath,UserInput,1);
                            newSubClass.setNotes(NoteInput);
                            SelectedClass.addSubClass(newSubClass);

                            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newSubClass);

                            newNode.setAllowsChildren(true);
                            treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());
                            tree.scrollPathToVisible(new TreePath(newNode.getPath()));
                        }
                        else if(ClassOrFunc==PopupFrame.ADD_FUNC){
                            MathFunc newMathFunc=new MathFunc(newUpperPath,UserInput);
                            newMathFunc.setNotes(NoteInput);
                            SelectedClass.addMathFunc(newMathFunc);

                            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newMathFunc);

                            newNode.setAllowsChildren(true);
                            treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());
                            tree.scrollPathToVisible(new TreePath(newNode.getPath()));
                        }
                    }

                }catch (NullPointerException nPE){
                    nPE.printStackTrace();
                    JOptionPane jOptionPane=new JOptionPane();
                    JOptionPane.showMessageDialog(jButtonAffirm,"请输入正确的英文名称！");
                }
                /*if(UserInput.equals(null)) {

                    if(ClassOrFunc==PopupFrame.ADD_CLASS) {
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("new class");

                        newNode.setAllowsChildren(true);
                        TreePath parentPath=tree.getSelectionPath();
                        parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());

                        treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());

                        SelectedClass.addSubClass(treePath2String(parentPath), "new class");
                        tree.scrollPathToVisible(new TreePath(newNode.getPath()));
                    }
                    else if(ClassOrFunc==PopupFrame.ADD_FUNC){
                        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("new class");

                        newNode.setAllowsChildren(true);
                        TreePath parentPath=tree.getSelectionPath();
                        parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());

                        treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());

                        SelectedClass.addMathFunc(treePath2String(parentPath), "new class");
                        tree.scrollPathToVisible(new TreePath(newNode.getPath()));
                    }

                }*/
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
