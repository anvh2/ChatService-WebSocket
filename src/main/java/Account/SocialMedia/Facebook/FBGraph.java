package Account.SocialMedia.Facebook;

import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class FBGraph {
    private String accessToken;

    public FBGraph(String accessToken){
        this.accessToken = accessToken;
    }

    public String getFBGraph(){
        String graph = null;
        String gr = "http://graph.facebook.com/me?" + accessToken;
        try {
            URL url = new URL(gr);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer buffer = new StringBuffer();

            while ((inputLine = reader.readLine()) != null){
                buffer.append(inputLine + "\n");
                graph = buffer.toString();
                System.out.println(graph);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }

    public Map getGraphData(String fbGraph){
        Map fbProfile = new HashMap();

        try {
            JSONObject json = new JSONObject(fbGraph);

            fbProfile.put("id", json.getString("id"));
            fbProfile.put("first_name", json.getString("first_name"));

            if (json.has("email")){
                fbProfile.put("email", json.getString("email"));
            }

            if (json.has("gender")){
                fbProfile.put("gender", json.getString("gender"));
            }
        } catch (JSONException e){
            e.printStackTrace();
            throw new RuntimeException("Error in parsing FB Graph data");
        }

        return fbProfile;
    }
}
