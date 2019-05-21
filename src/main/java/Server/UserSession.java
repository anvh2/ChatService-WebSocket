package Server;

import DAO.MessageDAO;
import Entity.Message;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class UserSession {
    private static HashMap<String, String> users = new HashMap<>();
    private Session session;
    private List<String> receivers = new ArrayList<>();

    public Session getSession(){
        return session;
    }

    public void addReceiver(String receiver) {
        this.receivers.add(receiver);
    }

    public void addNewUser(Session session, String username){
        this.session = session;
        users.put(session.getId(), username);
    }

    public void loadUserOnline(Set<ChatServer> endpoints, String username){
        endpoints.forEach(endpoint -> {
            Session sessionEndpoint = endpoint.getSessionOfEndpoint();//get session

            if (!users.get(sessionEndpoint.getId()).equals(users.get(session.getId()))){
                try {
                    //gui den tat ca cac user khac la user nay online
                    sessionEndpoint.getBasicRemote().sendObject(new Message(username, "","online"));

                    //gui den session nay nhung user dang online
                    session.getBasicRemote().sendObject(new Message(users.get(sessionEndpoint.getId()), "", "online"));
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendMessage(Set<ChatServer> endpoints, String message){
        Message messObject = new Message(users.get(session.getId()), receivers.get(0), message);

        //save message to db
        try{
            MessageDAO.saveMessage(messObject);
        } catch (Exception e){
            e.printStackTrace();
        }

        //gui lai cho chinh no
        try {
            session.getBasicRemote().sendObject(messObject);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }

        //kiem tra topic nao can gui va gui message toi client
        endpoints.forEach(endpoint -> {
            Session sessionEndpoint = endpoint.getSessionOfEndpoint();//get session

            //kiem tra nguoi can gui va gui tin nhan
            if (users.get(sessionEndpoint.getId()).equals(receivers.get(0))){
                System.out.println(" -receiver: " + receivers.get(0));
                try {
                    sessionEndpoint.getBasicRemote().sendObject(messObject);
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void sendRecentMessage(Session session) {
        List<Message> messages = null;
        String sender = users.get(session.getId());

        //load max 50 message from db
        try {
            messages = MessageDAO.getMessage(sender, receivers.get(0));
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

    public void removeOfflineUser(Set<ChatServer> endpoints, Session session){
        endpoints.forEach(endpoint -> {
            Session sessionEndpoint = endpoint.getSessionOfEndpoint();//get session

            if (sessionEndpoint!= session){
                try {
                    sessionEndpoint.getBasicRemote().sendObject(new Message(users.get(session.getId()), "","offline"));
                } catch (IOException | EncodeException e) {
                    e.printStackTrace();
                }
            }
        });

        users.remove(session.getId());
    }

    public String getUsername(Session session) {
        return users.get(session.getId());
    }
}
