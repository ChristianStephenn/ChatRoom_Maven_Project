package Presentation.Controller;

import Presentation.Constants;
import Presentation.Model.Message;
import Presentation.Singletons;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class SimpleUser {
    private static ObjectOutputStream output;
    private static String name;
    private static int port;
    private static ServerSocket ss;
    static Message message;

    public void connect(String name) {
        this.name = name;
        int min = 6500;
        int max = 7000;
        int range = max-min;
        try  {

            port = (int)(Math.random() * range) + min;

            send(name + " is connected");
            ss = new ServerSocket(port);
            while(true){
                Socket serverSocket = ss.accept();
                new UserThread(serverSocket).start();
            }
        } catch  (IOException uhe) {
            uhe.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public static void send(String str) throws IOException {
        //create the socket; it is defined by an remote IP address (the address of the server) and a port number
        Socket socket = new Socket(Constants.HOSTIP, 6666);
        //create the streams that will handle the objects coming and going through the sockets
        output = new ObjectOutputStream(socket.getOutputStream());

        message = new Message(name, port, str, Constants.dateFormat.format(new Date()));
        String mess = Singletons.getGson().toJson(message);
        output.writeObject(mess+"/-reg@#@#-/\\e");
    }

    public static void logout() throws IOException {
        send("logout");
    }

    public static String getName(){
        return name;
    }

}
