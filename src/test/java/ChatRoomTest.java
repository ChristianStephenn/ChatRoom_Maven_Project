import Presentation.Constants;
import Presentation.Controller.Server;
import Presentation.Controller.ServerThread;
import Presentation.Controller.SimpleUser;
import Presentation.GUI;
import Presentation.LoginGUI;
import Presentation.Model.Message;
import Presentation.Model.User;
import Presentation.Xml;
import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.Severity;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatRoomTest {

    @Test
    void ServerUserTest(){

        Server.addUser(new User("Alain","localhost",6660));
        User Bob = new User("Bob","localhost", 6969);
        Server.addUser(Bob);

        assertEquals(2,Server.getUsersList().size());
        Server.logout(Bob); //on déconnecte Bob donc il ne reste plus q'une personne dans la liste
        assertEquals(1,Server.getUsersList().size());

        assertEquals(0,Server.getMessagesList().size());

        Message message = new Message("Alain",6660,"Bonjour","15/06/20");

        Server.saveMessage(message);

        assertEquals(1,Server.getMessagesList().size());

        try {
            Server.broadcast(message);
        }
        catch (Exception e) {
            System.out.println("error");
        }
        //assertEquals(2,Server.getMessagesList().size());
    }



    @Test
    void ServerThreadTest() throws IOException, InterruptedException {
        /*ServerSocket ss = new ServerSocket(6666);
        Socket s = ss.accept();
        ServerThread serverThread = new ServerThread(s);
        serverThread.run();
        int n=(serverThread.getUsersList()).size();
        serverThread.addNewUser();
        assertEquals(n+1,serverThread.getUsersList().size());
        //problèmes avec les sockets*/
    }

    @Test
    void SimpleUserTest(){
        //problème sockets
       /* User Tom = new User("Tom","localhost", 6970);
        SimpleUser simpleUser = new SimpleUser();
        simpleUser.connect("tom");
        assertEquals("tom",SimpleUser.getName());*/
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
    }

    @Test
    void LoginGUI(){


    }
}
