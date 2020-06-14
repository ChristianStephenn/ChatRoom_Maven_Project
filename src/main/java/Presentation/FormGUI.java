package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class FormGUI {

    private static int avatarchoice=1;

    public FormGUI(){
        JPanel panel = new JPanel();
        JFrame frame = new JFrame("Chat Room");
        frame.setMinimumSize(new Dimension(1040, 680));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);


        JLabel avatar = new JLabel();
        Toolkit tt = Toolkit.getDefaultToolkit();
        Image ii = tt.getImage("./images/avatar/avatar1.jpeg");
        avatar.setIcon(new ImageIcon(ii));
        avatar.setBounds(500,0,100,100);
        panel.add(avatar);
        panel.setBackground(Color.white);



        //__________________________________________________________________________________________


        JRadioButton avatar1 = new JRadioButton("");
        avatar1.setMnemonic(KeyEvent.VK_B);
        avatar1.setSelected(true);

        JRadioButton avatar2 = new JRadioButton("");
        avatar2.setMnemonic(KeyEvent.VK_C);

        JRadioButton avatar3 = new JRadioButton("");
        avatar3.setMnemonic(KeyEvent.VK_D);

        JRadioButton avatar4 = new JRadioButton("");
        avatar4.setMnemonic(KeyEvent.VK_R);

        JRadioButton avatar5 = new JRadioButton("");
        avatar5.setMnemonic(KeyEvent.VK_P);

        JRadioButton avatar6 = new JRadioButton("");
        avatar6.setMnemonic(KeyEvent.VK_P);

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(avatar1);
        group.add(avatar2);
        group.add(avatar3);
        group.add(avatar4);
        group.add(avatar5);
        group.add(avatar6);

        //Register a listener for the radio buttons.
        avatar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image ii = tt.getImage("./images/avatar/avatar1.jpeg");
                avatar.setIcon(new ImageIcon(ii));
                avatar.setBounds(500,0,100,100);
                panel.add(avatar);
                panel.setBackground(Color.white);
                avatarchoice=1;

            }
        });
        avatar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image ii = tt.getImage("./images/avatar/avatar2.jpeg");
                avatar.setIcon(new ImageIcon(ii));
                avatar.setBounds(500,0,100,100);
                panel.add(avatar);
                panel.setBackground(Color.white);
                avatarchoice=2;

            }
        });
        avatar3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image ii = tt.getImage("./images/avatar/avatar3.jpeg");
                avatar.setIcon(new ImageIcon(ii));
                avatar.setBounds(500,0,100,100);
                panel.add(avatar);
                panel.setBackground(Color.white);
                avatarchoice=3;

            }
        });
        avatar4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image ii = tt.getImage("./images/avatar/avatar4.jpeg");
                avatar.setIcon(new ImageIcon(ii));
                avatar.setBounds(500,0,100,100);
                panel.add(avatar);
                panel.setBackground(Color.white);
                avatarchoice=4;

            }
        });
        avatar5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image ii = tt.getImage("./images/avatar/avatar5.jpeg");
                avatar.setIcon(new ImageIcon(ii));
                avatar.setBounds(500,0,100,100);
                panel.add(avatar);
                panel.setBackground(Color.white);
                avatarchoice=5;

            }
        });

        avatar6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image ii = tt.getImage("./images/avatar/avatar6.jpeg");
                avatar.setIcon(new ImageIcon(ii));
                avatar.setBounds(500,0,100,100);
                panel.add(avatar);
                panel.setBackground(Color.white);

            }
        });
        avatar1.setBounds(400,100,40,40);
        avatar2.setBounds(450,100,40,40);
        avatar3.setBounds(500,100,40,40);
        avatar4.setBounds(550,100,40,40);
        avatar5.setBounds(600,100,40,40);
        avatar6.setBounds(650,100,40,40);
        avatar1.setBackground(Color.white);
        avatar2.setBackground(Color.white);
        avatar3.setBackground(Color.white);
        avatar4.setBackground(Color.white);
        avatar5.setBackground(Color.white);
        avatar6.setBackground(Color.white);

        panel.add(avatar1);
        panel.add(avatar2);
        panel.add(avatar3);
        panel.add(avatar4);
        panel.add(avatar5);
        panel.add(avatar6);










        //___________________________________________________________________________________________

        JLabel labela = new JLabel("^ Pick your avatar above ^");
        labela.setBounds(460,150,165,25);
        panel.add(labela);




        JLabel label = new JLabel("Username");
        label.setBounds(375,200,80,25);
        panel.add(label);

        JLabel label2 = new JLabel("Password");
        label2.setBounds(375,275,80,25);
        panel.add(label2);

        JLabel label3 = new JLabel("Confirm password");
        label3.setBounds(350,350,125,25);
        panel.add(label3);

        JLabel label4 = new JLabel("");
        label4.setBounds(450,420,300,25);
        panel.add(label4);

        JTextField userfield = new JTextField();
        userfield.setBounds(460,200,165,25);
        panel.add(userfield);

        JPasswordField pwdfield = new JPasswordField();
        pwdfield.setBounds(460,275,165,25);
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
