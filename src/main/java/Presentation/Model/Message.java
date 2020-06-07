package Presentation.Model;

import java.io.Serializable;

public class Message implements Serializable {

    private String SenderName;
    private int port;
    private String Text;
    private String date;

    public Message(String senderName, int port, String text, String date) {
        this.SenderName = senderName;
        this.port = port;
        this.Text = text;
        this.date = date;
    }

    public int getPort() {
        return port;
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
