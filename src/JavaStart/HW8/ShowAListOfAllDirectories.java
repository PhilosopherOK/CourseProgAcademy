package JavaStart.HW8;

import java.io.File;
import java.io.IOException;

public class ShowAListOfAllDirectories {
    public static void main(String[] args) throws IOException {
        File folder = new File("Folder");
        folder.mkdirs();
        File file = new File("Folder/text.txt");
        file.createNewFile();

        showAListOfAllDirectories("Folder");
    }

    //    Реализуйте метод который выведет на экран список всех каталогов расположенных в каталоге
//    адрес которого будет параметром этого метода.
    public static void showAListOfAllDirectories(String catalogAddress) {
        File workFolder = new File(catalogAddress);
        File[] files = workFolder.listFiles();

        for (int i = 0; i < files.length; i++) {
            String fileType = "File";
            if (files[i].isDirectory()) {
                fileType = "Folder";
            }
            System.out.println(files[i] + "   " + files[i].length() + "  " + fileType);
        }
    }
}













