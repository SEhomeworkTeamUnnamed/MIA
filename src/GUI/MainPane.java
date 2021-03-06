package GUI;

import MathFunc.*;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

/**Main panel of the GUI, comprised of a left panel and a right panel
 * Created by jwt625 on 11/7/2015.
 */
public class MainPane extends JSplitPane{
    LeftPane leftPane;
    RightBottomPane rightPane;

    MainPane(JTree tree){
        super();
        leftPane = new LeftPane(tree);
        rightPane = new RightBottomPane(tree);

        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setPreferredSize(new Dimension(780, 610));
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        setLeftComponent(leftPane);
        setRightComponent(rightPane);

        setDividerSize(3);
        setDividerLocation(180);
    }

    public void setClassAndFuncPane(FuncClass selectedFuncClass){
        rightPane.setClassAndFuncPane(selectedFuncClass);
    }

    public void setParaAndCodePane(MathFunc selectedMathFunc){
        rightPane.setParaAndCodePane(selectedMathFunc);
    }
}
