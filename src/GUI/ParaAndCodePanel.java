package GUI;

import MathFunc.MathFunc;
import MathFunc.Para;

import javax.swing.*;

/**
 * Created by ’≈Àº‘¥ on 2015/12/10.
 */
public class ParaAndCodePanel extends JSplitPane{
    CodeGenePanel CGP;
    ParaPanel PP;
    ParaAndCodePanel(MathFunc InfoFunc){
        super();

        setOneTouchExpandable(true);
        setContinuousLayout(true);
        setOrientation(JSplitPane.VERTICAL_SPLIT);

        CGP = new CodeGenePanel();
        setTopComponent(CGP);
        PP = new ParaPanel(InfoFunc);
        setBottomComponent(PP);

        setDividerSize(3);
        setDividerLocation(100);
    }

}
