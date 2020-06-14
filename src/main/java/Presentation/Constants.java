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
    
    public static String TITLE = "Chat Room";
    public static String LOGO_DIRECTORY = "./image/Logo.PNG";
    public static String BUTTON_DIRECTORY = "./image/coussinet_button.PNG";
    public static String USERS = "Utilisateurs en ligne";
    public static String CHAT = "Chat";
    public static String WRITE = "Taper un message";

    public static Color mainColor = new Color(16, 15, 45, 255);
    public static Color barExtColor = new Color(24, 17, 61, 255);
    public static Color barIntColor = new Color(253, 157, 179, 255);
    public static Color colorText = new Color(136, 82, 238, 255);
    public static Color userTitleColor = new Color(238, 141, 25, 255);
    public static Color chatTitleColor = new Color(11, 226, 113, 255);

    public static Border lineborder = BorderFactory.createLoweredBevelBorder();
    public static Border RaisedBevelBorder = BorderFactory.createLineBorder(barIntColor);
    public static Border textBar_border = BorderFactory.createCompoundBorder(lineborder, RaisedBevelBorder);

    public static Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
    public static Border loweredBevelBorder = BorderFactory.createLoweredBevelBorder();

    public static Font font = new Font("Comic sans MS", Font.BOLD | Font.ITALIC, 10);
    public static Font font2 = new Font("Comic sans MS",Font.PLAIN, 12);
    public static Font userTitleFont =  new Font("comic sans ms",Font.BOLD|Font.ITALIC,18);
    public static Font chatTitleFont =  new Font("comic sans ms",Font.BOLD|Font.ITALIC,15);

   
}
