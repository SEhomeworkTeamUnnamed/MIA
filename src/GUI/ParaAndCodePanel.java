package GUI;

import MathFunc.*;
import com.sun.deploy.panel.JavaPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.xml.ws.soap.MTOMFeature;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 张思源 on 2015/12/10.
 */
public class ParaAndCodePanel extends JSplitPane{

    JButton CommitBotton;
    public class CodeGenePanel extends JPanel{
        JTextField CodeGeneTextField;
        CodeGenePanel(){
            CommitBotton=new JButton("生成代码");
            CodeGeneTextField=new JTextField(90);
            Box hBox=Box.createHorizontalBox();
            hBox.add(CommitBotton); hBox.add(Box.createHorizontalStrut(10));    hBox.add(CodeGeneTextField);
            //setLayout(new BorderLayout());
            add(hBox);

            Document CodeGeneTextDoc=new DefaultStyledDocument();
            CodeGeneTextField.setDocument(CodeGeneTextDoc);


        }

        void setCodeGeneText(String text){
            CodeGeneTextField.setText(text);
        }
    }

    public class ParaPanel extends JSplitPane{
        int ArrayNum1;  int ArrayNum2;
        JPanel[] ArrayPanel1;   JPanel[] ArrayPanel2;
        MathFunc ChosenFunc;

        public class AModel extends DefaultComboBoxModel{
            AModel(String[] s){
                for (int i = 0; i < s.length; i++) {
                    addElement(s[i]);
                }
            }
        }

        void SetOnePanel(Para OnePara, JPanel AimPanel){
            JPanel LeftPanel=new JPanel();  JPanel RightPanel=new JPanel();

            JTextArea ParaTextArea=new JTextArea("参数名称："+ OnePara.getObjectName()+'\n'+ OnePara.getNotes(), 3, 20);
            ParaTextArea.setFont(new Font("微软雅黑", Font.BOLD, 20));
            ParaTextArea.setForeground(Color.BLACK);
            //ParaTextArea.setEnabled(false);
            ParaTextArea.setLineWrap(true);
            LeftPanel.setLayout(new FlowLayout());
            LeftPanel.add(new JScrollPane(ParaTextArea));

            Box hBox=Box.createHorizontalBox();
            //JLabel ParaLabel=new JLabel("请选择您需要的参数：");
            ComboBoxModel mode=new AModel(OnePara.getAllParaValue());
            JComboBox combo=new JComboBox(mode);
            combo.setBorder(BorderFactory.createTitledBorder("请选择您需要的参数："));
            //cmyCustomizedField.setMinimumSize(new Dimension(20,20));
            //combo.setMinimumSize(new Dimension(10,5));


            combo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("ComboBox listener fired");
                    OnePara.setParaValue((String)combo.getSelectedItem());
                    OnePara.setShowName(true);
                    //System.out.print(combo.getSelectedItem());
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

            JTextArea ParaTextArea=new JTextArea(OneVarRangePara.getObjectName()+'\n'+OneVarRangePara.getNotes(), 3, 20);
            ParaTextArea.setFont(new Font("微软雅黑", Font.BOLD, 20));
            ParaTextArea.setForeground(Color.BLACK);
            //ParaTextArea.setEnabled(false);
            ParaTextArea.setLineWrap(true);
            LeftPanel.setLayout(new FlowLayout());
            LeftPanel.add(new JScrollPane(ParaTextArea));

            Box hBox=Box.createHorizontalBox();
            if(OneVarRangePara.IfHasStep()) {
                JLabel VarNameLabel=new JLabel("变量名称：");
                JLabel MinLabel=new JLabel("最小值：");
                JLabel StepLabel=new JLabel("步长：");
                JLabel MaxLabel=new JLabel("最大值：");
                JTextField VarNameTextField=new JTextField(5);
                JTextField MinTextField=new JTextField(5);
                JTextField StepTextField=new JTextField(5);
                JTextField MaxTextField=new JTextField(5);

                Document VarNameDoc=new DefaultStyledDocument();
                VarNameTextField.setDocument(VarNameDoc);
                VarNameDoc.addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarName(VarNameTextField.getText());
                        OneVarRangePara.setShowName(true);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarName(VarNameTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarName(VarNameTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }
                });

                Document MinDoc=new DefaultStyledDocument();
                MinTextField.setDocument(MinDoc);
                MinDoc.addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMin(MinTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMin(MinTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMin(MinTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }
                });

                Document StepDoc=new DefaultStyledDocument();
                StepTextField.setDocument(StepDoc);
                StepDoc.addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarStep(StepTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarStep(StepTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarStep(StepTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }
                });

                Document MaxDoc=new DefaultStyledDocument();
                MaxTextField.setDocument(MaxDoc);
                MaxDoc.addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMax(MaxTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMax(MaxTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMax(MaxTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }
                });

                hBox.add(VarNameLabel);
                hBox.add(VarNameTextField);
                hBox.add(Box.createVerticalStrut(5));
                hBox.add(MinLabel);
                hBox.add(MinTextField);
                hBox.add(Box.createVerticalStrut(5));
                hBox.add(StepLabel);
                hBox.add(StepTextField);
                hBox.add(Box.createVerticalStrut(5));
                hBox.add(MaxLabel);
                hBox.add(MaxTextField);
            }
            else {
                JLabel VarNameLabel=new JLabel("变量名称：");
                JLabel MinLabel=new JLabel("最小值：");
                JLabel MaxLabel=new JLabel("最大值：");
                JTextField VarNameTextField=new JTextField(10);
                JTextField MinTextField=new JTextField(10);
                JTextField MaxTextField=new JTextField(10);

                Document VarNameDoc=new DefaultStyledDocument();
                VarNameTextField.setDocument(VarNameDoc);
                VarNameDoc.addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarName(VarNameTextField.getText());
                        OneVarRangePara.setShowName(true);
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarName(VarNameTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarName(VarNameTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }
                });

