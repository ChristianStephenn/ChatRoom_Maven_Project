package Presentation.Controller;

import Data.Constants;
import Presentation.Model.Message;
import Presentation.Model.User;
import Data.Singletons;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Date;
import java.util.List;

/**
 * <b>ServerThread est la classe qui permet d'envoyer les sockets reçue par le Serveur</b>
 * <div>
 * UserThread contient :
 * <ul>
 * <li>Un Socket pour la communication</li>
 * <li>Un ObjectInputStream pour serialiser un objet (lecture)</li>
 * <li>Un ObjectOutputStream pour serialiser un objet (ecriture)</li>
 * <li>La liste des utilisateurs</li>
 * <li>Les messages de la discussion</li>
 * </ul>
 * </div>
 *
 */
public class ServerThread extends Thread{

    /**
     * Favorise la communication (au moment des transferts de données)
     */
    private final Socket socket;
    
    /**
     * Serialise l'objet (ecriture)
     */
    private ObjectInputStream input;
    
    /**
     * Serialise l'objet (lecture)
     */
    private ObjectOutputStream output;
    
    /**
     * Liste des utilisateurs en ligne
     */
    private List<User> usersList;
    
    /**
     * Message saisi
     */
    private Message message;

    /**
     * Contructeur ServerTheard
     *
     * @param socket
     *              socket pour le serveur
     */
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * Met en route le ServerThread
     *
     * @see Message
     * @see Server
     * @see Singletons
     *
     */
    public void run() {
        try {
            //create the streams that will handle the objects coming through the sockets
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());

            String mess = (String) input.readObject();     //deserialize and read the Student object from the stream
            String[] splitmess = mess.split(Constants.REGEX);

            Type MessType = new TypeToken<Message>() {
            }.getType();

            message = Singletons.getGson().fromJson(splitmess[0],MessType);

            usersList = Server.getUsersList();
            if(message.getText().equals("logout")){
                userLogout();
            }else if(message.getText().contains("Delete_Messages")){
                Server.deleteMessages();
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
    
    /**
     * Se déconnecte du Presentation.Controller.ChatRoom
     * 
     * @throws IOException relève une exception
     *
     * @see User
     * @see Message
     * @see Server
     * @see Constants
     */
    public void userLogout() throws IOException {
        User user = new User(message.getSenderName(),Constants.HOSTIP, message.getPort());
        Server.logout(user);
        Message logoutMessage = new Message(message.getSenderName(), 6000, message.getSenderName() + " is disconnected", Constants.dateFormat.format(new Date()));
        Server.broadcast(logoutMessage);
    }
    
    /**
     * Ajoute un nouvel utilisateur
     *
     * @throws IOException relève une exception
     * @throws InterruptedException relève une interruption
     *
     * @see User
     * @see Server
     * @see Message
     * @see Constants
     */
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
    }

    /**
     * Renvoie vrai si l'utilisateur existe deja, sinon faux
     *
     * @param name
     *          Nom de l'utilisateur
     * @param port
     *          Port de l'utilisateur
     *
     * @see User
     *
     * @return vrai si l'utilisateur existe, sinon faux
     */
    public boolean userExist(String name, int port){
        for (User user : usersList) {
            if (user.getName().equals(name) && user.getPort() == port) {
                return true;
            }
        }
        return false;
    }

    /**
     * Crée une liste d'utilisateur
     *
     * @return la liste des utilisateurs
     */
    public List<User> getUsersList() {
        return usersList;
    }
}
