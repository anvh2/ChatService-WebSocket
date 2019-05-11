package EntityDAO;

import Entity.SessionManager;
import Entity.User;
import org.hibernate.Session;

public class UserDAO {
    public User getUser(int id){
        Session session = SessionManager.getSession();

        return session.find(User.class, id);
    }
}
