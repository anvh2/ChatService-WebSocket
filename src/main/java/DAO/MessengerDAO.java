package DAO;

import Entity.Messenger;
import Entity.SessionManager;
import org.hibernate.Session;

public class MessengerDAO {
    public Messenger getMessenger(int id){
        Session session = SessionManager.getSession();

        return session.find(Messenger.class, id);
    }

    public void SetMessenger(Messenger messenger){

    }
}
