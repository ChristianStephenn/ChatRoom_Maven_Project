import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{

    private JPanel discussion_panel = new JPanel();
    private Border lineborder = BorderFactory.createLoweredBevelBorder();
    private Border RaisedBevelBorder = BorderFactory.createLineBorder(new Color(253, 157, 179, 255));
    private Border textBar_border = BorderFactory.createCompoundBorder(lineborder, RaisedBevelBorder);

    private Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
    private Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder();

    private JTextField write_text_field = new JTextField("Taper un message");
    private JLabel showText_label = new JLabel();
    private JPanel preShowText_panel = new JPanel();
    private JPanel messageBar_panel = new JPanel();
    private  JButton enterButton = new JButton();

    private JPanel jPanel = new JPanel();

    private ImageIcon img = new ImageIcon("./image/Logo.PNG");
    private JLabel image_label = new JLabel(img);

    private Font font = new Font("Comic sans MS", Font.BOLD | Font.ITALIC, 10);

    private JPanel users = new JPanel();

    private JLabel jLabel = new JLabel("oui");
    private JLabel jLabel1 = new JLabel("non");
    private JLabel jLabel2 = new JLabel("tg");


    public GUI() {
        ImageIcon img_button = new ImageIcon("./image/coussinet_button.PNG");
        JFrame frame = new JFrame("Chat Room");
        JPanel panel = new JPanel();


        frame.setMinimumSize(new Dimension(1040, 680));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(img.getImage());
        frame.pack();

        panel.setLayout(null);
        panel.setBackground(new Color(66,47,108, 255));


        /* création de la première case de discussion et ajout de son contenu*/

        users.setBorder(BorderFactory.createTitledBorder( loweredBevelBorder,"Utilisateurs en ligne", TitledBorder.CENTER, TitledBorder.TOP, new Font("comic sans ms",Font.BOLD|Font.ITALIC,18), new Color(238, 156, 58, 255)));
        discussion_panel.setBorder(BorderFactory.createTitledBorder( raisedBevelBorder,"Chat", TitledBorder.LEFT, TitledBorder.TOP, new Font("comic sans ms",Font.BOLD|Font.ITALIC,15), new Color(62, 241, 101, 255)));

        panel.add(image_label);
        image_label.setBounds(45,10, 200,250);
        panel.add(users);
        users.setLayout(new FlowLayout(FlowLayout.LEFT));
        users.setBounds(5,260, 290,380);
        users.setBackground(new Color(66,47,108, 255));

        /* création de la seconde case de discussion et ajout de son contenu */

        panel.add(discussion_panel);
        discussion_panel.setBackground(new Color(66,47,108, 255));
        discussion_panel.setBounds(300,3,720,635);
        discussion_panel.setLayout(null);

        /* création de nouvelles cases dans le panel discussion */

        discussion_panel.add(preShowText_panel);
        preShowText_panel.add(showText_label);
        preShowText_panel.setLayout(null);
        preShowText_panel.setBackground(new Color(58, 234, 20, 255));
        preShowText_panel.setBounds(10,25,700,560);

        //showText_label.setBounds(10,15,100,90);
        showText_label.setFont(font);
        showText_label.setForeground(new Color(7, 175, 226, 255));
        showText_label.setMinimumSize(new Dimension(300,20));

        /* tout ce qui concerne la barre d'écriture */

        discussion_panel.add(messageBar_panel);
        messageBar_panel.setLayout(null);
        messageBar_panel.add(write_text_field);
        messageBar_panel.add(enterButton);
        messageBar_panel.setBackground(new Color(84, 61, 135, 255));
        messageBar_panel.setBounds(3,588,715,43);
        messageBar_panel.setBorder(BorderFactory.createRaisedBevelBorder());
        write_text_field.setBounds(12,9,640,25);
        write_text_field.setBackground(new Color(253, 157, 179, 255));
        write_text_field.setBorder(textBar_border);
        write_text_field.setPreferredSize(new Dimension(600,20));
        write_text_field.setFont(font);
        enterButton.setBackground(null);
        enterButton.setBorder(BorderFactory.createRaisedBevelBorder());
        enterButton.setIcon(img_button);
        enterButton.setBounds(670,7,30,30);

        /*JScrollPane scrollPane = new JScrollPane(jPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);*/
        //preShowText_panel.add(scrollPane);

        enterButton.addActionListener(this);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent arg0) {
        showText_label.setText(write_text_field.getText());
    }
    public static void main(String[] args) {
        new GUI();
    }
}
