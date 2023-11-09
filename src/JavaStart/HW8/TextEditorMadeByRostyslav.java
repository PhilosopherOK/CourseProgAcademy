package JavaStart.HW8;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditorMadeByRostyslav {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        File file = new File("Text.txt");
        file.createNewFile();
        writeTextIntoTheFile(file, text);
    }

    public static void writeTextIntoTheFile(File file, String text) {
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
