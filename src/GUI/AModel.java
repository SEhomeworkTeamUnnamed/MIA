package GUI;

import javax.swing.*;

/**
 * Created by ’≈Àº‘¥ on 2015/12/11.
 */
public class AModel extends DefaultComboBoxModel{
    AModel(String[] s){
        for (int i = 0; i < s.length; i++) {
            addElement(s[i]);
        }
    }
}
