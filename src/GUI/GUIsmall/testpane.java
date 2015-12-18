package GUI.GUIsmall;

import GUI.LeftPane;
import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * Created by 张思源 on 2015/12/8.
 */
public class testpane extends JSplitPane{
    testpane(){
        super();

        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setPreferredSize(new Dimension(600, 400));
        setOrientation(JSplitPane.VERTICAL_SPLIT);

        JButton b1=new JButton("添加函数类");
        b1.setEnabled(true);

        Container con=new Container();
        con.setLayout(new FlowLayout());
        con.add(b1);

        setTopComponent(con);

        /*setLeftComponent(new LeftPane(tree));
        setRightComponent(new RightPane(treeModel,tree,RootClass));*/

        setDividerSize(3);
        setDividerLocation(150);
    }
}
