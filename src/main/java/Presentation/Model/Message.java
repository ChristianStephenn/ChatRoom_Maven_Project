package Presentation.Model;

import java.io.Serializable;

public class Message implements Serializable {

    private String SenderName;
    private String Text;
    private String date;

    public Message(String senderName, String text, String date) {
        SenderName = senderName;
        Text = text;
        this.date = date;
    }

    public String getSenderName() {
        return SenderName;
    }

    public String getText() {
        return Text;
    }

    public String getDate() {
        return date;
    }
}
