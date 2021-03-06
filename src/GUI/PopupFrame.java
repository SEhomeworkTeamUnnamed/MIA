package GUI;

import MathFunc.*;
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
 * Frame pop up when user click one of the <code>edit</code> method from the menu
 * Created by jwt625 on 11/8/2015.
 */
public class PopupFrame extends JFrame {

    /**
     * String to accept user's input of Object Name
     */
    String UserInput;

    /**
     * String to accept user's input of Object Notes
     */
    String NoteInput;

    public static int ADD_CLASS=0;

    public static int ADD_FUNC=1;

    public static int ADD_PARA=2;

    /**
     * affirm button
     */
    JButton jButtonAffirm;

    /**
     * cancel button
     */
    JButton jButtonCancel;

    /**
     * panel of the PopupFrame
     */
    PopupPane popupPane;

    /**
     * JTextArea for user to input notes
     */
    JTextArea jTextArea;

    /**
     * JTextArea for user to input parameter values
     */
    JTextArea paraValueTextArea;



    /**
     * lower component panel of the Panel of the PopupFrame
     */
    class LowerPopupPane extends JPanel{
        LowerPopupPane(){
            setLayout(new FlowLayout());

            jButtonAffirm=new JButton("确认");
            jButtonCancel=new JButton("取消");
            add(jButtonAffirm);
            add(jButtonCancel);

        }

    }

