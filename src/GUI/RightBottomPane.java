package GUI;

import javax.swing.*;

/**
 * Created by IIIS on 11/7/2015.
 */
public class RightBottomPane extends JScrollPane{
    RightBottomPane(){
        super();

        DefaultWelcomePane DWP=new DefaultWelcomePane();
        setViewportView(DWP);
    }
}
