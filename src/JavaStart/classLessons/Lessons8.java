package JavaStart.classLessons;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lessons8 {
    public static void main(String[] args) {
        String[] goods = new String[]{"Snikers", "Mars", "Kit-Kat", "Lion"};
        int[] prices = new int[]{30, 30, 35, 20};
        int[] n = new int[]{210, 180, 120, 300};

        File file = new File("report.csv");
        String delimeter = ";";
//        saveReportToSCVFile(file, delimeter, goods, prices, n);

        System.out.println(loadStringFromFile(file));
    }

    public static void saveReportToSCVFile(File file, String delimeter, String[] goods, int[] prices, int[] n) {
        try (PrintWriter pw = new PrintWriter(file)) {

            for (int i = 0; i < goods.length; i++) {
                pw.println(goods[i] + delimeter + prices[i] + delimeter + n[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadStringFromFile(File file) {
        String result = "";
        try (Scanner sc = new Scanner(file)) {

            for (; sc.hasNextLine();) {
                result += sc.nextLine() + System.lineSeparator();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void main1(String[] args) {
        // TODO Auto-generated method stub

        File file1 = new File("abc.txt");

        try {
            file1.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        File folder1 = new File("AAAAA");
        folder1.mkdirs();

        File file2 = new File(folder1, "cccc.docx");

        try {
            file2.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        File newFolder = new File("newFolder");
        folder1.renameTo(newFolder);


        file1.delete();
        file2.delete();

        folder1.delete();


        File workFolder = new File(".");

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
