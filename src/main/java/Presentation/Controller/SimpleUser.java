package Presentation.Controller;

import Presentation.Constants;
import Presentation.Model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class SimpleUser {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket socket;
    private Scanner scan;
    private String name;
    private int port;
    private static ServerSocket ss;
    Message message;

    public void connect(String name, String ip) {
        this.name = name;
        int min = 6500;
        int max = 7000;
        int range = max-min;
        try  {
            //create the socket; it is defined by an remote IP address (the address of the server) and a port number
            Socket socket = new Socket(ip, 6666);

            //create the streams that will handle the objects coming and going through the sockets
            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            //User user = (User) input.readObject();	//deserialize and read the Student object from the stream
            //System.out.println("Received student id: " + user.getID() + " and student name:" + user.getName() + " from server");
            port = (int)(Math.random() * range) + min;

            send(name + " is connected");
            ss = new ServerSocket(port);
            while(true){
                Socket serverSocket = ss.accept();
                input = new ObjectInputStream(serverSocket.getInputStream());
                message = (Message) input.readObject();
                System.out.println(message.getText());
                //new UserThread(socket, name).start();
            }
        } catch  (IOException | ClassNotFoundException uhe) {
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
        message = new Message(name, port, str, Constants.dateFormat.format(new Date()));
        output.writeObject(message);
    }

}