                Document MinDoc=new DefaultStyledDocument();
                MinTextField.setDocument(MinDoc);
                MinDoc.addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMin(MinTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMin(MinTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMin(MinTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }
                });

                Document MaxDoc=new DefaultStyledDocument();
                MaxTextField.setDocument(MaxDoc);
                MaxDoc.addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMax(MaxTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMax(MaxTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        System.out.println("VarRangePara TextField listener fired");
                        OneVarRangePara.setVarMax(MaxTextField.getText());
                        OneVarRangePara.setShowName(true);

                    }
                });

                hBox.add(VarNameLabel);
                hBox.add(VarNameTextField);
                hBox.add(Box.createVerticalStrut(5));
                hBox.add(MinLabel);
                hBox.add(MinTextField);
                hBox.add(Box.createVerticalStrut(5));
                hBox.add(MaxLabel);
                hBox.add(MaxTextField);
            }
            RightPanel.add(hBox);

            AimPanel.setLayout(new GridLayout(0, 2));
            AimPanel.add(LeftPanel);    AimPanel.add(RightPanel);
            return;
        }

        ParaPanel(MathFunc InfoFunc){
            super();
            ChosenFunc = InfoFunc;

            JPanel FuncIntrPanel=new JPanel();
            JTextArea FuncIntrTextArea=new JTextArea(InfoFunc.getNotes(), 3, 45);
            FuncIntrTextArea.setFont(new Font("微软雅黑", Font.BOLD, 25));
            FuncIntrTextArea.setForeground(Color.BLACK);
            //FuncIntrTextArea.setEnabled(false);
            //something wrong here 20151211
            FuncIntrPanel.setLayout(new FlowLayout());
            //add(FuncIntrTextArea, "Center");
            FuncIntrPanel.add(new JScrollPane(FuncIntrTextArea));
            ArrayNum1=InfoFunc.getNumOfVarRangePara();
            ArrayNum2=InfoFunc.getNumOfPara();
            ArrayPanel1=new JPanel[ArrayNum1];
            ArrayPanel2=new JPanel[ArrayNum2];
            if (ArrayPanel1 == null) {
                System.out.print("ArrayPanel1 is null!\n");
            }

            JPanel MainFieldPanel=new JPanel();
            JPanel LeftMainFieldPanel=new JPanel(); JPanel RightMainFieldPanel=new JPanel();
            //JTextArea MainFieldTextArea=new JTextArea(InfoFunc.getMainField()+'\n'+InfoFunc.getMainFieldNote(), 3, 20);
            JTextArea MainFieldTextArea=new JTextArea("此处为待处理的函数或数据", 3, 20);
            MainFieldTextArea.setFont(new Font("微软雅黑", Font.BOLD, 20));
            MainFieldTextArea.setForeground(Color.BLACK);
            //MainFieldTextArea.setEnabled(false);
            MainFieldTextArea.setLineWrap(true);
            LeftMainFieldPanel.setLayout(new FlowLayout());
            LeftMainFieldPanel.add(new JScrollPane(MainFieldTextArea));
            Box hBox=Box.createHorizontalBox();
            JLabel MainFieldLabel=new JLabel();
            JTextField MainFieldLabelTextField=new JTextField(15);

            Document MainFieldDoc=new DefaultStyledDocument();
            MainFieldLabelTextField.setDocument(MainFieldDoc);

            MainFieldDoc.addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    System.out.println("MainField listener fired");
                    InfoFunc.setMainField(MainFieldLabelTextField.getText());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    System.out.println("MainField listener fired");
                    InfoFunc.setMainField(MainFieldLabelTextField.getText());

                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    System.out.println("MainField listener fired");
                    InfoFunc.setMainField(MainFieldLabelTextField.getText());

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
                SetOnePanel(InfoFunc.getAllPara()[i], ArrayPanel2[i]);
            }

            Box vBox=Box.createVerticalBox();
            vBox.add(MainFieldPanel);
            vBox.add(Box.createVerticalStrut(10));

            if(ArrayPanel1.length !=0) {
                vBox.add(ArrayPanel1[0]);
                for (int i = 0; i < ArrayNum1 - 1; i++) {
                    //vBox.add(Box.createVerticalStrut(10));  //这里的10可以调整！
                    vBox.add(ArrayPanel1[i + 1]);
                }
            }

            vBox.add(Box.createVerticalStrut(10));

            if(ArrayPanel2.length!=0) {
                vBox.add(ArrayPanel2[0]);
                for (int i = 0; i < ArrayNum2 - 1; i++) {
                    //vBox.add(Box.createVerticalStrut(10));  //这里的10可以调整！
                    vBox.add(ArrayPanel2[i + 1]);
                }
            }


            CommitBotton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CGP.setCodeGeneText(InfoFunc.show());
                }
            });

            //vBox.add(Box.createGlue());
            //vBox.add(CommitBotton);

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


    CodeGenePanel CGP;
    ParaPanel PP;

    ParaAndCodePanel(MathFunc InfoFunc){
        super();

        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setOrientation(JSplitPane.VERTICAL_SPLIT);

        CGP = new CodeGenePanel();
        PP = new ParaPanel(InfoFunc);
        setTopComponent(PP);
        Box hBox=Box.createHorizontalBox();
        hBox.add(CGP);
        setBottomComponent(hBox);

        setDividerSize(3);
        //setDividerLocation(300);
        /*Box vBox=Box.createVerticalBox();
        vBox.add(PP);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(CGP);
        add(vBox);*/
    }

}
