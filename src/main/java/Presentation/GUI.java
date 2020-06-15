package Presentation;

import Presentation.Controller.SimpleUser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Scanner;

public class GUI implements ActionListener{

    private JPanel users = new JPanel();

    private ImageIcon img = new ImageIcon(Constants.LOGO_DIRECTORY);
    private JLabel image_label = new JLabel(img);

    private JPanel discussion_panel = new JPanel();

    private JPanel messageBar_panel = new JPanel();
    private JButton enterButton = new JButton();
    private ImageIcon img_button = new ImageIcon(Constants.BUTTON_DIRECTORY);
    private JTextField write_text_field = new JTextField(Constants.WRITE);

    private static JLabel showText_label = new JLabel();
    private JPanel preShowText_panel = new JPanel();

    private SimpleUser simpleUser;

    private static String messagesHTML = "<html>";

    public GUI() {
        initGUI();
        simpleUser = new SimpleUser();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        simpleUser.connect(name, "localhost");
    }

    public void initGUI(){
        JFrame frame = new JFrame(Constants.TITLE);
        JPanel panel = new JPanel();

        frame.setMinimumSize(new Dimension(1040, 680));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setDefaultCloseOperation();

        frame.setIconImage(img.getImage());
        frame.setResizable(false);
        frame.pack();

        panel.setLayout(null);
        panel.setBackground(Constants.mainColor);
        panel.add(image_label);
        panel.add(users);
        panel.add(discussion_panel);

        users.setBorder(BorderFactory.createTitledBorder(Constants.loweredBevelBorder,Constants.USERS, TitledBorder.CENTER, TitledBorder.TOP, Constants.userTitleFont, Constants.userTitleColor));
        users.setLayout(new FlowLayout(FlowLayout.LEFT));
        users.setBounds(5,260, 290,380);
        users.setBackground(Constants.mainColor);

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
                //printMessages();
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
                //printMessages();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        /*JScrollPane scrollPane = new JScrollPane(jPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);*/
        //preShowText_panel.add(scrollPane);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void printMessages() {
        messagesHTML += write_text_field.getText() + "<br>";
        //showText_label.setText(messagesHTML + "</html>");
        write_text_field.setText("");
    }

    public static void printGui(String message){
        messagesHTML += message + "<br>";
        showText_label.setText(messagesHTML + "</html>");
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
