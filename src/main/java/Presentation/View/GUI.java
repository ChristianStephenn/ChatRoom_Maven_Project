package Presentation.View;

import Data.Constants;
import Presentation.Controller.SimpleUser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
* <b>GUI est la classe qui permet afficher l'interface graphique du ChatRoom</b>
*/
public class GUI implements ActionListener{

    private final JPanel users = new JPanel();
    private static final JLabel showUser = new JLabel();
    
    private final JButton deleteButton = new JButton();
    private final ImageIcon img_bin_button = new ImageIcon(Constants.BIN_BUTTON_DIRECTORY);
    private final ImageIcon img_bin_button2 = new ImageIcon(Constants.BIN_BUTTON_DIRECTORY2);
    private final JLabel bin_text = new JLabel(Constants.BIN_MESSAGE);
    
    private final ImageIcon imgp = new ImageIcon(Constants.PAINT_DIRECTORY);
    private final JButton paintButton = new JButton();
    
    private final ImageIcon img = new ImageIcon(Constants.LOGO_DIRECTORY);
    private final JLabel image_label = new JLabel(img);

    private final JPanel discussion_panel = new JPanel();

    private final JPanel messageBar_panel = new JPanel();
    private final JButton enterButton = new JButton();
    private final ImageIcon img_button = new ImageIcon(Constants.BUTTON_DIRECTORY);
    private final JTextField write_text_field = new JTextField(Constants.WRITE);

    private static final JLabel showText_label = new JLabel();
    private final JPanel preShowText_panel = new JPanel();

    private final SimpleUser simpleUser;

    private static String messagesHTML = "<html>";
    private static String onlineUsersHTML = "<html>";
    
/**
* Constructeur GUI
*
* @see SimpleUser
*/
    public GUI() {
        String name = JOptionPane.showInputDialog("Enter name");
        initGUI();
        simpleUser = new SimpleUser();
        simpleUser.connect(name);
    }

    /**
    * Constructeur GUI surchargé
    *
    * @param name
    *             Nom de l'utilisateur
    * @see SimpleUser
    */
    public GUI(String name) { // login GUI
        initGUI();
        simpleUser = new SimpleUser();
        simpleUser.connect(name);
    }

    /**
    * Initialise le GUI
    *
    * @see SimpleUser
    */
    public void initGUI(){
        JFrame frame = new JFrame(Constants.TITLE);
        JPanel panel = new JPanel();

        frame.setMinimumSize(new Dimension(1040, 680));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e){
                try {
                    SimpleUser.logout();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        frame.setIconImage(img.getImage());
        frame.setResizable(false);

        panel.setLayout(null);
        panel.setBackground(Constants.mainColor);
        panel.add(image_label);
        panel.add(users);
        panel.add(discussion_panel);
        panel.add(deleteButton);
        panel.add(bin_text);
        panel.add(paintButton);

        image_label.setBounds(45,10, 200,250);

        users.setBorder(BorderFactory.createTitledBorder(Constants.loweredBevelBorder,Constants.USERS, TitledBorder.CENTER, TitledBorder.TOP, Constants.userTitleFont, Constants.userTitleColor));
        users.setLayout(new FlowLayout(FlowLayout.LEFT));
        users.setBounds(5,260, 290,340);
        users.setBackground(Constants.mainColor);
        users.add(showUser);
        
        showUser.setFont(Constants.fontUser);
        showUser.setForeground(Constants.userTitleColorColor);
        
        bin_text.setBounds(60,602,200,30);
        bin_text.setFont(Constants.font3);
        
        paintButton.setBounds(260,220,40,40);
        paintButton.setIcon(imgp);

        deleteButton.setBounds(20,602,30,30);
        deleteButton.setIcon(img_bin_button);
        deleteButton.setBorder(BorderFactory.createRaisedBevelBorder());
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                deleteButton.setIcon(img_bin_button2);
                try {
                    SimpleUser.send("Delete_Messages");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                deleteButton.setIcon(img_bin_button);
                bin_text.setText(Constants.BIN_MESSAGE);
            }
        });

        discussion_panel.setBorder(BorderFactory.createTitledBorder(Constants.raisedBevelBorder,Constants.CHAT, TitledBorder.LEFT, TitledBorder.TOP, Constants.chatTitleFont, Constants.chatTitleColor));
        discussion_panel.setBackground(Constants.mainColor);
        discussion_panel.setBounds(300,3,720,635);
        discussion_panel.setLayout(null);
        discussion_panel.add(preShowText_panel);
        discussion_panel.add(messageBar_panel);

        preShowText_panel.add(showText_label);
        preShowText_panel.setLayout(new BoxLayout(preShowText_panel, BoxLayout.Y_AXIS));
        preShowText_panel.setBackground(Constants.mainColor);
        preShowText_panel.setBorder(Constants.loweredBevelBorder);
        preShowText_panel.setBounds(10,25,700,560);
        showText_label.setFont(Constants.font2);
        showText_label.setForeground(Constants.colorText);
        showText_label.setMinimumSize(new Dimension(300,20));

        messageBar_panel.setLayout(null);
        messageBar_panel.add(write_text_field);
        messageBar_panel.add(enterButton);
        messageBar_panel.setBackground(Constants.barExtColor);
        messageBar_panel.setBounds(3,588,715,43);
        messageBar_panel.setBorder(BorderFactory.createRaisedBevelBorder());

        write_text_field.setBounds(12,9,640,25);
        write_text_field.setBackground(Constants.barIntColor);
        write_text_field.setBorder(Constants.textBar_border);
        write_text_field.setPreferredSize(new Dimension(600,20));
        write_text_field.setFont(Constants.font);
        write_text_field.addActionListener(e -> {
            try {
                SimpleUser.send(SimpleUser.getName() + " : " + write_text_field.getText());
                write_text_field.setText("");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        write_text_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                write_text_field.setText("");
            }
        });
        
        enterButton.setBackground(null);
        enterButton.setBorder(BorderFactory.createRaisedBevelBorder());
        enterButton.setIcon(img_button);
        enterButton.setBounds(670,7,30,30);
        enterButton.addActionListener(e -> {
            try {
                SimpleUser.send(SimpleUser.getName() + " : " + write_text_field.getText());
                write_text_field.setText("");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Envoie un message sur la zone de discussion
     *
     * @param message
     *              Message saisie
     */
    public static void printGui(String message){
        messagesHTML += message + "<br>";
        showText_label.setText(messagesHTML + "</html>");
    }

    /**
    * Rafraichit les utilisateurs en ligne
    *
    * @param name
    *              Nom de l'utilisateur
    */
    public static void refreshOnlineUsers(String name) {
        onlineUsersHTML += name + "<br>";
        showUser.setText(onlineUsersHTML + "</html>");
    }

    /**
    * Rafraichit la discussion
    */
    public static void refreshGUI() {
        showText_label.setText("");
        messagesHTML = "<html>";
    }

    /**
    * Rafraichit la zone des utilisateurs en ligne lorsqu'il y a une déconnexion
     *
     * @param name
     *          nom 
    */
    public static void refreshLogout(String name){
        String[] splitmess = onlineUsersHTML.split("<br>");
        onlineUsersHTML = "<html>";
        for (String username : splitmess) {
            if(!username.contains(name)){
                if(username.contains("<html>")){
                    onlineUsersHTML += username.substring(6) + "<br>";
                }else {
                    onlineUsersHTML += username + "<br>";
                }
            }
        }
        showUser.setText(onlineUsersHTML + "</html>");
    }

    /**
    * Affiche le GUI
    *
    * @param args
    *                 parametre du main
    */
    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
