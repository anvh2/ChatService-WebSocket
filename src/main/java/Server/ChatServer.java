package Server;

import DAO.MessageDAO;
import Entity.Message;
import Message.MessageDecoder;
import Message.MessageEncoder;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/messenger/{username}", decoders = MessageDecoder.class, encoders = MessageEncoder.class)
public class ChatServer {
    private static UsersSession users = new UsersSession();
    private String receiver = null;

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username){
        System.out.println("Server is open");
        System.out.println(" -session id: " + session.getId());
        System.out.println(" -username: " + username);

        users.addNewUser(session, username);
        users.loadAndAddOnlineUser(session, username);

    }

    @OnMessage
    public void onMessage(Session session, String message){
        //log
        System.out.println("Server on message");
        System.out.println(" -message: " + message);
        System.out.println(" -session id: " + session.getId());
        System.out.println(" -username: " + users.getUserName(session));

        //kiem tra su kien kich chuot vao ban be de subcribe topic
        if (message.length() > 4 && message.substring(0, 4).equals("user")){
            System.out.println(" -receiver: " + message.substring(5));
            receiver = message.substring(5);

            //send recent message to client
            users.sendRecentMessage(session, receiver);
        } else {
            Message messObject = new Message(users.getUserName(session), receiver, message);

            //save this message to db
            try{
                MessageDAO.saveMessage(messObject);
            } catch (Exception e) {
                e.printStackTrace();
            }

            users.sendMessage(session, receiver, messObject);
            System.out.println(" -sender:" + users.getUserName(session) + " receiver: " + receiver + " message: " + message);

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
        System.out.println(" -username: " + users.getUserName(session));

        users.removeOfflineUser(session);
        users.removeUser(session);
    }
}
