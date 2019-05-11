////import javax.websocket.*;
////import javax.websocket.server.ServerEndpoint;
////import java.io.IOException;
////import java.util.Collections;
////import java.util.HashSet;
////import java.util.Set;
////
////@ServerEndpoint("/chatroom")
////public class ChatRoomServer {
////    private static Set<Session> users = Collections.synchronizedSet(new HashSet<Session>());
////
////    @OnOpen
////    public void handleOpen(Session session){
////        users.add(session);
////        System.out.println("Server on open");
////    }
////
////    @OnMessage
////    public void handlMessage(String message, Session session) throws IOException {
////        String username = (String) session.getUserProperties().get("username");
////
////        if (username == null){
////            session.getUserProperties().put("username", message);
////            session.getBasicRemote().sendText("System: your are connected as " + message);
////        } else {
////            for (Session user : users) {
////                user.getBasicRemote().sendText(username + ": " + message);
////            }
////        }
////    }
////
////    @OnClose
////    public void handleClose(Session session){
////        users.remove(session);
////    }
////
////    @OnError
////    public void handleError(Throwable throwable){
////        throwable.printStackTrace();
////    }
////}
//
//import javax.websocket.*;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Set;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//@ServerEndpoint("/messenger/{username}")
//public class ChatRoomServer {
//    private Session session;
//    private static Set<ChatRoomServer> endpoints = new CopyOnWriteArraySet<ChatRoomServer>();
//    private static HashMap<String, String> users = new HashMap<>();
//
//    @OnOpen
//    public void onOpen(Session session, @PathParam("username") String username){
//        System.out.println("--------------Server is open");
//
//        this.session = session;
//        endpoints.add(this);
//        users.put(session.getId(), username);
//    }
//
//    @OnMessage
//    public void onMessage(Session session, String message){
//        System.out.println("--------------Server on open");
//
//        endpoints.forEach(endpoint -> {
//            try {
//                endpoint.session.getBasicRemote().sendText(users.get(session.getId()) + ": " + message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable){
//        System.out.println("--------------Server on error");
//        throwable.printStackTrace();
//    }
//
//    @OnClose
//    public void onClose(Session session){
//        System.out.println("--------------Server is close");
//        endpoints.remove(this);
//        users.remove(session.getId());
//    }
//}