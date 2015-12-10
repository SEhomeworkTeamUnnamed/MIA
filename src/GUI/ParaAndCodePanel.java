package GUI;

import MathFunc.MathFunc;
import MathFunc.Para;

import javax.swing.*;

/**
 * Created by ’≈Àº‘¥ on 2015/12/10.
 */
public class ParaAndCodePanel extends JSplitPane{
    ParaAndCodePanel(MathFunc InfoFunc){
        super();

        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setOrientation(JSplitPane.VERTICAL_SPLIT);

        setTopComponent(new CodeGenePanel());
        setBottomComponent(new ParaPanel(InfoFunc));

        setDividerSize(3);
        setDividerLocation(100);
    }

}
