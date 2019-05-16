package Entity;

import DAO.MessageDAO;

import javax.persistence.*;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.List;

@Entity
@Table(name = "Message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "content")
    private String content;

    public Message(){

    }

    public Message(String sender, String receiver, String content){
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
    }

    //send message to session
    public static void sendMessage(Session session, Message message){
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    //send recent message to session
    public static void sendRecentMessage(Session session, String sender, String receiver) {
        List<Message> messages = null;
        //load max 50 message from db
        try {
            messages = MessageDAO.getMessage(sender, receiver);
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            //send message loaded to client
            assert messages != null;
            for (int i = messages.size() - 1; i >= 0; i--){
                session.getBasicRemote().sendObject(messages.get(i));
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }
}
