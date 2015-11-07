package GUI;

import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * Created by IIIS on 11/7/2015.
 */
public class MainPane extends JSplitPane{
    MainPane(DefaultTreeModel treeModel, JTree tree, FuncClass RootClass){
        super();

        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setPreferredSize(new Dimension(600, 400));
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        setLeftComponent(new LeftPane(tree));
        setRightComponent(new RightPane(treeModel,tree,RootClass));

        setDividerSize(3);
        setDividerLocation(150);
    }
}
