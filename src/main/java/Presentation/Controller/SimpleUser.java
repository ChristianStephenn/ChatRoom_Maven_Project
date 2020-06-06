package Presentation.Controller;

import Presentation.Constants;
import Presentation.Model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class SimpleUser {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket socket;
    private Scanner scan;
    private String name;
    Message message;

    public void connect(String name, String ip) {
        this.name = name;
        int port = 6666;
        String tmpString;
        try  {
            //create the socket; it is defined by an remote IP address (the address of the server) and a port number
            socket = new Socket(ip, port);

            //create the streams that will handle the objects coming and going through the sockets
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            send(name + " is connected");
            //User user = (User) input.readObject();	//deserialize and read the Student object from the stream
            //System.out.println("Received student id: " + user.getID() + " and student name:" + user.getName() + " from server");
            scan = new Scanner(System.in);
            while(true){
                tmpString = name + ": " + scan.nextLine();
                socket = new Socket(ip, port);
                output = new ObjectOutputStream(socket.getOutputStream());
                send(tmpString);
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

    public void send(String str) throws IOException {
        message = new Message(name, str, Constants.dateFormat.format(new Date()));
        output.writeObject(message);
    }

}
