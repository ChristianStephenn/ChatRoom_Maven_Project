package Presentation.Controller;

import Presentation.Constants;
import Presentation.GUI;
import Presentation.Model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class UserThread extends Thread{
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Scanner scan;
    private String name;
    private int port;
    private Message message;

    public UserThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        String tmpString;
        scan = new Scanner(System.in);
        try {
            //create the streams that will handle the objects coming through the sockets
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());

            message = (Message) input.readObject();
            System.out.println(message.getText());
            GUI.printGui(message.getText());

        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {
                output.close();
                input.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public void send(String str) throws IOException {
        message = new Message(name, port, str, Constants.dateFormat.format(new Date()));
        output.writeObject(message);
    }
}