    /**
     * upper component panel of the Panel of the PopupFrame
     */
    class UpperPopupPane extends JPanel{
        UpperPopupPane(int ClassOrFunc){
            setLayout(new FlowLayout());

            JLabel jLabel;
            if(ClassOrFunc == PopupFrame.ADD_CLASS) {
                jLabel = new JLabel("请输入函数类名：");
            }
            else if(ClassOrFunc == PopupFrame.ADD_FUNC){
                jLabel = new JLabel("请输入函数名：");
            }
            else{
                jLabel = new JLabel("请输入参数名称：");
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

    /**
     * mid component panel of the Panel of the PopupFrame
     */
    class MidPopupPane extends JPanel{
        MidPopupPane(int ClassOrFunc){
            setLayout(new GridLayout(1,2));

            JLabel jLabel;
            if(ClassOrFunc == PopupFrame.ADD_CLASS) {
                jLabel = new JLabel("请输入函数类的说明：");
                jTextArea = new JTextArea(3,10);
                jTextArea.setLineWrap(true);

                add(jLabel);
                add(new JScrollPane(jTextArea));
            }
            else if (ClassOrFunc == PopupFrame.ADD_FUNC) {
                jLabel = new JLabel("请输入函数的说明：");
                jTextArea = new JTextArea(3,10);
                jTextArea.setLineWrap(true);

                add(jLabel);
                add(new JScrollPane(jTextArea));
            }
            else if(ClassOrFunc == PopupFrame.ADD_PARA){
                jLabel = new JLabel("请输入参数的说明：");
                jTextArea = new JTextArea(3,10);
                jTextArea.setLineWrap(true);

                add(jLabel);
                add(new JScrollPane(jTextArea));
            }
            else{
                jLabel = new JLabel("请按行输入可取的参数值：");
                paraValueTextArea = new JTextArea(3,10);
                paraValueTextArea.setLineWrap(true);

                add(jLabel);
                add(new JScrollPane(paraValueTextArea));
            }
        }
    }

    /**
     * Panel of the PopupFrame
     */
    class PopupPane extends JPanel{
        LowerPopupPane botPane;
        MidPopupPane midPopupPane;
        UpperPopupPane upPane;
        PopupPane(int ClassOrFunc){
            setPreferredSize(new Dimension(350, 250));
            setLayout(new FlowLayout());

            upPane = new UpperPopupPane(ClassOrFunc);
            midPopupPane = new MidPopupPane(ClassOrFunc);
            botPane = new LowerPopupPane();

            Box vBox = Box.createVerticalBox();
            vBox.add(upPane);
            vBox.add(Box.createVerticalStrut(10));
            vBox.add(midPopupPane);
            vBox.add(Box.createVerticalStrut(10));
            if(ClassOrFunc == PopupFrame.ADD_PARA){
                vBox.add(new MidPopupPane(PopupFrame.ADD_PARA + 1));
                vBox.add(Box.createVerticalStrut(10));
            }
            vBox.add(botPane);

            add(vBox);
        }
    }

    /**construct PopupFrame to accept user's input
     * @param FrameName name of the frame
     * @param treeModel treeModel of the tree
     * @param tree tree composed of FuncClass and MathFunc
     * @param ClassOrFunc number determine the type of the PopupFrame
     * @param bottomPane Panel showing the information of the chosen MathObject,
     *                   for refreshing ofter modification
     */
    public PopupFrame(String FrameName,
                      DefaultTreeModel treeModel, JTree tree,
                      int ClassOrFunc, MainPane bottomPane){
        super(FrameName);

        setSize(350,250);
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        setLocation(screenSize.width/4,screenSize.height/4);


        popupPane = new PopupPane(ClassOrFunc);

        setContentPane(popupPane);

        setVisible(true);


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
                    if(UserInput.equals(""))
                    {
                        JOptionPane.showMessageDialog(jButtonAffirm,"请输入正确的名称！");
                    }
                    else{
                        setVisible(false);
                        TreePath parentPath=tree.getSelectionPath();
                        parentNode=(DefaultMutableTreeNode)(parentPath.getLastPathComponent());


                        if(ClassOrFunc==PopupFrame.ADD_CLASS) {
                            String parentClassName = ((FuncClass) parentNode.getUserObject()).getObjectName();
                            String newUpperPath = ((FuncClass) parentNode.getUserObject()).getUpperPath()+"\\C"+parentClassName;
                            FuncClass newSubClass=new FuncClass(newUpperPath,UserInput,1);
                            newSubClass.setNotes(NoteInput);
                            ((FuncClass) parentNode.getUserObject()).addSubClass(newSubClass);

                            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newSubClass);

                            newNode.setAllowsChildren(true);
                            treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());
                            tree.scrollPathToVisible(new TreePath(newNode.getPath()));
                            bottomPane.setClassAndFuncPane((FuncClass) parentNode.getUserObject());
                        }
                        else if(ClassOrFunc==PopupFrame.ADD_FUNC){
                            if(!checkName(UserInput)){
                                throw new NullPointerException("Func Name Error");
                            }
                            String parentClassName = ((FuncClass) parentNode.getUserObject()).getObjectName();
                            String newUpperPath = ((FuncClass) parentNode.getUserObject()).getUpperPath()+"\\C"+parentClassName;
                            MathFunc newMathFunc=new MathFunc(newUpperPath,UserInput);
                            newMathFunc.setNotes(NoteInput);
                            ((FuncClass) parentNode.getUserObject()).addMathFunc(newMathFunc);

                            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newMathFunc);

                            newNode.setAllowsChildren(true);
                            treeModel.insertNodeInto(newNode,parentNode,parentNode.getChildCount());
                            tree.scrollPathToVisible(new TreePath(newNode.getPath()));
                            bottomPane.setClassAndFuncPane((FuncClass) parentNode.getUserObject());
                        }
                        else{
                            String parentClassName = ((MathFunc) parentNode.getUserObject()).getObjectName();
                            String newUpperPath = ((MathFunc) parentNode.getUserObject()).getUpperPath()+"\\F"+parentClassName;
                            Para newPara=new Para();
                            newPara.setUpperPath(newUpperPath);
                            newPara.setObjectName(UserInput);
                            newPara.setNotes(NoteInput);
                            String paraValues = paraValueTextArea.getText();
                            newPara.addParaValue("default                                         ");
                            newPara.addParaValue(paraValues.substring(0, paraValues.indexOf("\n")));
                            if(paraValues.indexOf("\n")>=0) {
                                do {
                                    paraValues = paraValues.substring(paraValues.indexOf("\n")+1);
                                    try {
                                        newPara.addParaValue(paraValues.substring(0, paraValues.indexOf("\n")));
                                    }catch(StringIndexOutOfBoundsException SIOOBE){
                                        newPara.addParaValue(paraValues);
                                    }
                                } while (paraValues.indexOf("\n")>=0);
                            }
                            /*for testing
                            System.out.print(paraValues.substring(paraValues.indexOf("\n")));
                            */
                            ((MathFunc) parentNode.getUserObject()).addPara(newPara);
                            bottomPane.setParaAndCodePane((MathFunc) parentNode.getUserObject());
                        }
                    }

                }catch (NullPointerException nPE){
                    nPE.printStackTrace();
                    JOptionPane.showMessageDialog(jButtonAffirm,"请输入正确的仅由英文字母组成的名称！");
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
            /*for testing
            p=p+treePath.getPathComponent(i).toString();
            */
        }
        /*for testing
        System.out.print(p+"\n");
        */
        return path;
    }

    /**check whether a string is a acceptable Mathematica function Name
     * @param stringToCheck string to check
     * @return true if the string is acceptable
     */
    private boolean checkName(String stringToCheck){
        int len = stringToCheck.length();
        char tempCh;
        for (int i = 0; i < len; i++) {
            tempCh = stringToCheck.charAt(i);
            if((tempCh < 'A')||(tempCh > 'z')){
                return false;
            }
        }
        return true;
    }
}
