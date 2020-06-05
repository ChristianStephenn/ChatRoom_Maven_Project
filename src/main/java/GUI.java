import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GUI {

    private Border lineborder = BorderFactory.createLoweredBevelBorder();
    private Border RaisedBevelBorder = BorderFactory.createLineBorder(new Color(253, 157, 179, 255));
    private Border textBar_border = BorderFactory.createCompoundBorder(lineborder, RaisedBevelBorder);

    private  Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();

    private JPanel discussion = new JPanel();
    private JTextField write_text = new JTextField("Entrer un message");
    private JLabel showText = new JLabel();
    private JPanel pre_showText = new JPanel();
    private JPanel messageBar = new JPanel();
    private  JButton enterButton = new JButton();

    private Font font = new Font("Comic sans MS", Font.BOLD | Font.ITALIC, 10);

    private JPanel info = new JPanel();
    private JPanel users = new JPanel();

    private  GridBagConstraints gc1 = new GridBagConstraints();
    private  GridBagConstraints gc2 = new GridBagConstraints();

    public GUI() {
        ImageIcon img = new ImageIcon("./image/logo_ChatRoom.PNG");
        ImageIcon img_button = new ImageIcon("./image/coussinet_button.PNG");
        JFrame frame = new JFrame("Chat Room");
        JPanel panel = new JPanel();


        frame.setMinimumSize(new Dimension(1040, 680));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(img.getImage());
        frame.pack();

        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(66,47,108, 255));


        gc1.insets = new Insets(5,4,8,5); //espace entre les cases

        /* création de la première case de discussion et ajout de son contenu*/

        users.setBorder(BorderFactory.createTitledBorder( raisedBevelBorder,"Utilisateurs en ligne", TitledBorder.CENTER, TitledBorder.TOP, new Font("comic sans ms",Font.BOLD|Font.ITALIC,18), new Color(238, 156, 58, 255)));
        discussion.setBorder(BorderFactory.createTitledBorder( raisedBevelBorder,"Chat", TitledBorder.LEFT, TitledBorder.TOP, new Font("comic sans ms",Font.BOLD|Font.ITALIC,15), new Color(62, 241, 101, 255)));


        gc1.fill = GridBagConstraints.NONE; //espace qu'occupe la case, ici par défaut
        gc1.anchor = GridBagConstraints.FIRST_LINE_START; //emplacement de la case, ici en haut à gauche
        gc1.weightx = 2;  //nombre de cases en abscisse
        gc1.weighty = 2;  //nombre de cases en ordonnée
        gc1.gridx = 0;      //position en abscisse
        gc1.gridy = 0;      //position en ordonnée
        gc1.ipadx = 100;    //largeur de la case
        gc1.ipady = 200;    //longueur de la case
        panel.add(info, gc1);
        info.setLayout(new GridLayout(2,1));
        info.add(new JLabel(new ImageIcon("./image/Logo.PNG")));
        info.setBackground(new Color(66,47,108, 255));
        info.add(users, gc2);
        users.setLayout(new FlowLayout(FlowLayout.LEFT));
        users.setBackground(new Color(66,47,108, 255));

        /* création de la seconde case de discussion et ajout de son contenu */

        gc1.gridx = 1;
        gc1.gridy = 0;
        gc1.fill = GridBagConstraints.VERTICAL;
        gc1.anchor = GridBagConstraints.CENTER;
        gc1.ipadx = 500;
        panel.add(discussion, gc1);
        discussion.setBackground(new Color(66,47,108, 255));
        discussion.setLayout(new GridBagLayout());

        /* création de nouvelles cases dans le panel discussion */

        gc2.weighty = 2;
        gc2.weightx = 2;

        gc2.gridy=0;
        gc2.gridx=0;
        gc2.ipadx = 200;
        gc2.ipady = 550;
        gc2.gridwidth = 2;
        gc2.anchor = GridBagConstraints.LINE_START;
        gc2.fill = GridBagConstraints.BOTH;
        gc2.anchor = GridBagConstraints.CENTER;
        discussion.add(pre_showText,gc2);
        pre_showText.add(showText);
        pre_showText.setBackground(new Color(66,47,108, 255));

        gc2.gridy=1;
        gc2.gridx=0;
        gc2.fill = GridBagConstraints.HORIZONTAL;
        gc2.ipady = 3;
        gc2.anchor = GridBagConstraints.LAST_LINE_END;
        discussion.add(messageBar,gc2);
        messageBar.add(write_text);
        messageBar.add(enterButton);
        messageBar.setBackground(new Color(84, 61, 135, 255));
        messageBar.setBorder(BorderFactory.createRaisedBevelBorder());
        write_text.setBackground(new Color(253, 157, 179, 255));
        write_text.setBorder(textBar_border);
        write_text.setPreferredSize(new Dimension(600,20));
        write_text.setFont(font);
        enterButton.setPreferredSize(new Dimension(30,30));
        enterButton.setBackground(null);
        enterButton.setBorder(BorderFactory.createRaisedBevelBorder());
        enterButton.setIcon(img_button);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }



    public static void main(String[] args) {
        new GUI();
    }
}
