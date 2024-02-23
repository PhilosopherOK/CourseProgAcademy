package academy.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Message {
    private Date date = new Date();
    private String from;
    private String to;
    private String text;
    private String file;

    public Message(String from, String text) {
        this.from = from;
        this.text = text;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(this);
    }

    public static Message fromJSON(String s) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.fromJson(s, Message.class);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("[").append(date)
                .append(", From: ").append(from).append(", To: ").append(to)
                .append("] ").append(text)
                .toString();
    }

    public int send(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        if (getFile() != null && new File(file).exists()) {
            sendFileOnServer(Utils.getURL() + "/file?fileName="+file+"&to="+to);
        }

        try (OutputStream os = conn.getOutputStream()) {
            String json = toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            return conn.getResponseCode(); // 200?
        }
    }

    public int sendFileOnServer(String url) throws IOException {
        URL obj2 = new URL(url);
        HttpURLConnection conn2 = (HttpURLConnection) obj2.openConnection();

        conn2.setRequestMethod("POST");
        conn2.setDoOutput(true);

        try (OutputStream outputStream = conn2.getOutputStream();
             InputStream inputStream = new FileInputStream(new File(file))) {

            byte[] buffer = new byte[10240];
            while (true) {
                int count = inputStream.read(buffer);
                if (count < 0) {
                    break;
                }
                outputStream.write(buffer, 0, count);
            }
            System.out.println("file save In Directory Server");
            return conn2.getResponseCode();
        }
    }


    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
