package GUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;

import MathFunc.*;
import StringComp.StringComp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jwt625 on 12/24/2015.
 */
public class PopupSearchFrame extends JFrame {
    class PopupPane extends JSplitPane{

        class LowerPane extends JScrollPane{
            class ButtonWithFunc extends JButton{
                MathFunc mathFunc;
                ButtonWithFunc(MathFunc AssociatedFunc){
                    super(AssociatedFunc.getObjectName());
                    mathFunc = AssociatedFunc;
                }
                public MathFunc getMathFunc() {
                    return mathFunc;
                }
            }
            LowerPane(MathFunc[] Results){
                super();
                JPanel jPanel = new JPanel();
                if (Results == null){
                    jPanel.setLayout(new GridLayout(1, 1));
                    jPanel.add(new JButton());
                }
                else{
                    int len = Results.length;
                    jPanel.setLayout(new GridLayout(len,1));
                    for (int i = 0; i < len; i++) {
                        ButtonWithFunc jButton = new ButtonWithFunc(Results[i]);
                        jPanel.add(jButton);
                        jButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                setFrameVisible(false);
                                BottomPane.setParaAndCodePane(jButton.getMathFunc());
                            }
                        });
                    }
                }
                setViewportView(jPanel);
            }

        }

        LowerPane lowerPane;
        MainPane BottomPane;

        PopupPane(MainPane bottomPane){
            super();
            BottomPane = bottomPane;
            setOneTouchExpandable(true);
            setContinuousLayout(true);
            setPreferredSize(new Dimension(200, 200));
            setDividerSize(3);
            setDividerLocation(30);
            setOrientation(JSplitPane.VERTICAL_SPLIT);

            JTextField inputField = new JTextField();

            Document MainFieldDoc=new DefaultStyledDocument();
            inputField.setDocument(MainFieldDoc);

            MainFieldDoc.addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    StringComp stringComp = new StringComp();
                    MathFunc[] tempAllFuncs = new MathFunc[AllMathFuncs.length];
                    for (int i = 0, len = AllMathFuncs.length; i < len; i++) {
                        tempAllFuncs[i] = AllMathFuncs[i];
                    }
                    setLowerPane(stringComp.simRank(inputField.getText(), tempAllFuncs));
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    StringComp stringComp = new StringComp();
                    MathFunc[] tempAllFuncs = new MathFunc[AllMathFuncs.length];
                    for (int i = 0, len = AllMathFuncs.length; i < len; i++) {
                        tempAllFuncs[i] = AllMathFuncs[i];
                    }
                    setLowerPane(stringComp.simRank(inputField.getText(), tempAllFuncs));
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    StringComp stringComp = new StringComp();
                    MathFunc[] tempAllFuncs = new MathFunc[AllMathFuncs.length];
                    for (int i = 0, len = AllMathFuncs.length; i < len; i++) {
                        tempAllFuncs[i] = AllMathFuncs[i];
                    }
                    setLowerPane(stringComp.simRank(inputField.getText(), tempAllFuncs));
                }
            });

            setTopComponent(inputField);
            lowerPane = new LowerPane(null);
            setBottomComponent(lowerPane);
        }
        void setLowerPane(MathFunc[] Results){
            lowerPane = new LowerPane(Results);
            setBottomComponent(lowerPane);
        }
    }

    PopupPane popupPane;
    MathFunc[] AllMathFuncs;

    PopupSearchFrame(MainPane bottomPane, FuncClass RootClass){
        super("ËÑË÷º¯Êý");
        AllMathFuncs = RootClass.getAllInsideMathFunc();
        System.out.println("Search frame created");
        for (int i = 0, len = AllMathFuncs.length; i < len; i++) {

            System.out.println(AllMathFuncs[i].getObjectName());
        }
        System.out.println("print ended");
        setSize(200,200);
        popupPane = new PopupPane(bottomPane);
        setContentPane(popupPane);
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        setLocation(screenSize.width/4,screenSize.height/4);
        pack();
        setVisible(true);

    }
    void setFrameVisible(boolean visible){
        setVisible(visible);
    }
}
