package Presentation.Controller;

import Presentation.Constants;
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

    public UserThread(Socket socket, String name, int port) {
        this.socket = socket;
        this.name = name;
        this.port = port;
    }

    public void run() {
        String tmpString;
        scan = new Scanner(System.in);
        try {
            //create the streams that will handle the objects coming through the sockets
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());
            tmpString = name + ": " + scan.nextLine();
            output = new ObjectOutputStream(socket.getOutputStream());
            send(tmpString);

            //User user = new User(3000,"jack");
            //output.writeObject(user);		//serialize and write the Student object to the stream

        } catch (IOException ex) {
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
