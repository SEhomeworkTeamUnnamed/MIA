package GUITest;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by jwt625 on 12/9/2015.
 */
public class PopupFrameTest extends JFrame {
    class RecursiveSplitPane extends JSplitPane{
        int DividerLocation;
        RecursiveSplitPane(Component topPane, Component botPane, int num){
            DividerLocation = (110+40*(num-1))*num/(num+1);
            System.out.println("num: "+num+"\ndivider location(in construction): "+DividerLocation);
            System.out.println("pane len(in construction): "+(110+40*num));

            setOneTouchExpandable(true);
            setContinuousLayout(true);
            setLayout(new GridLayout(1, 1));
            setPreferredSize(new Dimension(270, 110+40*num));
            setOrientation(JSplitPane.VERTICAL_SPLIT);
            setTopComponent(topPane);
            setBottomComponent(botPane);
            setDividerSize(3);
            setDividerLocation(DividerLocation);
        }
        RecursiveSplitPane addBotPane(Component botPane, int num){
            JSplitPane jSplitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this, this);
            jSplitPane.setDividerLocation(1);
            jSplitPane.setLayout(new BorderLayout(0,0));
            RecursiveSplitPane Pane=new RecursiveSplitPane(this, botPane, num);
            return Pane;
            //setPreferredSize(new Dimension(270, 110+50*num));
            //DividerLocation = 100*num/(num+1);
            //System.out.println("divider location(in add): "+DividerLocation);
            //System.out.println("pane len(in add): "+(110+50*num));
            //setTopComponent(topPane);
            //setBottomComponent(botPane);
            //setDividerSize(3);
            //setDividerLocation(DividerLocation);
        }
    }

    PopupFrameTest(){
        super();
        ////将样式设为windows样式
        //try {
        //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        //} catch (Exception ex) {
        //    ex.printStackTrace();
        //}

        setSize(270,220);
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        setLocation(screenSize.width/4,screenSize.height/4);

        JLabel jLabel1=new JLabel("请输入");
        JLabel jLabel2=new JLabel("请输入");
        JTextField jTextField=new JTextField(10);
        JTextArea jTextArea=new JTextArea();
        JButton jButtonAffirm=new JButton("确认");
        JButton jButtonCancel=new JButton("取消");

        JPanel upPane=new JPanel();
        JPanel botPane=new JPanel();
        upPane.setLayout(new GridLayout(4,1));
        upPane.add(jLabel1);
        upPane.add(jTextField);
        upPane.add(jLabel2);
        upPane.add(jTextArea);

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

        RecursiveSplitPane recursiveSplitPane=new RecursiveSplitPane(upPane, botPane, 1);
        for (int i = 0; i < 0; i++) {
            recursiveSplitPane=recursiveSplitPane.addBotPane(botPane, i+2);
        }
        //System.out.println(((RecursiveSplitPane) recursiveSplitPane.getTopComponent()).getDividerLocation());
        //System.out.println(((RecursiveSplitPane) ((RecursiveSplitPane) recursiveSplitPane.getTopComponent()).getTopComponent()).getDividerLocation());
        JScrollPane jScrollPane=new JScrollPane();
        jScrollPane.setViewportView(recursiveSplitPane);

        JButton jButtonTest=new JButton("test");
        jScrollPane.add(jButtonTest);

        setContentPane(jScrollPane);

        setVisible(true);


        //jTextField.addActionListener(new ActionListener() {
        //    @Override
        //    public void actionPerformed(ActionEvent e) {
        //        UserInput=jTextField.getText();
        //    }
        //});

        Document doc=new DefaultStyledDocument();
        jTextField.setDocument(doc);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //int option =
                //        JOptionPane.showConfirmDialog(null, "是否保存更改?", "",
                //                JOptionPane.YES_NO_CANCEL_OPTION);
                //System.out.print(option);
                System.exit(0);
            }
        });
    }
    public static void main(String[] args){
        new PopupFrameTest();
    }
}
