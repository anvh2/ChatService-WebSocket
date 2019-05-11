import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/messenger/{username}")
public class ChatUserServerByString {
    private Session session;
    private static Set<ChatUserServerByString> endpoints = new CopyOnWriteArraySet<ChatUserServerByString>();
    private static HashMap<String, String> users = new HashMap<>();
    private static HashMap<String, String> receivers = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username){
        System.out.println("--------------Server is open");

        this.session = session;
        endpoints.add(this);
        users.put(session.getId(), username);
    }

    @OnMessage
    public void onMessage(Session session, String message){
        System.out.println("--------------Server on open");

        System.out.println("type: " + message.substring(0, 4));
        if (message.substring(0, 4).equals("user")){
            receivers.put(session.getId(), message.substring(5));
            System.out.println("receiver: " + message.substring(5));
        } else {
            endpoints.forEach(endpoint -> {
                try {
                    endpoint.session.getBasicRemote().sendText(users.get(session.getId()) + ": " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (endpoint.session == session) {
                        endpoint.session.getBasicRemote().sendText(users.get(session.getId()) + ": " + message);
                    } else {
                        for (int i = 0; i < receivers.size(); i++){
                            if (users.get(endpoint.session.getId()).equals(receivers.get(i))){
                                endpoint.session.getBasicRemote().sendText(users.get(session.getId()) + ": " + message);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable){
        System.out.println("--------------Server on error");
        throwable.printStackTrace();
    }

    @OnClose
    public void onClose(Session session){
        System.out.println("--------------Server is close");
        endpoints.remove(this);
        users.remove(session.getId());
    }
}
