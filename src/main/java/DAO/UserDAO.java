package DAO;

import Entity.SessionManager;
import Entity.User;
import org.hibernate.Session;

public class UserDAO {
    public User getUser(int id){
        Session session = SessionManager.getSession();

        return session.find(User.class, id);
    }

    public void setUser(User user){
        Session session = SessionManager.getSession();

        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();

        session.close();
    }

    public static User getUser(String username){
//        Query query = SessionManager.getSession().createNativeQuery("select * from user where username = " + username);
//        query.executeUpdate();
//
//        return (User) query.getSingleResult();

        return null;
    }
}
