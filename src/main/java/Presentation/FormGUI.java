package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class FormGUI {

    public FormGUI(){
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Chat Room");
        frame.setMinimumSize(new Dimension(1040, 680));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Username");
        label.setBounds(375,150,80,25);
        panel.add(label);

        JLabel label2 = new JLabel("Password");
        label2.setBounds(375,250,80,25);
        panel.add(label2);

        JLabel label3 = new JLabel("Confirm password");
        label3.setBounds(325,350,125,25);
        panel.add(label3);

        JLabel label4 = new JLabel("");
        label4.setBounds(450,420,300,25);
        panel.add(label4);

        JTextField userfield = new JTextField();
        userfield.setBounds(460,150,165,25);
        panel.add(userfield);

        JPasswordField pwdfield = new JPasswordField();
        pwdfield.setBounds(460,250,165,25);
        panel.add(pwdfield);

        JPasswordField confpwdfield = new JPasswordField();
        confpwdfield.setBounds(460,350,165,25);
        panel.add(confpwdfield);

        JButton button = new JButton("register");
        button.setBounds(490,470,80,25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!userfield.getText().trim().equals("")
                    && pwdfield.getText().equals(confpwdfield.getText())
                    && pwdfield.getText().length()>=8) {

                    label4.setText("Account successfuly created");
                    //enregistrement des données en XML à faire

                }else{
                    if (userfield.getText().equals("")){
                        label4.setText("Please choose a username");
                    }
                    else if (pwdfield.getText().equals("")) {
                        label4.setText("Please choose a password");
                    }
                    else if (!pwdfield.getText().equals(confpwdfield.getText().trim())){
                        label4.setText("The passwords must match");
                    }
                    else if (pwdfield.getText().length()<8){
                        label4.setText("Your password must be at least 8 character long");
                    }
                }
            }
        });
        panel.add(button);

        JButton button2 = new JButton("back");
        button2.setBounds(450,520,160,25);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
                frame.dispose();
                new LoginGUI();
            }
        });
        panel.add(button2);
        frame.getRootPane().setDefaultButton(button);

        JLabel logo = new JLabel();
        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("./images/bg.png");
        logo.setIcon(new ImageIcon(i));
        logo.setBounds(0,0,321,250);
        panel.add(logo);
        panel.setBackground(Color.white);
        frame.pack();
        frame.setVisible(true);



    }



}
