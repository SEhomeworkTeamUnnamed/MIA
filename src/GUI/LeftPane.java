package GUI;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Created by IIIS on 11/7/2015.
 */
public class LeftPane extends JScrollPane {

    LeftPane(JTree tree){
        super();

        setViewportView(tree);
    }
}
