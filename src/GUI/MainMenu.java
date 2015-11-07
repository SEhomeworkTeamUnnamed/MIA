package GUI;

import MathFunc.FuncClass;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IIIS on 11/7/2015.
 */
public class MainMenu extends JMenuBar {
    MainMenu(FuncClass RootClass){
        super();

        JMenu menu1=new JMenu("文件");
        JMenuItem item1=new JMenuItem("打开");
        JMenuItem item2=new JMenuItem("保存");
        menu1.add(item1);
        menu1.add(item2);
        add(menu1);

        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RootClass.outputFile();
            }
        });
    }
}
