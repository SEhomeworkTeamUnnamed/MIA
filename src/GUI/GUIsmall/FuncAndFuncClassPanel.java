package GUI.GUIsmall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 张思源 on 2015/12/9.
 */
public class FuncAndFuncClassPanel extends JSplitPane{
    FuncAndFuncClassPanel(){

        super();

        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setPreferredSize(new Dimension(450, 360));
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        JButton b1=new JButton("first");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //测试代码
        /*JButton b1=new JButton("first");
        JButton b2=new JButton("second");
        JButton b3=new JButton("third");
        JButton b4=new JButton("fourth");
        JButton b5=new JButton("fifth");
        JButton b6=new JButton("sixth");
        JButton b7=new JButton("seventh");
        b1.setEnabled(true);

        Container con=new Container();
        con.setLayout(new FlowLayout());
        con.add(b1);con.add(b2);con.add(b3);con.add(b4);con.add(b5);con.add(b6);con.add(b7);

        setLeftComponent(con);*/

        setDividerSize(3);
        setDividerLocation(200);

    }


}
