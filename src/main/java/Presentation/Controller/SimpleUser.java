package Presentation.Controller;

import Presentation.Model.User;
import org.w3c.dom.ls.LSInput;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleUser {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket socket;
    private Scanner scan;
    private String name;

    public void connect(String name, String ip) {
        this.name = name;
        int port = 6666;
        String str;
        try  {
            //create the socket; it is defined by an remote IP address (the address of the server) and a port number
            socket = new Socket(ip, port);

            //create the streams that will handle the objects coming and going through the sockets
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            String textToSend = new String(name + " is connected!");
            System.out.println("text sent to the server: " + textToSend);
            output.writeObject(textToSend);		//serialize and write the String to the stream

            //User user = (User) input.readObject();	//deserialize and read the Student object from the stream

            //System.out.println("Received student id: " + user.getID() + " and student name:" + user.getName() + " from server");
            scan = new Scanner(System.in);
            while(true){
                str = name + ": " + scan.nextLine();
                socket = new Socket(ip, port);
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(str);
            }
        } catch  (IOException uhe) {
            uhe.printStackTrace();
        } finally {
            try {
                input.close();
                output.close();
                socket.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

}
