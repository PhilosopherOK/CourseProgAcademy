package JavaStart.HW8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class SavingTwoDimensionalArrayToTextFile {
    public static void main(String[] args) throws IOException {
        Random random = new Random();
        int[][] array = new int[5][5];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = random.nextInt(9);
            }
        }
        File file = new File("TextTwoDimensionalArray.txt");
        file.createNewFile();
        writeInTxtFileYourArray(array, file);
    }

    public static void writeInTxtFileYourArray(int[][] array, File file) {
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (int i = 0; i < array.length; i++) {
                printWriter.print("[");
                for (int j = 0; j < array[i].length; j++) {
                    printWriter.print(array[i][j] + "");
                    if (j != array[i].length - 1) {
                        printWriter.print(", ");
                    }
                }
                printWriter.println("]");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
