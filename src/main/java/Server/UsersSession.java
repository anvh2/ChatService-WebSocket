package Server;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class UsersSession {
    private static Set<ChatServer> endpoints = new CopyOnWriteArraySet<ChatServer>();
    private static HashMap<String, String> users = new HashMap<>();


}
