package GUITest;

/**
 * Created by jwt625 on 12/9/2015.
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class JSplitPaneTest extends JFrame {

    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JSplitPaneTest frame = new JSplitPaneTest();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JSplitPaneTest() {
        //将样式设为windows样式
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 450);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        JPanel j1 = new JPanel();
        j1.setBackground(Color.PINK);
        JPanel j2 = new JPanel();
        j2.setBackground(Color.CYAN);
        JPanel j3 = new JPanel();
        j3.setBackground(Color.ORANGE);
        // JSplitPane.HORIZONTAL_SPLIT 为水平分割
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,false,j1,j2);
        j1.setLayout(new BorderLayout(0, 0));

        JLabel lblJ_2 = new JLabel("J1");
        lblJ_2.setFont(new Font("Arial Black", Font.PLAIN, 14));
        lblJ_2.setHorizontalAlignment(SwingConstants.CENTER);
        j1.add(lblJ_2, BorderLayout.CENTER);
        j2.setLayout(new BorderLayout(0, 0));

        JLabel lblJ_1 = new JLabel("J2");
        lblJ_1.setFont(new Font("Arial Black", Font.PLAIN, 14));
        lblJ_1.setHorizontalAlignment(SwingConstants.CENTER);
        j2.add(lblJ_1, BorderLayout.CENTER);
        splitPane.setOneTouchExpandable(true);
        //设置分隔条的位置为 JSplitPane 大小的一个百分比。
        splitPane.setDividerLocation(220);

        // JSplitPane.VERTICAL_SPLIT 为垂直分割
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,false,j3,splitPane);
        j3.setLayout(new BorderLayout(0, 0));

        JLabel lblJ = new JLabel("J3");
        lblJ.setFont(new Font("Arial Black", Font.PLAIN, 14));
        lblJ.setHorizontalAlignment(SwingConstants.CENTER);
        j3.add(lblJ, BorderLayout.CENTER);
        splitPane2.setDividerLocation(50);

        contentPane.add(splitPane2, BorderLayout.CENTER);
    }

}
