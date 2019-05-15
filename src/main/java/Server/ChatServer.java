import DAO.MessageDAO;
import Entity.Message;
import Message.MessageDecoder;
import Message.MessageEncoder;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/messenger/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatServer {
    private static Set<ChatServer> endpoints = new CopyOnWriteArraySet<ChatServer>();
    private static HashMap<String, String> users = new HashMap<>();
    private Session session;
    private String receiver = null;

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username){
        System.out.println("Server is open");
        System.out.println(" -session id: " + session.getId());
        System.out.println(" -username: " + username);

        this.session = session;
        endpoints.add(this);
        users.put(session.getId(), username);
    }

    private void loadFriend(Session session) {

    }

    @OnMessage
    public void onMessage(Session session, String message){
        System.out.println("Server on message");
        System.out.println(" -message: " + message);
        System.out.println(" -session id: " + session.getId());
        System.out.println(" -username: " + users.get(session.getId()));

        if (message.length() > 4 && message.substring(0, 4).equals("user")){
            System.out.println(" -receiver: " + message.substring(5));
            receiver = message.substring(5);

            sendRecentMessage(session);
        } else {
            Message messObject = new Message(users.get(session.getId()), receiver, message);

            endpoints.forEach(endpoint -> {
                try {
                    //gui lai cho chinh no
                    if (endpoint.session == session) {
                        endpoint.session.getBasicRemote().sendObject(messObject);

                        //save message to db
                        try{
                            MessageDAO.saveMessage(messObject);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        System.out.println("sender:" + users.get(session.getId()) + " receiver: " + receiver + " message: " + message);
                    }


                    if (users.get(endpoint.session.getId()).equals(receiver)){
                        System.out.println(" -receiver: " + receiver);
                        endpoint.session.getBasicRemote().sendObject(messObject);
                    }
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void sendRecentMessage(Session session) {
        //load message from db
        List<Message> messages = MessageDAO.getMessage(users.get(session.getId()), receiver);

        try {
            //send message loaded to client
            for (int i = 0; i < messages.size(); i++){
                session.getBasicRemote().sendObject(messages.get(i));
            }
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Throwable throwable){
        System.out.println("Server on error");
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose(Session session){
        System.out.println("Server is close");
        System.out.println(" -session id: " + session.getId());
        System.out.println(" -username: " + users.get(session.getId()));

        endpoints.remove(this);
        users.remove(session.getId());
    }
}
