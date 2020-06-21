package Presentation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Constants {
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final String XMLMESSAGES_INPUT_FILE = "src/main/java/messages.xml";
    public static final String XMLMESSAGES_OUTPUT_FILE = "src/main/java/messages.xml";
    public static final String HOSTIP = "localhost";
    public static final String REGEX = "/-reg@#@#-/";
    
    public static String TITLE = "Chat Room";
    public static String LOGO_DIRECTORY = "./image/Logo.PNG";
    public static String BUTTON_DIRECTORY = "./image/coussinet_button.PNG";
    public static String BIN_BUTTON_DIRECTORY = "./image/poubox.PNG";
    public static String BIN_BUTTON_DIRECTORY2 = "./image/poubix.PNG";
    public static String USERS = "Online users";
    public static String CHAT = "Chat";
    public static String WRITE = "Write a message";
    public static String BIN_MESSAGE = "Click me to delete the conversation";
    public static String BIN_MESSAGE2 = "Conversation deleted !";

    public static Color mainColor = new Color(189, 158, 232, 255);
    public static Color barExtColor = new Color(185, 138, 243, 255);
    public static Color barIntColor = new Color(253, 157, 179, 255);
    public static Color colorText = new Color(62, 49, 83, 255);
    public static Color userTitleColor = new Color(54, 26, 130, 255);
    public static Color chatTitleColor = new Color(96, 37, 234, 255);

    public static Border lineborder = BorderFactory.createLoweredBevelBorder();
    public static Border RaisedBevelBorder = BorderFactory.createLineBorder(barIntColor);
    public static Border textBar_border = BorderFactory.createCompoundBorder(lineborder, RaisedBevelBorder);

    public static Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
    public static Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder();

    public static Font font = new Font("Comic sans MS", Font.BOLD | Font.ITALIC, 13);
    public static Font font2 = new Font("Comic sans MS",Font.BOLD, 15);
    public static Font font3 = new Font("Comic sans MS", Font.ITALIC, 10);
    public static Font userTitleFont =  new Font("comic sans ms",Font.BOLD|Font.ITALIC,18);
    public static Font chatTitleFont =  new Font("comic sans ms",Font.BOLD|Font.ITALIC,15);

   
}
