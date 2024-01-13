package Tsymbaliuk_Oleksandr_JavaOOP.HW5;

/*1) Реализуйте сервис для сравнения файлов на идентичность. Файлы считаются идентичными если
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

//        Function<File, byte[]> function = ServiceForComparingFiles::getByteFromFile;

        System.out.println(ServiceForComparingFiles.comparingFiles(ServiceForComparingFiles.getByteFromFile(file1),
                ServiceForComparingFiles.getByteFromFile(file2)));
    }
}

public class ServiceForComparingFiles {

    public static boolean comparingFiles(byte[][] bytes1, byte[][] bytes2) {
        return Arrays.equals(bytes1, bytes2);
    }

    public static byte[][] getByteFromFile(File file) {
        byte[] bytes = new byte[(int) file.length()];
//        byte[][] bytes = new byte[Integer.MAX_VALUE][Integer.MAX_VALUE];
        try (InputStream inputStream = new FileInputStream(file)) {
            int index = inputStream.read() / Integer.MAX_VALUE; //TODO


//            inputStream.read(bytes[1]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new byte[][]{bytes};
    }
}
