package Tsymbaliuk_Oleksandr_JavaOOP.HW5;
/*
1) Реализуйте сервис для сравнения файлов на идентичность. Файлы считаются идентичными если
они побайтово равны. Файлы разной длинны, или файлы в которых хотя бы один байт отличен
считаются разными.
2) Дополните полученный сервис возможностью передачи адресов файлов в ключевом режиме при
запуске приложения из командной строки.
 */

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first address");
        File file1 = new File(scanner.nextLine());
        System.out.println("Enter second address");
        File file2 = new File(scanner.nextLine());

        Function<File, byte[]> function = ServiceForComparingFiles::getByteFromFile;

        System.out.println(ServiceForComparingFiles.comparingFiles(function.apply(file1), function.apply(file2)));
    }
}

public class ServiceForComparingFiles {
    public static boolean comparingFiles(byte[] bytes1, byte[] bytes2) {
        return Arrays.equals(bytes1, bytes2);
    }

    public static byte[] getByteFromFile(File file) {
        byte[] bytesFromFile = null;
        try (InputStream inputStream = new FileInputStream(file)) {
            bytesFromFile = inputStream.readAllBytes();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return bytesFromFile;
    }
}


/*
package IOcompareTwoFiles;

import java.io.*;
import java.util.Arrays;

public class TwoFilesComparator {
    public boolean compareTwoFiles(File file1, File file2){
        int readBytes1 = 0;
        int readBytes2 = 0;
        try (InputStream reader1 = new FileInputStream(file1);
             InputStream reader2 = new FileInputStream(file2)) {

            byte[] mas1 = new byte[1_000_000];
            byte[] mas2 = new byte[1_000_000];
            while (true){
                readBytes1 = reader1.read(mas1);
                readBytes2 = reader2.read(mas2);
                if(!Arrays.equals(mas1,mas2)){
                    System.out.println("Файлы НЕ одинаковые");
                    return false;
                }
                if(readBytes1 <= 0 && readBytes2 <= 0){
                    break;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Файлы идентичны");
        return true;
    }

    public static void main(String[] args) {
        File f1 = new File("C:\\Users\\User1\\Desktop\\from\\firstFile.txt");
        File f2 = new File("C:\\Users\\User1\\Desktop\\from\\secondFile.txt");
        TwoFilesComparator twoFilesComparator = new TwoFilesComparator();
        twoFilesComparator.compareTwoFiles(f1,f2);
    }
}
 */