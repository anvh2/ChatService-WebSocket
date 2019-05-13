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
    private Session session;
//    private HashMap<String, String> receivers = new HashMap<>();
    private String receiver = null;

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username){
        System.out.println("Server is open");
        System.out.println(" -session id: " + session.getId());
        System.out.println(" -username: " + username);

//        User user = UserDAO.getUser("admin");
//        System.out.println(user.getPassword());

        this.session = session;
        endpoints.add(this);
        users.put(session.getId(), username);
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
                    if (endpoint.session == session) {
                        endpoint.session.getBasicRemote().sendText(users.get(session.getId()) + ": " + message);
                    }

                    if (users.get(endpoint.session.getId()).equals(receiver)){
                        System.out.println(" -sender: " + users.get(endpoint.session.getId()));
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
