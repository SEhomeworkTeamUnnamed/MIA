package GUI;

import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * Created by IIIS on 11/7/2015.
 */
public class MainPane extends JSplitPane{
    LeftPane leftPane;
    RightPane rightPane;

    MainPane(JTree tree, FuncClass RootClass){
        super();
        leftPane = new LeftPane(tree);
        rightPane = new RightPane(tree,RootClass);

        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setPreferredSize(new Dimension(600, 400));
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        setLeftComponent(leftPane);
        setRightComponent(rightPane);

        setDividerSize(3);
        setDividerLocation(150);
    }
}
