package Utils;

import Entity.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AppUtils {
    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
    private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();

    //store user info in session
    public static void storeLoginedUser(HttpSession session, User loginedUser){
            session.setAttribute("loginedUser", loginedUser);
    }

    //get info stored user
    public static User getLoginedUser(HttpSession session){
        return (User) session.getAttribute("loginedUser");
    }

    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri){
        Integer id = uri_id_map.get(requestUri);

        if (id == null){
            id = REDIRECT_ID++;

            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);

            return id;
        }

        return id;
    }

    public static String getRedirectAfterLoginUrl(HttpSession session, int redirectID){
        return id_uri_map.get(redirectID);
    }
}
