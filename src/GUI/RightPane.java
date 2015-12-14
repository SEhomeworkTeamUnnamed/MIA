package GUI;

import MathFunc.*;

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
        rightBottomPane = new RightBottomPane(tree);
        rightTopPane = new RightTopPane(tree, rightBottomPane);

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
