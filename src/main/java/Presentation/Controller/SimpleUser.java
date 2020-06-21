package Presentation.Controller;

import Data.Constants;
import Presentation.Model.Message;
import Data.Singletons;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

    /**
     * <b>SimpleUser est la classe qui permet de préciser l'utilisateur est connecté.</b>
     * <div>
     * SimpleUser contient :
     * <ul>
     * <li>Un ObjectOutputStream pour serialiser un objet (ecriture)</li>
     * <li>Un Nom</li>
     * <li>Un Port</li>
     * <li>Un Message</li>
     *</ul>
     *</div>
     */
public class SimpleUser {
    
    /**
     * Serialise l'objet (ecriture)
     */
    private static ObjectOutputStream output;
    
    /**
     * Nom de l'utilisateur
     */
    private static String name;
    
    /**
     * Port de l'utilisateur
     */
    private static int port;
    
    /**
     * Message de l'utilisateur
     */
    static Message message;

    /**
     * Permet de se connecter au Presentation.Controller.ChatRoom
     *
     * @param name
     *             Nom de l'utilisateur
     */
    public void connect(String name) {
        if(name.trim().equals("")){
            this.name = "Anonyme User";
        }else{
            this.name = name;
        }
        int min = 6500;
        int max = 7000;
        int range = max-min;
        try  {

            port = (int)(Math.random() * range) + min;

            send(name + " is connected");
            ServerSocket ss = new ServerSocket(port);
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

    /**
     * Envoie le message
     *
     * @param str
     *              un parametre de User
     *
     * @throws IOException releve une exception
     *
     * @see Message
     * @see Singletons
     */
    public static void send(String str) throws IOException {
        //create the socket; it is defined by an remote IP address (the address of the server) and a port number
        Socket socket = new Socket(Constants.HOSTIP, 6666);
        //create the streams that will handle the objects coming and going through the sockets
        output = new ObjectOutputStream(socket.getOutputStream());

        message = new Message(name, port, str, Constants.dateFormat.format(new Date()));
        String mess = Singletons.getGson().toJson(message);
        output.writeObject(mess+"/-reg@#@#-/\\e");
    }

    /**
     * Se déconnecte du Presentation.Controller.ChatRoom
     *
     * @throws IOException releve une exception
     */
    public static void logout() throws IOException {
        send("logout");
    }

    /**
     * @return le nom de l'utilisateur
     */
    public static String getName(){
        return name;
    }

}
