package Tsymbaliuk_Oleksandr_JavaOOP.classLectures.CL11;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class NetworkService {
    public static String getStringFromURL(String spec, String code) throws IOException {
        URL url = new URL(spec);
        URLConnection connection = url.openConnection();
        String result = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), code))) {
            for (;;) {
                String temp = br.readLine();
                if (temp == null) {
                    break;
                }
                result += temp + System.lineSeparator();
            }
            return result;
        }
    }

    public static long getFileFromURL(String spec, File folder) throws IOException {
        URL url = new URL(spec);
        URLConnection connection = url.openConnection();
        int n = spec.lastIndexOf("/");
        String fileName = spec.substring(n + 1);
        try(InputStream is = connection.getInputStream();
            OutputStream os = new FileOutputStream(new File(folder,fileName))){
            return is.transferTo(os);
        }
    }

    public static Map<String, List<String>> getHeadersFromURL(String spec) throws IOException{
        URL url = new URL(spec);
        URLConnection connection = url.openConnection();
        return connection.getHeaderFields();
    }
}
