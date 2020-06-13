package Presentation.Controller;

import Presentation.Model.Message;
import Presentation.Model.User;
import Presentation.Singletons;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
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

            String mess = (String) input.readObject();     //deserialize and read the Student object from the stream
            String[] splitmess = mess.split("/-reg@#@#-/");

            Type MessType = new TypeToken<Message>() {
            }.getType();

            message = Singletons.getGson().fromJson(splitmess[0].toString(),MessType);
            System.out.println(message.getText());

            users = Server.getUsers();
            Server.broadcast(message);
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
            User user = new User(message.getSenderName(),"localhost", message.getPort());
            Server.addUser(user);
        }else{
            if(!userExist(message.getSenderName())){
                User user = new User(message.getSenderName(),"localhost", message.getPort());
                Server.addUser(user);
            }
        }
    }

    public boolean userExist(String name){
        for (User user : users) {
            if (user.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getUsers() {
        return users;
    }
}
