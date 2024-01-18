package Tsymbaliuk_Oleksandr_JavaOOP.HW11;
/*
1) Напишите программу, которая выведет в файл все ссылки, которые содержатся в html документе,
который будет прислан в результате запроса на произвольный URL.
2) Проверить доступность сайтов указанных в отдельном файле.
 */

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class SaveStringsFromURL {
    public static void main(String[] args) throws IOException {
        String spec = "https://tomcat.apache.org/";
        getStringFromURLAndSave(spec);
    }

    public static void getStringFromURLAndSave(String spec) throws IOException {
        URL url = new URL(spec);
        URLConnection connection = url.openConnection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
             PrintWriter printWriter = new PrintWriter(new File("InfoFromURLhw11.txt"))) {
            while (true) {
                String temp = br.readLine();
                if (temp == null) {
                    break;
                }
                printWriter.println(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
