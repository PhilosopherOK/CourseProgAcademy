package academy.prog;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetFiles {
    public static int downloadAllFilesForTo(String url, String path) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoInput(true);
//getName of file
        String fileName = httpURLConnection.getHeaderField("Content-Disposition");
        if(fileName == null){
            return HttpURLConnection.HTTP_NOT_FOUND;
        }
        String[] strings = fileName.split("=");
        String [] strings1 = strings[strings.length - 1].split("/");
        File file = new File(path + "/" + strings1[strings1.length-1]);
        System.out.println(strings1[strings1.length - 1] +" has been downloaded !");

        try (InputStream inputStream = httpURLConnection.getInputStream();
             OutputStream outputStream = new FileOutputStream(file)) {

            byte[] buffer = new byte[10240];
            while (true) {
                int count = inputStream.read(buffer);
                if (count < 0) {
                    break;
                }
                outputStream.write(buffer, 0, count);
            }
            return HttpURLConnection.HTTP_OK;
        }
    }

    private static byte[] responseBodyToArray(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }


//    public static void getUsers(String url) throws IOException {
//        URL obj = new URL(url);
//        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
//
//        conn.setRequestMethod("GET");
//        conn.setDoInput(true);
//
//
//        try(InputStream is = conn.getInputStream()){
//            byte[] buf = responseBodyToArray(is);
//            String strBuf = new String(buf, StandardCharsets.UTF_8);
//
//            if(!strBuf.equals("")){
//                System.out.println(strBuf);
//            }
//        }
//    }
//
//    private static byte[] responseBodyToArray(InputStream is) throws IOException {
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        byte[] buf = new byte[10240];
//        int r;
//
//        do {
//            r = is.read(buf);
//            if (r > 0) bos.write(buf, 0, r);
//        } while (r != -1);
//
//        return bos.toByteArray();
//    }

}
