package GUI;

import MathFunc.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IIIS on 11/7/2015.
 */
public class RightBottomPane extends JScrollPane{
    DefaultWelcomePane DWP;
    ClassAndFuncPane CAFP;
    ParaAndCodePanel PACP;
    TreePath treePath;
    JTree jTree;
    int newRow;

    public class ClassAndFuncPane extends JSplitPane{
        int ArrayNum;
        JPanel[] ArrayPanel;

        void SetOnePanel(String CaFName, String CaFNote, JPanel AimPanel, MathObject ClickedMathObject,int row){
            JPanel LeftPanel=new JPanel();  JPanel RightPanel=new JPanel();

            JButton CaFBotton=new JButton(CaFName);
            CaFBotton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int parentRow = newRow;
                    newRow = newRow + row+1;
                    //System.out.println(row);
                    //System.out.println(jTree.getRowForPath(treePath));
                    //System.out.println(newRow);
                    jTree.expandRow(jTree.getRowForPath(treePath));
                    for (int i = parentRow+1; i < newRow; i++) {
                        jTree.collapseRow(i);
                    }
                    jTree.expandRow(newRow);

                    //Object[] parentPathObject = treePath.getPath();
                    //Object[] currentPathObject = new MathObject[parentPathObject.length];
                    //for (int i = 0, len = parentPathObject.length; i < len; i++) {
                    //    currentPathObject[i] = parentPathObject[i];
                    //}
                    //currentPathObject[parentPathObject.length] = ClickedMathObject;
                    //TreePath currentTreePath = new TreePath(currentPathObject);
                    //jTree.scrollPathToVisible(currentTreePath);

                    if(ClickedMathObject instanceof FuncClass){
                        setClassAndFuncPane((FuncClass)ClickedMathObject);
                    }
                    else{
                        setParaAndCodePane((MathFunc)ClickedMathObject);
                    }
                }
            });
            LeftPanel.setLayout(new FlowLayout());
            LeftPanel.add(CaFBotton);

            JTextArea CaFTextArea=new JTextArea(CaFNote, 3, 15);
            CaFTextArea.setFont(new Font("微软雅黑", Font.BOLD, 20));
            CaFTextArea.setForeground(Color.BLACK);
            //CaFTextArea.setEnabled(false);
            CaFTextArea.setLineWrap(true);
            RightPanel.setLayout(new FlowLayout());
            RightPanel.add(new JScrollPane(CaFTextArea));

            AimPanel.setLayout(new GridLayout(0,2));
            AimPanel.add(LeftPanel);    AimPanel.add(RightPanel);
            return;
        }

        ClassAndFuncPane(String ParentNote, String[] CaFNames, String[] CaFNotes, FuncClass ParentFuncClass){
            super();

            ArrayNum=Math.max(CaFNames.length, CaFNotes.length);
            Box vBox = Box.createVerticalBox();
            if(ArrayNum!=0) {
                ArrayPanel = new JPanel[ArrayNum];
                for (int i = 0; i < ArrayNum; i++) {
                    ArrayPanel[i] = new JPanel();
                }

                //between are added by jwt on 20151211
                //

                for (int i = 0; i < ArrayNum; i++) {
                    int numOfSubClass = ParentFuncClass.getNumOfClass();
                    MathObject mathObjectToSend;
                    if (i < numOfSubClass) {
                        mathObjectToSend = ParentFuncClass.getAllFuncClass()[i];
                    } else {
                        mathObjectToSend = ParentFuncClass.getAllMathFunc()[i - numOfSubClass];
                    }
                    SetOnePanel(CaFNames[i], CaFNotes[i], ArrayPanel[i], mathObjectToSend, i);
                }


                vBox.add(ArrayPanel[0]);
                for (int i = 0; i < ArrayNum - 1; i++) {
                    vBox.add(Box.createVerticalStrut(10));  //这里的10可以调整！
                    vBox.add(ArrayPanel[i + 1]);
                }
            }

            JPanel CaFTopPanel=new JPanel();
            JPanel CaFBottomPanel=new JPanel();
            JTextArea ParentTextArea=new JTextArea(ParentNote, 3, 25);
            ParentTextArea.setFont(new Font("微软雅黑", Font.BOLD, 25));
            ParentTextArea.setForeground(Color.BLACK);
            //ParentTextArea.setEnabled(false);

            CaFTopPanel.setLayout(new FlowLayout());
            CaFTopPanel.add(new JScrollPane(ParentTextArea));
            CaFBottomPanel.setLayout(new BorderLayout());
            CaFBottomPanel.add(vBox, "Center");

            setOneTouchExpandable(true);
            setContinuousLayout(true);
            //setPreferredSize(new Dimension(600, 400));
            setOrientation(JSplitPane.VERTICAL_SPLIT);

            setTopComponent(CaFTopPanel);
            setBottomComponent(CaFBottomPanel);

            setDividerSize(3);
            setDividerLocation(100);
        }
    }

    RightBottomPane(JTree tree){
        super();
        DWP=new DefaultWelcomePane();
        jTree = tree;
        setDefaultWelcomePane();
        //DefaultWelcomePane DWP=new DefaultWelcomePane();
        //setViewportView(DWP);

        //String[] testString1 = new String[5];
        //for (int i = 0; i < 5; i++) {
        //    testString1[i] = i+"";
        //}
        //ClassAndFuncPane CFP=new ClassAndFuncPane("hhh", testString1, testString1);
        //setViewportView(CFP);

        //MathFunc mf=new MathFunc();
        //ParaAndCodePanel CGP=new ParaAndCodePanel(mf);
        //setViewportView(CGP);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {

                treePath=tree.getSelectionPath();
                newRow = jTree.getRowForPath(treePath);
                try {
                    DefaultMutableTreeNode selectionNode =
                            (DefaultMutableTreeNode) treePath.getLastPathComponent();
                    MathObject selectionNodeObject = (MathObject)selectionNode.getUserObject();
                    if(selectionNodeObject instanceof FuncClass){
                        setClassAndFuncPane((FuncClass)selectionNodeObject);
                    }
                    else{
                        setParaAndCodePane((MathFunc)selectionNodeObject);
                    }

                }catch(NullPointerException eNull){
                    eNull.printStackTrace();
                    setDefaultWelcomePane();
                }
            }
        });

    }

    void setDefaultWelcomePane(){
        setViewportView(DWP);
    }

    public void setClassAndFuncPane(FuncClass selectedFuncClass){
        int numOfCF = selectedFuncClass.getNumOfClass()+selectedFuncClass.getNumOfFunc();
        String[] CFNames=new String[numOfCF];
        String[] CFNotes=new String[numOfCF];
        for (int i = 0, len = selectedFuncClass.getNumOfClass(); i < len; i++) {
            CFNames[i] = selectedFuncClass.getAllFuncClass()[i].getObjectName();
            CFNotes[i] = selectedFuncClass.getAllFuncClass()[i].getNotes();
        }
        for (int i = selectedFuncClass.getNumOfClass(), i0 = selectedFuncClass.getNumOfClass(); i < numOfCF; i++) {
            CFNames[i] = selectedFuncClass.getAllMathFunc()[i - i0].getObjectName();
            CFNotes[i] = selectedFuncClass.getAllMathFunc()[i - i0].getNotes();
        }

        CAFP=new ClassAndFuncPane(selectedFuncClass.getNotes(),  CFNames, CFNotes, selectedFuncClass);
        setViewportView(CAFP);
    }
    public void setParaAndCodePane(MathFunc selectedMathFunc){
        PACP=new ParaAndCodePanel(selectedMathFunc);
        setViewportView(PACP);
    }
}
