import Presentation.Constants;
import Presentation.Model.Message;
import Presentation.Model.User;
import Presentation.Xml;

import java.util.Date;
import java.util.List;

public class ChatRoom {

    public static void readMessagesList(List<Message> messagesList){
        for (int i = 0; i < messagesList.size(); i++) {
            afficheMessage(messagesList.get(i));
        }
    }

    public static void afficheMessage(Message mess){
        System.out.println(mess.getSenderName() + " "+mess.getDate());
        System.out.println("\t\t"+mess.getText());
    }

    public static void deleteMessage(List<Message> messagesList, Message mess){
        for (int i = 0; i < messagesList.size(); i++) {
            if(mess.equals(messagesList.get(i))){
                messagesList.remove(i);
                break;
            }
        }
    }

    public static void main(String[] args) {

        List<Message> messagesList;

        Xml XMLCHATROOM = new Xml();
        messagesList = XMLCHATROOM.readXMLMessages();
        readMessagesList(messagesList);

        User lol = new User("marc");
        Message mess = new Message(lol.getName(),"Hello World 3 !", Constants.dateFormat.format(new Date()));
        afficheMessage(mess);
        messagesList.add(mess);

        //XMLCHATROOM.writeXMLMessages(messagesList);
    }
}
