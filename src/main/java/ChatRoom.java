import Presentation.Constants;
import Presentation.Controller.Server;

public class ChatRoom {

    public static void main(String[] args) {
        Server.connect(Constants.HOSTIP);
    }
}
