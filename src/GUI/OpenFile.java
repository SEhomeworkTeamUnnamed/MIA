package GUI;

import javax.swing.*;

/**
 * Created by IIIS on 11/7/2015.
 */
public class OpenFile extends JFileChooser {
    OpenFile(String title) {
        super(System.getProperty("user.dir"));

        setDialogTitle(title);
        setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        setMultiSelectionEnabled(false);
    }

    OpenFile(String title, String path) {
        super(path);

        setDialogTitle(title);
        setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        setMultiSelectionEnabled(false);
    }
}
