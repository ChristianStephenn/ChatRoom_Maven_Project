package Presentation.Controller;

import Data.Constants;
import Data.Singletons;
import Presentation.Model.Message;
import Presentation.Model.User;
import Presentation.Model.Xml;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

/**
 * <b>Server est la classe qui gère tous les messages envoyés les retransmets</b>
 * <div>
 * Server contient :
 * <ul>
 * <li>Un ServerSocket pour récupérer les informations </li>
 * <li>La liste des utilisateurs</li>
 * <li>La liste des messages</li>
 * <li>Un fichier XML qui contient les messages envoyés</li>
 * </ul>
 * </div>
 */

public class Server{
    /**
     * Permet de récuperer les informations envoyer
     */
    private static ServerSocket ss;
    /**
     * Contient la liste des utilisateurs connectés
     */
    private static final List<User> usersList = new ArrayList<>();
    /**
     * Liste qui contient la liste des messages envoyés
     */
    private static List<Message> messagesList = new ArrayList<>();
    /**
     * Fichier qui contient tous les messages envoyés
     */
    private static final Xml XMLCHATROOM = new Xml();

    /**
     * connecte le serveur à un port et attend les informations qui lui seront envoyer
     * @param ip
     *             adresse ip sur le lequel se trouve le serveur
     *
     * @see ServerSocket
     * @see Socket
     * @see ServerThread
     *
     */
    public static void connect(String ip) {
        try {
            messagesList = XMLCHATROOM.readXMLMessages();  // we load the previous conversation

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
     * envoie le message
     *
     * @throws IOException relève une exception
     * @param message
     *                  message à envoyer
     *
     * @see User
     * @see Message
     */

    public static void broadcast(Message message) throws IOException {
        for (User user : usersList) {                       //for every user we send a message
            send(message, user.getIp(), user.getPort());
        }
        saveMessage(message);                               // we save it in the message's list
    }

    /**
     * sauvegarde le message
     *
     * @param message
     *                  message à enregistrer
     *
     * @see Message
     */
    public static void saveMessage(Message message){
        if (!message.getText().contains("is connected") && !message.getText().contains("is disconnected")){
            messagesList.add(message);
            XMLCHATROOM.writeXMLMessages(messagesList);
        }
    }

    /**
     * envoie un message
     *
     * @throws IOException relève une exception
     * @param message
     *              message à envoyer
     * @param ip
     *              ip du serveur
     * @param port
     *              port de l'utilisateur
     *
     * @see Socket
     * @see ObjectOutputStream
     */
    public static void send(Message message, String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);
        ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());  //we send a message by sending it as an output
        output.writeObject(message);
    }


    /**
     * Déconecte un utilisateur
     *
     * @param user
     *                  utilisateur à déconecter
     *
     */
    public static void logout(User user) {
        for (int i = 0; i < usersList.size(); i++) {                //we search the user in the list
            if(usersList.get(i).getName().equals(user.getName())){
                usersList.remove(i);
                break;
            }
        }
    }



    /**
     * renvoie la liste d'utilisateur
     *
     * @return usersList
     */
    public static List<User> getUsersList(){
        return usersList;
    }


    /**
     * renvoie la liste des messages
     *
     * @return messagesList
     */
    public static List<Message> getMessagesList() {
        return messagesList;
    }


    /**
     * rajoute un utilisateurs à la liste
     *
     * @param user
     *              utilisateur à ajouter
     */
    public static void addUser(User user ){
        usersList.add(user);
    }


    /**
     * affiche tous les anciens messages
     *
     * @throws InterruptedException
     *              interruption
     * @throws IOException
     *              interruption
     * @param user
     *              utilisateur dont on veut afficher les messages
     *
     * @see Message
     */
    public static void printOldMessages(User user) throws IOException, InterruptedException {
        for (Message message : messagesList) {
            send(message, user.getIp(), user.getPort());
            sleep(100);
        }
    }



    /**
     * affiche les information d'un utilisateurs
     *
     * @throws IOException relève une exception
     * @param user
     *              utilisateur dont on veut afficher les  informations
     *
     * @see Message
     */
    public static void sendOnlineUsers(User user) throws IOException {
        for (User onlineUser : usersList) {
            Message message = new Message("Server", 6000, "Online_Users_List" + Constants.REGEX + onlineUser.getName(), Constants.dateFormat.format(new Date()));
            send(message, user.getIp(), user.getPort());
        }
    }


    /**
     * supprime tous les messages
     *
     * @throws IOException relève une exception
     *
     *
     * @see Message
     */

    public static void deleteMessages() throws IOException {
        messagesList.clear();                                       //we delete all the messages
        Message message = new Message("Server", 6000, "Clear_Messages", Constants.dateFormat.format(new Date()));  //we send a message as the server
        XMLCHATROOM.writeXMLMessages(messagesList);
        broadcast(message);
    }
}