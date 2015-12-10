package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ’≈Àº‘¥ on 2015/12/10.
 */
public class CodeGenePanel extends JPanel{
    CodeGenePanel(){
        JTextArea CodeGeneTextArea=new JTextArea(5, 40);
        setLayout(new BorderLayout());
        add(CodeGeneTextArea, "Center");
    }
}
