package sample;

import org.json.simple.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sillybird on 14.04.2016.
 */

public class Client {
    private String html;
    public String initConnection (JSONObject json, String operation) throws Exception {
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

            html = readStreamToString(connection.getInputStream(), "utf-8");
            System.out.println(html);

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
