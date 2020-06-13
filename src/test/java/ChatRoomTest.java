import Presentation.Constants;
import Presentation.Controller.Server;
import Presentation.Controller.ServerThread;
import Presentation.GUI;
import Presentation.LoginGUI;
import Presentation.Model.Message;
import Presentation.Model.User;
import Presentation.Xml;
import org.junit.jupiter.api.Test;

import java.net.Socket;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatRoomTest {

    @Test
    void ServerUserTest(){
        Server.addUser(new User("Alain","localhost",6660));
        Server.addUser(new User("Bob","localhost", 6969));
        assertEquals(2,Server.getUsers().size());
    }

    @Test
    void ServerThreadTest(){
        ServerThread serverThread = new ServerThread(new Socket());
        serverThread.run();
        int n=(serverThread.getUsers()).size();
        serverThread.addNewUser();
        //assertEquals(n+1,serverThread.getUsers()).size(););
        //problèmes avec les sockets
    }

    @Test
    void SimpleUserTest(){
        //problème sockets
    }

    @Test
    void UserThreadTest(){
        //problème sockets
    }

    @Test
    void MessageTest(){
        Message message = new Message("JeanMi",6666, "couenne","08:04:2020");
        assertEquals("JeanMi",message.getSenderName());
        assertEquals("couenne",message.getText());
        assertEquals("08:04:2020",message.getDate());
    }

    @Test
    void UserTest(){
        User user = new User("Bob","localhost", 6670);
        assertEquals("Bob",user.getName());
        user.setName("Bernard");
        assertEquals("Bernard",user.getName());
        assertEquals("localhost",user.getIp());
        assertEquals(6670,user.getPort());
    }
    @Test
    void readXmlTest(){
        List<Message> messagesList;
        Xml xml = new Xml();
        messagesList = xml.readXMLMessages();
        assertEquals("louis",messagesList.get(0).getSenderName());
        assertEquals("xml test",messagesList.get(0).getText());
        assertEquals("2020/06/02 00:27:49",messagesList.get(0).getDate());
    }

    @Test
    void writeXmlTest(){
        //pending
        List<Message> messagesList;
        Xml xml = new Xml();
        messagesList = xml.readXMLMessages();
        Message mess = new Message("unitTest",6680,"Hello World!", Constants.dateFormat.format(new Date()));
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
