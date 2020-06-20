package Presentation.Controller;

import Presentation.Constants;
import Presentation.Model.Message;
import Presentation.Model.User;
import Presentation.Xml;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

public class Server{
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private static ServerSocket ss;
    private static List<User> usersList = new ArrayList<User>();
    private static List<Message> messagesList = new ArrayList<Message>();
    private static final Xml XMLCHATROOM = new Xml();

    public static void connect(String ip) {
        try {
            messagesList = XMLCHATROOM.readXMLMessages();

            //the server socket is defined only by a port (its IP is localhost)
            ss = new ServerSocket (6666);
            System.out.println("Server waiting for connection...");

            while (true) {
                Socket socket = ss.accept();//establishes connection
                // create a new thread to handle client socket
                new ServerThread(socket).start();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            //if IOException close the server socket
            if (ss != null && !ss.isClosed()) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public static void broadcast(Message message) throws IOException {
        for (User user : usersList) {
            send(message, user.getIp(), user.getPort());
        }
        saveMessage(message);
    }

    public static void saveMessage(Message message){
        if (!message.getText().contains("is connected") && !message.getText().contains("is disconnected")){
            messagesList.add(message);
            //XMLCHATROOM.writeXMLMessages(messagesList); sauvegarde XML
        }
    }

    public static void send(Message message, String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);
        output = new ObjectOutputStream(socket.getOutputStream());
        output.writeObject(message);
    }

    public static void logout(User user) {
        for (int i = 0; i < usersList.size(); i++) {
            /*for (int j = 0; j < usersList.size(); j++) {
                System.out.println(usersList.get(j).getName() + i);
            }*/
            if(usersList.get(i).getName().equals(user.getName())){
                usersList.remove(i);
                break;
            }
        }
    }

    public static List<User> getUsersList(){
        return usersList;
    }

    public static void addUser(User user ){
        usersList.add(user);
    }

    public static void printOldMessages(User user) throws IOException, InterruptedException {
        for (Message message : messagesList) {
            send(message, user.getIp(), user.getPort());
            sleep(100);
        }
    }
}
