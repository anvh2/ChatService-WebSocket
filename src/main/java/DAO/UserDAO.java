package DAO;

import Entity.SessionManager;
import Entity.User;
import org.hibernate.Session;


public class UserDAO {
    public static User getUser(String username){
        Session session = SessionManager.getSession();

        return session.find(User.class, username);
    }

    public static void setUser(User user){
        Session session = SessionManager.getSession();

        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();

        session.close();
    }
}
