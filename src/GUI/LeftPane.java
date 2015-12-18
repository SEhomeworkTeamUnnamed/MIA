package GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**Left panel of the main panel
 * Created by jwt625 on 11/7/2015.
 */
public class LeftPane extends JScrollPane {

    LeftPane(JTree tree){
        super();

        setViewportView(tree);
    }
}
