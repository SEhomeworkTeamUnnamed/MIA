package GUI;

import javax.swing.*;
import javax.swing.plaf.SplitPaneUI;
import java.awt.*;

/**
 * Created by 张思源 on 2015/12/9.
 */
public class ClassAndFuncPane extends JSplitPane{
    int ArrayNum;
    JPanel[] ArrayPanel;

    void SetOnePanel(String CaFName, String CaFNote, JPanel AimPanel){
        JPanel LeftPanel=new JPanel();  JPanel RightPanel=new JPanel();

        JButton CaFBotton=new JButton(CaFName);
        //CaFBotton.addActionListener(/**********/);
        LeftPanel.setLayout(new FlowLayout());
        LeftPanel.add(CaFBotton);

        JTextArea CaFTextArea=new JTextArea(CaFNote, 5, 20);
        CaFTextArea.setEnabled(false);
        RightPanel.setLayout(new FlowLayout());
        RightPanel.add(CaFTextArea);

        AimPanel.setLayout(new GridLayout(0,2));
        AimPanel.add(LeftPanel);    AimPanel.add(RightPanel);
        return;
    }

    ClassAndFuncPane(String ParentNote, String[] CaFNames, String[] CaFNotes){
        super();

        ArrayNum=Math.max(CaFNames.length, CaFNotes.length);
        ArrayPanel=new JPanel[ArrayNum];
        for (int i = 0; i < ArrayNum; i++) {
            ArrayPanel[i]=new JPanel();
        }

        for(int i=0; i<ArrayNum; i++)
        {
            SetOnePanel(CaFNames[i], CaFNotes[i], ArrayPanel[i]);
        }

        Box vBox=Box.createVerticalBox();
        vBox.add(ArrayPanel[0]);
        for(int i=0; i<ArrayNum-1; i++){
            vBox.add(Box.createVerticalStrut(10));  //这里的10可以调整！
            vBox.add(ArrayPanel[i+1]);
        }

        JPanel CaFTopPanel=new JPanel();
        JPanel CaFBottomPanel=new JPanel();
        JTextArea ParentTextArea=new JTextArea(ParentNote, 5, 40);
        ParentTextArea.setEnabled(false);

        CaFTopPanel.setLayout(new FlowLayout());
        CaFTopPanel.add(ParentTextArea);
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
