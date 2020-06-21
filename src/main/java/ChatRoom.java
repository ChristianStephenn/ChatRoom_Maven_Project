import Presentation.Constants;
import Presentation.Controller.Server;

/**
  * <b>ChatRoom est une classe qui permet de lancer le serveur.</b>
  */

public class ChatRoom {

    public static void main(String[] args) {
        Server.connect(Constants.HOSTIP);
    }
}
