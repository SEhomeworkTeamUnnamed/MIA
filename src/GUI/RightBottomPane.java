package GUI;

import MathFunc.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * Created by IIIS on 11/7/2015.
 */
public class RightBottomPane extends JScrollPane{
    DefaultWelcomePane DWP;
    ClassAndFuncPane CAFP;
    ParaAndCodePanel PACP;
    RightBottomPane(JTree tree, FuncClass RootClass){
        super();
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

                TreePath treePath=tree.getSelectionPath();
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
        DWP=new DefaultWelcomePane();
        setViewportView(DWP);
    }

    void setClassAndFuncPane(FuncClass selectedFuncClass){
        int numOfCF = selectedFuncClass.getNumOfClass()+selectedFuncClass.getNumOfFunc();
        String[] CFNames=new String[numOfCF];
        String[] CFNotes=new String[numOfCF];
        for (int i = 0, len = selectedFuncClass.getNumOfClass(); i < len; i++) {
            CFNames[i] = selectedFuncClass.getAllFuncClass()[i].getObjectName();
            CFNotes[i] = selectedFuncClass.getAllFuncClass()[i].getNotes();
        }
        for (int i = selectedFuncClass.getNumOfClass(), i0 = selectedFuncClass.getNumOfClass(); i < numOfCF; i++) {
            CFNames[i] = selectedFuncClass.getAllMathFunc()[i - i0].getObjectName();
            CFNotes[i] = selectedFuncClass.getAllMathFunc()[i - i0].getObjectName();
        }

        CAFP=new ClassAndFuncPane(selectedFuncClass.getNotes(),  CFNames, CFNotes);
        setViewportView(CAFP);
    }
    void setParaAndCodePane(MathFunc selectedMathFunc){
        PACP=new ParaAndCodePanel(selectedMathFunc);
        setViewportView(PACP);
    }
}
