package Tsimbaliuk.HW5;

/*1) Реализуйте сервис для сравнения файлов на идентичность. Файлы считаются идентичными если
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
        System.out.println("Enter first address");
        File file1 = new File(scanner.nextLine());
        System.out.println("Enter second address");
        File file2 = new File(scanner.nextLine());

        System.out.println(ServiceForComparingFiles.comparingFiles(ServiceForComparingFiles.getByteFromFile(file1),
                ServiceForComparingFiles.getByteFromFile(file2)));
    }
}

public class ServiceForComparingFiles {

    public static boolean comparingFiles(byte[] bytes1, byte[] bytes2) {
        return Arrays.equals(bytes1, bytes2);
    }

    public static byte[] getByteFromFile(File file) {
        byte[] bytes = new byte[10000];
        try (InputStream inputStream = new FileInputStream(file)) {
            inputStream.read(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
    }
}
