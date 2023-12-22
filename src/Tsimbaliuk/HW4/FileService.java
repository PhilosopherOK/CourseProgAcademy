package Tsimbaliuk.HW4;
/*
Потоки ввода вывода
1) Напишите программу, которая скопирует все файлы с заранее определенным расширением
(например, только doc) из одного каталога в другой.

3. Усовершенствуйте класс описывающий группу студентов
добавив возможность сохранения списка студентов в файл.
4. Реализовать обратный процесс — т.е. считать данные о
студентах из файла
 */

import java.io.*;

class Main {
    public static void main(String[] args)  {

        File folderIn = new File("C:/CourseProgAcademy");
        File folderOut = new File("AAAA");
        folderOut.mkdirs();

        try {
            System.out.println(FileService.copyFilesWithDocExtension(folderIn, folderOut, "txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


public class FileService {
    public static long copyFile(File fileIn, File fileOut) throws IOException {
        try (InputStream inputStream = new FileInputStream(fileIn); OutputStream outputStream = new FileOutputStream(fileOut)) {
            return inputStream.transferTo(outputStream);
        }
    }

    public static long copyFilesWithDocExtension(File folderIn, File folderOut, String extension) throws IOException {
        File[] files = folderIn.listFiles();
        long filesCopyCounter = 0;
        for (File file : files) {
            if(file.isFile() && file.getName().endsWith(extension)){
                File fileOut = new File(folderOut, file.getName());
                System.out.println(copyFile(file, fileOut));
                filesCopyCounter++;
            }
        }
        return filesCopyCounter;
    }
}
