package GUI.GUIsmall;

import GUI.LeftPane;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ’≈Àº‘¥ on 2015/12/8.
 */
public class TempPane extends JSplitPane {
    TempPane(){

        super();

        setPreferredSize(new Dimension(450, 360));
        setOrientation(JSplitPane.HORIZONTAL_SPLIT);

        FuncAndFuncClassPanel Fp=new FuncAndFuncClassPanel();
        setLeftComponent(new TempRightBottomPane(Fp));

        setDividerSize(0);
        setDividerLocation(447);


    }
}
