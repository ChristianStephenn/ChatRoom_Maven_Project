import javax.swing.*;
import java.awt.*;

public class GUI {

    private JPanel users = new JPanel();
    private JPanel messageBar = new JPanel();
    private JPanel zone_showText = new JPanel();
    private JPanel logo = new JPanel();

    public GUI() {
        Toolkit t = Toolkit.getDefaultToolkit();
        ImageIcon img = new ImageIcon(t.getImage("./images/bg.png"));
        JFrame frame = new JFrame("Chat Room");
        JPanel panel = new JPanel();
        GridBagConstraints gc = new GridBagConstraints();

        frame.setMinimumSize(new Dimension(1040, 680));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(img.getImage());
        frame.pack();

        panel.setLayout(new GridBagLayout());
        //panel.setBackground(new Color(66,47,108, 255));

        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(5,2,2,2);
        gc.ipady = gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 0;
        gc.gridy = 0;
        panel.add(new JLabel(new ImageIcon(t.getImage("./images/bg.png"))),gc);
        
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

}
