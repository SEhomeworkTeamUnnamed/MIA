package GUI;

import javax.swing.*;

/**class for file opening, called when fileOpen JMenuItem is clicked
 * Created by jwt625 on 11/7/2015.
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
