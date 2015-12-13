package GUI;

import MathFunc.FuncClass;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**
 * Created by IIIS on 11/7/2015.
 */
public class RightPane extends JSplitPane{
    RightTopPane rightTopPane;
    RightBottomPane rightBottomPane;

    RightPane(JTree tree){
        super();
        rightTopPane = new RightTopPane(tree);
        rightBottomPane = new RightBottomPane(tree);

        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setPreferredSize(new Dimension(447, 400));
        setOrientation(JSplitPane.VERTICAL_SPLIT);

        setTopComponent(rightTopPane);
        setBottomComponent(rightBottomPane);

        setDividerSize(3);
        setDividerLocation(40);
    }

}
