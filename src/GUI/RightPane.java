package GUI;

import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * Created by IIIS on 11/7/2015.
 */
public class RightPane extends JSplitPane{
    RightPane(DefaultTreeModel treeModel, JTree tree, FuncClass RootClass){
        super();

        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setPreferredSize(new Dimension(447, 400));
        setOrientation(JSplitPane.VERTICAL_SPLIT);

        setTopComponent(new RightTopPane(treeModel,tree,RootClass));
        setBottomComponent(new RightBottomPane());

        setDividerSize(3);
        setDividerLocation(40);
    }

}
