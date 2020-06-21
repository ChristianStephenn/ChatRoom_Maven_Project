package Presentation.Controller;

import Presentation.Constants;
import Presentation.GUI;
import Presentation.Model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * <b>UserThread est la classe qui permet d'envoyer les sockets reçue par le serveur</b>
 * <div>
 * UserThread contient :
 * <ul>
 * <li>Un Socket pour la communication</li>
 * <li>Un ObjectInputStream pour serialiser un objet (lecture)</li>
 * <li>Un ObjectOutputStream pour serialiser un objet (ecriture)</li>
 * <li>Un message</li>
 * </ul>
 * </div>
 *
 *
 */
public class UserThread extends Thread{
    
    /**
     * Favorise la communication entre les utilisateurs
     */
    private final Socket socket;
    
    /**
     * Serialise l'objet (lecture)
     */
    private ObjectInputStream input;
    
    /**
     * Serialise l'objet (ecriture)
     */
    private ObjectOutputStream output;
    
    /**
     * Message de l'utilisateur
     */
    private Message message;

    /**
     * Contructeur UserThread surchargé
     *
     * @param socket
     *              Favorise la communication
     */
    public UserThread(Socket socket) {
        this.socket = socket;
    }

    /**
     * Met en route le UserThread
     * 
     * @see Message
     * @see GUI
     */
    public void run() {
        try {
            //create the streams that will handle the objects coming through the sockets
            input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());

            message = (Message) input.readObject();
            if(message.getText().contains("Online_Users_List")){
                String[] splitmess = message.getText().split(Constants.REGEX);
                GUI.refreshOnlineUsers(splitmess[1]);
            }else if (message.getText().contains("is connected")){
                GUI.refreshOnlineUsers(message.getSenderName());
            }else if (message.getText().contains("is disconnected")){
                GUI.refreshLogout(message.getSenderName());
            }else if (message.getText().contains("Clear_Messages")){
                GUI.refreshGUI();
            }else{
                System.out.println(message.getText());
                GUI.printGui(message.getText());
            }

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

}
