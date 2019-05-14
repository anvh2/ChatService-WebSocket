import DAO.UserDAO;
import Entity.User;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/messenger/{username}")
public class ChatServer {
    private static Set<ChatServer> endpoints = new CopyOnWriteArraySet<ChatServer>();
    private static HashMap<String, String> users = new HashMap<>();
//    private static HashMap<String, String> messageQueue = new HashMap<>();
    private Session session;
//    private HashMap<String, String> receivers = new HashMap<>();
    private String receiver = null;

    private void setReceiver(String receiver){
        this.receiver  =receiver;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username){
        System.out.println("Server is open");
        System.out.println(" -session id: " + session.getId());
        System.out.println(" -username: " + username);

        this.session = session;
        endpoints.add(this);
        users.put(session.getId(), username);

        loadFriend(session);
    }

    private void loadMessage(Session session) {
        try {
            session.getBasicRemote().sendText(users.get(session.getId()) + ": load message");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFriend(Session session) {
        try {
            session.getBasicRemote().sendText(users.get(session.getId()) + ": load friend");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        } else {
            endpoints.forEach(endpoint -> {
                try {
                    //gui lai cho chinh no
                    if (endpoint.session == session) {
                        endpoint.session.getBasicRemote().sendText(users.get(session.getId()) + ": " + message);
                    }


                    if (users.get(endpoint.session.getId()).equals(receiver)){
                        System.out.println(" -receiver: " + receiver);
                        endpoint.session.getBasicRemote().sendText(users.get(session.getId()) + ": " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
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
