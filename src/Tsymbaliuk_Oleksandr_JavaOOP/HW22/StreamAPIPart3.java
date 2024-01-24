package Tsymbaliuk_Oleksandr_JavaOOP.HW22;
/*
Stream API
Часть 3
Промежуточные методы для
изменения потока
Задание для самостоятельной проработки
1) Создайте текстовый файл с url адресами. Используя Stream API выделите только те адреса
которые являются доступными на текущий момент.
2) На основе массива рок-исполнителей создайте список из первых (по алфавиту) трех песен.
3) На основе адреса каталога выделите список файлов с расширением «txt».
*/

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class StreamAPIPart3 {
    public static void main(String[] args) throws IOException {
//        File file = new File("URLs.txt");
//        getActiveUrls(file);

//        Singer singer1 = new Singer("Freddie Mercury", new String[]{"We Are the Champions", "Somebody to Love"});
//        Singer singer2 = new Singer("David Bowie", new String[]{"Space Oddity", "Let Me Sleep Beside You", "Suffragette City"});
//        Singer singer3 = new Singer("James Paul McCartney", new String[]{"Can’t Buy Me Love", "Another Girl"});
//        Singer[] rockStars = new Singer[]{singer1, singer2, singer3};
//        System.out.println(getListWithThreeSongFromSingers(rockStars));

//        System.out.println(getListOfTxtFiles(new File("AAAA")));
    }

    //#1
    public static void getActiveUrls(File url) throws IOException {
        Function<String, URLConnection> strToURLCon = s -> {
            try {
                URL url1 = new URL(s);
                URLConnection connection = url1.openConnection();
                return connection;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
        Predicate<URLConnection> activeUrl = u -> {
            try {
                InputStream inputStream = u.getInputStream();
                return true;
            } catch (IOException e) {
                return false;
            }
        };

        Files.lines(Path.of(url.getAbsolutePath()))
                .map(strToURLCon)
                .filter(activeUrl)
                .forEach(URl -> System.out.println("this URL: " + URl.getURL() + " is active !"));
    }

    //#2
    public static List<String> getListWithThreeSongFromSingers(Singer[] singers){
        return Arrays.stream(singers)
                .flatMap( singer -> Arrays.stream(singer.getSongs()))
                .limit(3)
                .collect(Collectors.toList());
    }

    //#3
        public static List<File> getListOfTxtFiles(File folderName){
        return Arrays.stream(folderName.list())
                .filter(fileNameStr -> fileNameStr.endsWith(".txt"))
                .map(file -> new File(file)).collect(Collectors.toList());
    }


}

class Singer {
    private String name;
    private String[] songs;

    public Singer(String name, String[] songs) {
        super();
        this.name = name;
        this.songs = songs;
    }

    public String[] getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Singer [name=" + name + ", songs=" + Arrays.toString(songs) + "]";
    }
}
