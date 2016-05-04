package sample;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import sample.dialog.Dialog;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sillybird on 14.04.2016.
 * Simple class. Method initConnection() create connection and send HTTP request w JSON file
 * and method readStreamToString gets response
 */

public class Client {
    private String html;
    public String initConnection (JSONObject json, String operation, Stage stage) throws Exception {
        HttpURLConnection connection = (HttpURLConnection)new URL("http://dota2begin.tk:80/server/api/" + operation).openConnection();
        connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
        out.write(json.toJSONString());
        out.flush();
        out.close();

//читаем то, что отдал нам сервер
            try {
                html = readStreamToString(connection.getInputStream(), "utf-8");
                System.out.println(html);
            }
            catch (IOException e) {
                Dialog dialog = new Dialog();
                Label label = new Label();
                label.setText("Server fatal error!");
                dialog.showDialog(stage, label);
            }


        return html;
    }
    private String readStreamToString(InputStream in, String encoding)
            throws IOException {
        StringBuffer b = new StringBuffer();
        InputStreamReader r = new InputStreamReader(in, encoding);
        int c;
        while ((c = r.read()) != -1) {
            b.append((char)c);
        }
        return b.toString();
    }
}
