package GUI.GUIsmall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by 张思源 on 2015/12/8.
 */
public class TempFrame extends JFrame{
    /*这是一个承载以后会加入类似RightBottomPane的Frame，作为测试准运行的Pane用*/

    TempFrame(String FrameName){

        super(FrameName);

        TempPane TempPane1=new TempPane();
        getContentPane().add(TempPane1);

        pack();
        setVisible(true);

        //设置Frame位置
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize=kit.getScreenSize();
        setLocation(screenSize.width/4,screenSize.height/4);

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
        new TempFrame("little MIA");
        }

}
