package Server;

import Message.MessageDecoder;
import Message.MessageEncoder;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/messenger/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatServer {
    private static Set<ChatServer> endpoints = new CopyOnWriteArraySet<ChatServer>();
    private UserSession userSession = new UserSession();

    //get session of this endpoint
    public Session getSessionOfEndpoint(){
        return userSession.getSession();
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username){
        System.out.println("Server is open");
        System.out.println(" -session id: " + session.getId());
        System.out.println(" -username: " + username);

        endpoints.add(this);
        userSession.addNewUser(session, username);
        userSession.loadUserOnline(endpoints, username);
    }

    @OnMessage
    public void onMessage(Session session, String message){
        //log
        System.out.println("Server on message");
        System.out.println(" -message: " + message);
        System.out.println(" -session id: " + session.getId());
//        System.out.println(" -username: " + users.get(session.getId()));

//        //kiem tra su kien kich chuot vao ban be de subcribe topic
        if (message.length() > 4 && message.substring(0, 4).equals("user")){
            System.out.println(" -receiver: " + message.substring(5));
            userSession.setReceiver(message.substring(5));

            //send recent message to client
            userSession.sendRecentMessage(session);
        } else { // neu khong phai la 1 event message thi gui tin nhan giua cac user
            userSession.sendMessage(endpoints, message);
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
//        System.out.println(" -username: " + users.get(session.getId()));

        //remove all data of user
        userSession.removeOfflineUser(endpoints, session);
        endpoints.remove(this);
    }
}
