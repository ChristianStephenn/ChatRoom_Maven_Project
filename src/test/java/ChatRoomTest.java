import Presentation.Controller.Server;
import Presentation.Controller.ServerThread;
import Presentation.Model.Message;
import Presentation.Model.User;
import Presentation.Xml;
import org.junit.jupiter.api.Test;

import java.net.Socket;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatRoomTest {

    @Test
    void ServerTest(){
        Server server = new Server();
        //server.connect(""); ?????
        server.addUser(new User("Alain"));
        server.addUser(new User("Bob"));
        assertEquals(2,Server.getUsers().size());
    }

    @Test
    void ServerThreadTest(){
        ServerThread serverThread = new ServerThread(new Socket());
        serverThread.run();
        int n=(serverThread.getUsers()).size();
        serverThread.addNewUser();
        //assertEquals(n+1,serverThread.getUsers()).size(););

    }

    @Test
    void SimpleUserTest(){

    }

    @Test
    void UserThreadTest(){


    }

    @Test
    void MessageTest(){
        Message message = new Message("JeanMi","couenne","08:04:2020");
        assertEquals("JeanMi",message.getSenderName());
        assertEquals("couenne",message.getText());
        assertEquals("08:04:2020",message.getDate());
    }

    @Test
    void UserTest(){
        User user = new User("Bob");
        assertEquals("Bob",user.getName());
        user.setName("Bernard");
        assertEquals("Bernard",user.getName());
    }
    @Test
    void XmlTest(){
        Xml xml = new Xml();

    }

    @Test
    void ChatRoomTest(){


    }

    @Test
    void GUI(){
        GUI gui = new GUI();
        assertEquals(gui,false);

    }

    @Test
    void LoginGUI(){
        LoginGUI loginGUI = new LoginGUI();
        assertEquals(loginGUI,false);

    }




}
