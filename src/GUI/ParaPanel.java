package GUI;

import MathFunc.MathFunc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import MathFunc.Para;
import MathFunc.VarRangePara;

/**
 * Created by 张思源 on 2015/12/10.
 */
public class ParaPanel extends JSplitPane{
    int ArrayNum1;  int ArrayNum2;
    JPanel[] ArrayPanel1;   JPanel[] ArrayPanel2;

    void SetOnePanel(Para OnePara, JPanel AimPanel){
        JPanel LeftPanel=new JPanel();  JPanel RightPanel=new JPanel();

        JTextArea ParaTextArea=new JTextArea(OnePara.getObjectName()+'\n'+ OnePara.getNotes(), 5, 20);
        ParaTextArea.setEnabled(false);
        LeftPanel.setLayout(new FlowLayout());
        LeftPanel.add(ParaTextArea);

        Box hBox=Box.createHorizontalBox();
        //JLabel ParaLabel=new JLabel("请选择您需要的参数：");
        ComboBoxModel mode=new AModel(OnePara.getAllParaValue());
        JComboBox combo=new JComboBox(mode);
        combo.setBorder(BorderFactory.createTitledBorder("请选择您需要的参数："));

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        hBox.add(combo);
        RightPanel.add(hBox);

        AimPanel.setLayout(new GridLayout(0,2));
        AimPanel.add(LeftPanel);    AimPanel.add(RightPanel);
        return;
    }

    void SetOnePanel(VarRangePara OneVarRangePara, JPanel AimPanel){
        JPanel LeftPanel=new JPanel();  JPanel RightPanel=new JPanel();

        JTextArea ParaTextArea=new JTextArea(OneVarRangePara.getObjectName()+'\n'+OneVarRangePara.getNotes(), 5, 20);
        ParaTextArea.setEnabled(false);
        LeftPanel.setLayout(new FlowLayout());
        LeftPanel.add(ParaTextArea);

        Box hBox=Box.createHorizontalBox();
        if(OneVarRangePara.IfHasStep()) {
            JLabel MinLabel=new JLabel("最小值："); JLabel StepLabel=new JLabel("步长："); JLabel MaxLabel=new JLabel("最大值：");
        JTextField MinTextField=new JTextField(10); JTextField StepTextField=new JTextField(10); JTextField MaxTextField=new JTextField(10);

        MinTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        StepTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        MaxTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        hBox.add(MinLabel); hBox.add(MinTextField); hBox.add(Box.createVerticalStrut(5));
        hBox.add(StepLabel); hBox.add(StepTextField); hBox.add(Box.createVerticalStrut(5));
        hBox.add(MaxLabel); hBox.add(MaxTextField);
    }
        else {
            JLabel MinLabel=new JLabel("最小值："); JLabel MaxLabel=new JLabel("最大值：");
            JTextField MinTextField=new JTextField(10); JTextField MaxTextField=new JTextField(10);

            MinTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            MaxTextField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            hBox.add(MinLabel); hBox.add(MinTextField); hBox.add(Box.createVerticalStrut(5));
            hBox.add(MaxLabel); hBox.add(MaxTextField);
        }
        RightPanel.add(hBox);

        AimPanel.setLayout(new GridLayout(0, 2));
        AimPanel.add(LeftPanel);    AimPanel.add(RightPanel);
        return;
    }

    ParaPanel(MathFunc InfoFunc){
        super();

        JPanel FuncIntrPanel=new JPanel();
        JTextArea FuncIntrTextArea=new JTextArea(InfoFunc.getNotes(), 5, 40);
        FuncIntrTextArea.setEnabled(false);
        FuncIntrPanel.setLayout(new BorderLayout());
        add(FuncIntrTextArea, "Center");

        ArrayNum1=InfoFunc.getNumOfVarRangePara();
        ArrayNum2=InfoFunc.getNumOfPara();
        ArrayPanel1=new JPanel[ArrayNum1];
        ArrayPanel2=new JPanel[ArrayNum2];

        JPanel MainFieldPanel=new JPanel();
        JPanel LeftMainFieldPanel=new JPanel(); JPanel RightMainFieldPanel=new JPanel();
        JTextArea MainFieldTextArea=new JTextArea(InfoFunc.getMainField()+'\n'+InfoFunc.getMainFieldNote(), 5, 20);
        MainFieldTextArea.setEnabled(false);
        LeftMainFieldPanel.setLayout(new FlowLayout());
        LeftMainFieldPanel.add(MainFieldTextArea);
        Box hBox=Box.createHorizontalBox();
        JLabel MainFieldLabel=new JLabel("MainField名称：");
        JTextField MainFieldLabelTextField=new JTextField(15);

        MainFieldLabelTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        hBox.add(MainFieldLabel); hBox.add(MainFieldLabelTextField);
        RightMainFieldPanel.add(hBox);
        MainFieldPanel.setLayout(new GridLayout(0, 2));
        MainFieldPanel.add(LeftMainFieldPanel); MainFieldPanel.add(RightMainFieldPanel);

        for (int i = 0; i < ArrayNum1; i++) {
            ArrayPanel1[i]=new JPanel();
        }

        for (int i = 0; i < ArrayNum2; i++) {
            ArrayPanel2[i]=new JPanel();
        }

        for(int i=0; i<ArrayNum1; i++)
        {
            SetOnePanel(InfoFunc.getAllVarRangePara()[i], ArrayPanel1[i]);
        }

        for(int i=0; i<ArrayNum2; i++)
        {
            SetOnePanel(InfoFunc.getAllVarRangePara()[i], ArrayPanel2[i]);
        }

        Box vBox=Box.createVerticalBox();
        vBox.add(MainFieldPanel);
        vBox.add(Box.createVerticalStrut(10));

        vBox.add(ArrayPanel1[0]);
        for(int i=0; i<ArrayNum1-1; i++){
            //vBox.add(Box.createVerticalStrut(10));  //这里的10可以调整！
            vBox.add(ArrayPanel1[i+1]);
        }

        vBox.add(Box.createVerticalStrut(10));

        vBox.add(ArrayPanel2[0]);
        for(int i=0; i<ArrayNum2-1; i++){
            //vBox.add(Box.createVerticalStrut(10));  //这里的10可以调整！
            vBox.add(ArrayPanel2[i+1]);
        }

        JButton CommitBotton=new JButton("提交");

        CommitBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        vBox.add(Box.createGlue());
        vBox.add(CommitBotton);

        JPanel FuncParaPanel=new JPanel();
        FuncParaPanel.setLayout(new BorderLayout());
        FuncParaPanel.add(vBox, "Center");

        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setOrientation(JSplitPane.VERTICAL_SPLIT);

        setTopComponent(FuncIntrPanel);
        setBottomComponent(FuncParaPanel);

        setDividerSize(3);
        setDividerLocation(100);
    }

}
