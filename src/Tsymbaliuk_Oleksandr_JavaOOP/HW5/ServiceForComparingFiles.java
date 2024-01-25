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

class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first file address");
        File file1 = new File(scanner.nextLine());
        System.out.println("Enter second file address");
        File file2 = new File(scanner.nextLine());
        ServiceForComparingFiles.getByteFromFile(file1, file2);
    }
}

public class ServiceForComparingFiles {
    public static boolean getByteFromFile(File file1, File file2) {
        byte[] bytesFromFile1 = new byte[1_000_000];
        byte[] bytesFromFile2 = new byte[1_000_000];
        int valueFromBytes1 = 0;
        int valueFromBytes2 = 0;
        try (InputStream inputStream1 = new FileInputStream(file1);
             InputStream inputStream2 = new FileInputStream(file2)) {
            for (; ; ) {
                valueFromBytes1 = inputStream1.read(bytesFromFile1);
                valueFromBytes2 = inputStream2.read(bytesFromFile2);
                if (!Arrays.equals(bytesFromFile1, bytesFromFile2)) {
                    System.out.println("Files are not identical");
                    return false;
                }
                if (valueFromBytes1 <= 0 || valueFromBytes2 <= 0) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Files are identical !");
        return true;
    }
}
