package Server;

import DAO.MessageDAO;
import Entity.Message;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class UsersSession {
    private static Set<UsersSession> endpoints = new CopyOnWriteArraySet<UsersSession>();
    private static HashMap<String, String> users = new HashMap<>();
    private Session session;

    /**/
    public String getUserName(Session session){
        return users.get(session.getId());
    }

    /**/
    public void addNewUser(Session session, String username){
        this.session = session;
        endpoints.add(this);
        users.put(session.getId(), username);
    }

    /**/
    public void loadAndAddOnlineUser(Session session, String username){
        endpoints.forEach(endpoint -> {
            if (!users.get(endpoint.session.getId()).equals(users.get(session.getId()))) {
                try {
                    //gui den tat ca cac user khac la user nay online
                    endpoint.session.getBasicRemote().sendObject(new Message(username, "", "online"));
                    //gui den user nay nhung user khac dang online
                    endpoint.session.getBasicRemote().sendObject(new Message(users.get(endpoint.session.getId()), "", "online"));
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**/
    public void sendRecentMessage(Session session, String receiver){
        List<Message> messages = null;//list 50 newest message
        String sender = users.get(session.getId());//get username of this session

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

    /**/
    public void sendMessage(Session session, String receiver, Message messObject){
        //gui lai cho chinh no
        try {
            session.getBasicRemote().sendObject(messObject);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }

        //kiem tra topic nao can gui va gui message toi client
        endpoints.forEach(endpoint -> {
            //kiem tra nguoi can gui va gui tin nhan
            if (users.get(endpoint.session.getId()).equals(receiver)){
                System.out.println(" -receiver: " + receiver);
                try {
                    endpoint.session.getBasicRemote().sendObject(messObject);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**/
    public void removeOfflineUser(Session session){
        endpoints.forEach(endpoint -> {
            if (endpoint.session != session){
                try {
                    endpoint.session.getBasicRemote().sendObject(new Message(users.get(session.getId()), "","offline"));
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**/
    public void removeUser(Session session){
        endpoints.remove(this);
        users.remove(session.getId());
    }
}
