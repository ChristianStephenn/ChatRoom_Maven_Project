package Presentation.Controller;

import Presentation.Model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread{

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            //create the streams that will handle the objects coming through the sockets
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());

            String text = (String)input.readObject();  //read the object received through the stream and deserialize it
            System.out.println("server received a text:" + text);

            //User user = new User(3000,"jack");
            //output.writeObject(user);		//serialize and write the Student object to the stream


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
}
