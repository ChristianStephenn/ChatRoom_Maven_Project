package Presentation.Controller;

import Presentation.Constants;
import Presentation.Model.Message;
import Presentation.Model.User;
import Presentation.Singletons;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Date;
import java.util.List;

public class ServerThread extends Thread{

    private final Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private List<User> usersList;
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
            String[] splitmess = mess.split(Constants.REGEX);

            Type MessType = new TypeToken<Message>() {
            }.getType();

            message = Singletons.getGson().fromJson(splitmess[0].toString(),MessType);

            usersList = Server.getUsersList();
            if(message.getText().equals("logout")){
                userLogout();
            }else{
                System.out.println(message.getText());
                Server.broadcast(message);
                addNewUser();
            }

        } catch (IOException | ClassNotFoundException | InterruptedException ex) {
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

    public void userLogout() throws IOException {
        User user = new User(message.getSenderName(),Constants.HOSTIP, message.getPort());
        Server.logout(user);
        Message logoutMessage = new Message("Server", 6000, message.getSenderName() + " is disconnected", Constants.dateFormat.format(new Date()));
        Server.broadcast(logoutMessage);
    }

    public void addNewUser() throws IOException, InterruptedException {
        User user = new User(message.getSenderName(),Constants.HOSTIP, message.getPort());
        if(usersList.size() == 0){
            Server.addUser(user);
            Server.printOldMessages(user);
            Server.sendOnlineUsers(user);
        }else{
            if(!userExist(message.getSenderName(), message.getPort())){
                Server.addUser(user);
                Server.printOldMessages(user);
                Server.sendOnlineUsers(user);
            }
        }

        /*if(message.getText().contains("is connected")){
            Server.sendOnlineUsers(user);
        }*/
    }

    public boolean userExist(String name, int port){
        for (User user : usersList) {
            if (user.getName().equals(name) && user.getPort() == port) {
                return true;
            }
        }
        return false;
    }

    public List<User> getUsersList() {
        return usersList;
    }
}
