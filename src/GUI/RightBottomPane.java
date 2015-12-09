package GUI;

import javax.swing.*;

/**
 * Created by IIIS on 11/7/2015.
 */
public class RightBottomPane extends JScrollPane{
    RightBottomPane(){
        super();

        /*DefaultWelcomePane DWP=new DefaultWelcomePane();
        setViewportView(DWP);*/
        String[] testString1 = new String[5];
        for (int i = 0; i < 5; i++) {
            testString1[i] = i+"";
        }
        ClassAndFuncPane CFP=new ClassAndFuncPane("hhh", testString1, testString1);
        setViewportView(CFP);
    }
}
