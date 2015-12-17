package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 张思源 on 2015/12/9.
 */
public class DefaultWelcomePane extends JPanel{
    public void add(Component c, GridBagConstraints constraints, int x, int y, int w, int h){
        constraints.gridx=x;
        constraints.gridy=y;
        constraints.gridwidth=w;
        constraints.gridheight=h;
        add(c, constraints);
    }

    DefaultWelcomePane(){
        setPreferredSize(new Dimension(450, 360));

        JLabel bgp=new JLabel();
        bgp.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\root\\picture\\welcome pic.png"));
        bgp.setText("欢迎使用 Mathematica Input Assistant!");
        bgp.setFont(new Font("微软雅黑", Font.BOLD, 30));
        bgp.setForeground(Color.BLACK);
        bgp.setHorizontalTextPosition(0);
        bgp.setVerticalTextPosition(0);

        /*JLabel BackgroundPic=new JLabel(new ImageIcon(System.getProperty("user.dir")+"\\root\\picture\\123.jpg"));

        JLabel title=new JLabel("欢迎使用MIA！");
        title.setFont(new Font("华文彩云", Font.BOLD, 30));
        title.setForeground(Color.BLUE);
        title.setOpaque(false);
        //title.setContentAreaFilled(false);*/

        //创建一个水平箱子，用glue把title推到中央
        /*Box hBox=Box.createHorizontalBox();
        hBox.add(Box.createGlue().setBackground(););
        hBox.add(title);
        hBox.add(Box.createGlue());*/
        //创建一个垂直箱子，用glue把title推到中央
        /*Box vBox=Box.createVerticalBox();
        vBox.add(Box.createGlue());
        vBox.add(hBox);
        vBox.add(Box.createGlue());*/

        setLayout(new BorderLayout());
        add(bgp, "Center");   //add(title);


        //GridBagLayout lay=new GridBagLayout();  //创建网格组布局方式对象
        //setLayout(lay);
        /*setLayout(new BorderLayout());

        JLabel title=new JLabel("欢迎使用MIA！");

        add(title, "Center");*/

        //初始化constraint
        /*GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        constraints.weightx=3;
        constraints.weighty=4;
        add(title, constraints, 0, 0, 20, 10);*/

    }
}
