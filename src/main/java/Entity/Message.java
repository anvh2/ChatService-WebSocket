package Entity;

import javax.persistence.*;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

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
}
