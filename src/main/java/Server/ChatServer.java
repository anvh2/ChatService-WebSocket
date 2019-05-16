package Server;

import DAO.MessageDAO;
import Entity.Message;
import Message.MessageDecoder;
import Message.MessageEncoder;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
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

        //gui den tat ca cac user khac la thang nay online
        endpoints.forEach(endpoint -> {
            //Message.sendMessage(endpoint.session, new Message(username, "",""));
        });
    }

    private void loadFriend(Session session) {

    }

    @OnMessage
    public void onMessage(Session session, String message){
        //log
        System.out.println("Server on message");
        System.out.println(" -message: " + message);
        System.out.println(" -session id: " + session.getId());
        System.out.println(" -username: " + users.get(session.getId()));

        //kiem tra su kien kich chuot vao ban be de subcribe topic
        if (message.length() > 4 && message.substring(0, 4).equals("user")){
            System.out.println(" -receiver: " + message.substring(5));
            receiver = message.substring(5);

            //send recent message to client
            Message.sendRecentMessage(session, users.get(session.getId()), receiver);
        } else {
            Message messObject = new Message(users.get(session.getId()), receiver, message);

            //kiem tra topic nao can gui va gui message toi client
            endpoints.forEach(endpoint -> {
                //gui lai cho chinh no
                if (endpoint.session == session) {
                    Message.sendMessage(endpoint.session, messObject);

                    //save this message to db
                    try{
                        MessageDAO.saveMessage(messObject);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println("sender:" + users.get(session.getId()) + " receiver: " + receiver + " message: " + message);
                }

                //kiem tra nguoi can gui va gui tin nhan
                if (users.get(endpoint.session.getId()).equals(receiver)){
                    System.out.println(" -receiver: " + receiver);
                    Message.sendMessage(endpoint.session, messObject);
                }
            });
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
