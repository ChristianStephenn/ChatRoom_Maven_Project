package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class LoginGUI extends JFrame {
    private JPanel loginPanel=new JPanel();

    public LoginGUI() {
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Chat Room");
        frame.setMinimumSize(new Dimension(1040, 680));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("User");
        label.setBounds(400,370,80,25);
        panel.add(label);

        JLabel label2 = new JLabel("Password");
        label2.setBounds(375,420,80,25);
        panel.add(label2);

        JTextField userfield = new JTextField();
        userfield.setBounds(460,370,165,25);
        panel.add(userfield);

        JPasswordField pwdfield = new JPasswordField();
        pwdfield.setBounds(460,420,165,25);
        panel.add(pwdfield);

        JButton button = new JButton("login");
        button.setBounds(490,470,80,25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!userfield.getText().trim().equals("")) {
                    frame.dispose();
                    //new GUI(userfield.getText());
                }
            }
        });
        panel.add(button);

        JButton button2 = new JButton("create account");
        button2.setBounds(450,520,160,25);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
                new FormGUI();
            }
        });
        panel.add(button2);


        JLabel logo = new JLabel();
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("./images/bg.png");
        logo.setIcon(new ImageIcon(i));
        logo.setBounds(410,70,321,250);
        panel.add(logo);


        panel.setBackground(Color.white);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    LoginGUI window = new LoginGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }


}
