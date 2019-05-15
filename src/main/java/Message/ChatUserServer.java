package Message;

import Entity.Message;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatUserServer {
    private Session session;
    private static Set<ChatUserServer> chatEndpoints = new CopyOnWriteArraySet<ChatUserServer>();
    private static HashMap<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username){
        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);

        Message message = new Message(username, "", "Connected!");
        broadcastMessage(message);

        System.out.println("Server on open");
    }

    @OnMessage
    public void onMessage(Session session, Message message){
        //message.setFrom(users.get(session.getId()));
        broadcastMessage(message);
    }

    @OnClose
    public void onClose(Session session){
        chatEndpoints.remove(this);
        Message message = new Message(users.get(session.getId()), "", "Disconnected!");
        broadcastMessage(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable){

    }

    private static void broadcastMessage(Message message) {
        chatEndpoints.forEach(endpoint ->{
            synchronized (endpoint){
                try {
                    endpoint.session.getBasicRemote().sendObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
