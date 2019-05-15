package DAO;

import Entity.Message;
import Entity.SessionManager;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    public static List<Message> getMessage(String sender, String receiver){
        return SessionManager.getSession()
                .createQuery("SELECT m FROM Message m WHERE (sender = :sender OR sender = :receiver) AND (receiver = :receiver OR receiver = :sender)", Message.class)
                .setParameter("sender", sender)
                .setParameter("receiver", receiver)
                .getResultList();
    }

    public static void saveMessage(Message message){
        Session session = SessionManager.getSessionFactory().openSession();

        session.getTransaction().begin();
        session.save(message);
        session.getTransaction().commit();

        session.close();
    }
}
