package Tsymbaliuk_Oleksandr_JavaOOP.HW11;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class CheckForWorkingURL {
    public static void main(String[] args) throws IOException {
        File file = new File("URLs.txt");
        checkForWorkingURL(file);
    }

    public static void checkForWorkingURL(File file) throws IOException {
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            URL url = new URL(scanner.nextLine());
            URLConnection connection = url.openConnection();

            try(InputStream inputStream = connection.getInputStream()){
                System.out.println(true);
            }catch (IOException e){
                System.out.println(false);
            }
        }
    }
}
