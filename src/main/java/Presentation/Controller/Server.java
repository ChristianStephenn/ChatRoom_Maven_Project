package Presentation.Controller;

import Presentation.Constants;
import Presentation.Model.Message;
import Presentation.Model.User;
import Presentation.Xml;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * <b>Server est la classe qui permet d'initialiser un serveur.</b>
 * <div>
 * Server contient :
 * <ul>
 * <li>ObjectOutputStream pour sérialiser un objet</li>
 * <li>ServerSocket pour établir une liaison réseau</li>
 * <li>usersList pour afficher la liste des utilisateurs</li>
 * <li>messagesList pour afficher la liste des messages</li>
 * <li>XMLCHATROOM : fichier Xml</li>
 * </ul>
 * </div>
 *
 */
public class Server{
    
    /**
     * Permettre sérialiser un objet
     */
    private static ObjectOutputStream output;
    
    /**
     * Permettre d'établir une liaison réseau
     */
    private static ServerSocket ss;
    
    /**
     * La liste des utilisateurs
     */
    private static final List<User> usersList = new ArrayList<>();
    
    /**
     * La liste des messages
     */
    private static List<Message> messagesList = new ArrayList<>();
    
    /**
     * Fichier XML
     */
    private static final Xml XMLCHATROOM = new Xml();

    /**
     * Connecte le serveur
     * 
     * @param ip
     *          ip du serveur
     */
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

    /**
     * Diffuse les messages
     *
     * @param message
     *              Message saisie
     *
     * @throws IOException relève une exception
     *
     * @see Message
     */
    public static void broadcast(Message message) throws IOException {
        for (User user : usersList) {
            send(message, user.getIp(), user.getPort());
        }
        saveMessage(message);
    }

    /**
     * Sauvegarde les messages
     *
     * @param message
     *              Message saisie
     *
     * @see Message
     */
    public static void saveMessage(Message message){
        if (!message.getText().contains("is connected") && !message.getText().contains("is disconnected")){
            messagesList.add(message);
            //XMLCHATROOM.writeXMLMessages(messagesList); sauvegarde XML
        }
    }

    /**
     * Envoie les messages
     *
     * @param message
     *              Message saisie
     * @param ip
     *              Ip du serveur
     * @param port
     *              Port du serveur
     *
     * @throws IOException relève une exception
     *
     * @see Message
     */
    public static void send(Message message, String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);
        output = new ObjectOutputStream(socket.getOutputStream());
        output.writeObject(message);
    }

    /**
     * Se déconnecte du serveur
     *
     * @param user
     *          Nom de l'utilisateur
     * @see User
     */
    public static void logout(User user) {
        for (int i = 0; i < usersList.size(); i++) {
            if(usersList.get(i).getName().equals(user.getName())){
                usersList.remove(i);
                break;
            }
        }
    }
    
    /**
     * Obtient la liste des utilisateurs
     *
     * @see User
     * @return la liste des utilisateurs
     */
    public static List<User> getUsersList(){
        return usersList;
    }

    /**
     * Ajoute la liste des utilisateurs
     *
     * @param user
     *          Nom de l'utilisateur
     * @see User
     */
    public static void addUser(User user ){
        usersList.add(user);
    }

    /**
     * Affiche les anciens messages
     * 
     * @param user
     *              Nom de l'utilisateur
     * @throws IOException relève une exception
     * @throws InterruptedException relève une interruption
     * @see User
     * @see Message
     */
    public static void printOldMessages(User user) throws IOException, InterruptedException {
        for (Message message : messagesList) {
            send(message, user.getIp(), user.getPort());
            sleep(100);
        }
    }

    /**
     * Envoie un message de tous les utilisateurs en ligne
     * @param user
     *              Nom de l'utilisateur
     * @throws IOException relève une exception
     * @see User
     * @see Message
     *
     */
    public static void sendOnlineUsers(User user) throws IOException {
        for (User onlineUser : usersList) {
            Message message = new Message("Server", 6000, "Online_Users_List" + Constants.REGEX + onlineUser.getName(), Constants.dateFormat.format(new Date()));
            send(message, user.getIp(), user.getPort());
        }
    }


    public static void deleteMessages() throws IOException {
        messagesList.clear();
        Message message = new Message("Server", 6000, "Clear_Messages", Constants.dateFormat.format(new Date()));
        broadcast(message);
        //XMLCHATROOM.writeXMLMessages(messagesList); sauvegarde XML
    }
}

