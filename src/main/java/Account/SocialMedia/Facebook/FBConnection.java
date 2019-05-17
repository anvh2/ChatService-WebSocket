package Account.SocialMedia.Facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class FBConnection {
    private static final String FB_APP_ID = "2354188414857594";
    private static final String FB_APP_SECRET = "4340b787b93d5a73f847be3cc10f76b5";
    private static final String REDIRECT_URI = "http://localhost:8080/websocket";
    private static String accessToken = "";

    public String getFBAuthUrl() throws UnsupportedEncodingException {
        return "http://www.facebook.com/dialog/oauth?" + "client_id="
                + FBConnection.FB_APP_ID + "&redirect_uri="
                + URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
                + "&scope=email";
    }

    public String getFBGrapUrl(String code) throws UnsupportedEncodingException {
        String fbGrapUrl = "";

        fbGrapUrl = "https://graph.facebook.com/oauth/access_token?"
                + "client_id=" + FBConnection.FB_APP_ID + "&redirect_uri="
                + URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
                + "&client_secret=" + FB_APP_SECRET + "&code=" + code;

        return fbGrapUrl;
    }

    public String getAccessToken(String code) throws UnsupportedEncodingException {
        if ("".equals(accessToken)){
            URL fbGrapURL;

            try {
                fbGrapURL = new URL(getFBGrapUrl(code));
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw  new RuntimeException("Invalid code received!" + e);
            }

            URLConnection fbconn;
            StringBuffer buffer = null;

            try {
                fbconn = fbGrapURL.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(fbconn.getInputStream()));
                String inputLine;

                buffer = new StringBuffer();

                while ((inputLine = reader.readLine()) != null){
                    buffer.append(inputLine + "\n");
                }

                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw  new RuntimeException("Unable to connect with fb" + e);
            }

            accessToken = buffer.toString();

            if (accessToken.startsWith("{")){
                throw new RuntimeException("ERROR: Access token invalid");
            }
        }

        return accessToken;
    }
}
