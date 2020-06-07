package Presentation.Controller;

import Presentation.Model.Message;
import Presentation.Model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server{
    private static ObjectInputStream input;
    private static ObjectOutputStream output;
    private String ip = "localhost";
    private static ServerSocket ss;
    private static List<User> users = new ArrayList<User>();

    public static void connect(String ip) {
        try {
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
        for (User user : users) {
            send(message, user.getIp(), user.getPort());
        }
    }

    public static void send(Message message, String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);
        output = new ObjectOutputStream(socket.getOutputStream());
        output.writeObject(message);
    }

    public static List<User> getUsers(){
        return users;
    }

    public static void addUser(User user ){
        users.add(user);
    }
}
