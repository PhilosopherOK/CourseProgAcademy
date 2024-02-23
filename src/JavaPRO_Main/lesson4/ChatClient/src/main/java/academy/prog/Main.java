package academy.prog;
/*
1. получать список всех пользователей командой /users   excellent
2. сделать адресные сообщения, /tell "name" бла бла бла
3. сделать пересылку файла, что у адресованного скачалось на комп и в консоле было сообщение скачано тотатота
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter your login: ");
            String login = scanner.nextLine();

            Thread th = new Thread(new GetThread());
            th.setDaemon(true);
            th.start();

            System.out.println("Enter your message: ");
            System.out.println("If u want to tell with someone take his from command /users ," +
                    " and write /tell 'name' bla bla bla");
            System.out.println("If you want to send something to someone, take it from the /users command," +
                    " and write /send 'name' 'path to file'   PS/ pls dont spam files couse i can download only last one");
            while (true) {
                String text = scanner.nextLine();
                if (text.isEmpty()) break;
                Message m = new Message(login, text);
                int resultHttpResponse = filterText(text, m, login);
                if (resultHttpResponse != 200) { // 200 OK
                    System.out.println("HTTP error occurred: " + resultHttpResponse);
                    return;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    public static int filterText(String text, Message m, String login) throws IOException {
        //if command /users take some clients
        if (text.equals("/users")) {
            GetUsers.getUsers(Utils.getURL() + "/add");
            return 200;
        }//If we have some ToClient
        else if (text.startsWith("/tell ")) {
            String toClient = text.split(" ")[1];
            m.setTo(toClient);
            text = text.substring(7 + toClient.length());
            m.setText(text);
            return m.send(Utils.getURL() + "/add");
        }// If we will be sent something
        else if (text.startsWith("/send")) {
            String[] strings = text.split(" ");
            if (strings.length <= 1) {
                System.out.println("Bad Request");
            }
            m.setTo(strings[1]);
            File file = new File(strings[2]);
            if (file.exists()) {
                m.setFile(strings[2]);
            } else {
                System.out.println("File is not exist !");
                return 200;
            }
            m.setText("qwe1 !!! You have been sent a file, to download please enter, /getFile 'folder where to install'");
            return m.send(Utils.getURL() + "/add");
        }//If you see that you have a file to download
        else if (text.startsWith("/getFile")) {
            String[] strings = text.split(" ");
            if (strings.length <= 0) {
                System.out.println("Path error, pls enter right path");
                return 200;
            }
            File file = new File(strings[1]);
            if (!file.exists() && !file.isDirectory()) {
                System.out.println("Path error, pls enter right path");
                return 200;
            } else {
                return GetFiles.downloadAllFilesForTo(Utils.getURL() + "/file?login=" + login, strings[1]);
            }
        } else {
            return m.send(Utils.getURL() + "/add");
        }
    }
}
