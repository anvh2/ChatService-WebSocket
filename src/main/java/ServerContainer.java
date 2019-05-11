//import javax.xml.bind.DatatypeConverter;
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class ServerContainer {
//    private static Socket socket;
//
//    public static void main(String[] args) {
//        try {
//            ServerSocket serverSocket = new ServerSocket(8000);
//
//            System.out.println("Server Started and listening to the port 8000");
//
//
//            while(true)
//            {
//                Socket socket = serverSocket.accept();
//                System.out.println("A client connected in port: " + socket.getPort());
//
//                InputStream inputStream = socket.getInputStream();
//                OutputStream outputStream = socket.getOutputStream();
//
//                String data = new Scanner(inputStream, "UTF-8").useDelimiter("\\r\\n\\r\\n").next();
//                Matcher matcher = Pattern.compile("^GET").matcher(data);
//                if (matcher.find()) {
//                    Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(data);
//                    match.find();
//                    byte[] response = ("HTTP/1.1 101 Switching Protocols\r\n"
//                            + "Connection: Upgrade\r\n"
//                            + "Upgrade: websocket\r\n"
//                            + "Sec-WebSocket-Accept: "
//                            + DatatypeConverter
//                            .printBase64Binary(
//                                    MessageDigest
//                                            .getInstance("SHA-1")
//                                            .digest((match.group(1) + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11")
//                                                    .getBytes("UTF-8")))
//                            + "\r\n\r\n")
//                            .getBytes("UTF-8");
//
//                    outputStream.write(response, 0, response.length);
//
//
//                } else {
//
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
//}
