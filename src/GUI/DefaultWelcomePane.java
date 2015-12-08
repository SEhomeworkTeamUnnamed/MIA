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

        GridBagLayout lay=new GridBagLayout();  //创建网格组布局方式对象
        setLayout(lay);

        JLabel title=new JLabel("欢迎使用MIA！");

        //初始化constraint
        GridBagConstraints constraints=new GridBagConstraints();
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        constraints.weightx=3;
        constraints.weighty=4;
        add(title, constraints, 0, 0, 20, 10);

    }
}
