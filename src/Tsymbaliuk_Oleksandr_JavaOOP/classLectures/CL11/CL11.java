package Tsymbaliuk_Oleksandr_JavaOOP.classLectures.CL11;
// https://docs.google.com/document/d/1HlgH2P4ILRpahW9kuZblbtA0KYfA1XVyarBifBfbXTg/edit
// сайт с доп уроками и дз
// HEROKU сервер с бесплатными статическими айпишниками



import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;




public class CL11 {
    public static void main(String[] args) {
//        String spec = "https://lms.prog.kiev.ua/pluginfile.php/35360/mod_lesson/page_contents/263/fe10.mp4";
//        try {
//            String html = NetworkService.getStringFromURL(spec, "UTF-8");
//            System.out.println(html);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String spec = "https://lms.prog.kiev.ua/pluginfile.php/35360/mod_lesson/page_contents/263/fe10.mp4";
        File folder = new File("Downloads");
        folder.mkdirs();

        try {
            System.out.println(NetworkService.getFileFromURL(spec, folder));
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String spec = "https://s.dou.ua/CACHE/images/img/announces/dou_news-128-min/97ca2af8a2c54b5064d41c8615998c07.jpg";
//        String spec2 = "https://dou.ua/";
//
//        try {
//            Map<String, List<String>> headers = NetworkService.getHeadersFromURL(spec);
//
//            ((Map<?, ?>) headers).forEach((k, v)-> System.out.println(k+" - "+v));
//
//
//
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
    }
}

