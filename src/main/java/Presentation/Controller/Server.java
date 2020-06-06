package Presentation.Controller;

import Presentation.Model.User;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server{
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
                /*if(users.size()>0)
                    System.out.println(users.get(0).getName() + " " + users.size());*/
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

    public static List<User> getUsers(){
        return users;
    }

    public static void addUser(User user ){
        users.add(user);
    }
}
