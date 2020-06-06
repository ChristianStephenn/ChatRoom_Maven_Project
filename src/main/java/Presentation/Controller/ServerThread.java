package Presentation.Controller;

import Presentation.Model.Message;
import Presentation.Model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ServerThread extends Thread{

    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private List<User> users;
    private Message message;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            //create the streams that will handle the objects coming through the sockets
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());

            message = (Message) input.readObject();     //deserialize and read the Student object from the stream
            System.out.println(message.getText());
            users = Server.getUsers();
            addNewUser();

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

    public void addNewUser(){
        if(users.size() == 0){
            User user = new User(message.getSenderName());
            Server.addUser(user);
        }else{
            if(!userExist(message.getSenderName())){
                User user = new User(message.getSenderName());
                Server.addUser(user);
            }
        }
    }

    public boolean userExist(String name){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getName().equals(name)){
                return true;
            }
        }
        return false;
    }

}
