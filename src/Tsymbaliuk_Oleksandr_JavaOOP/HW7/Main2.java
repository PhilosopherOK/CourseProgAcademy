package Tsymbaliuk_Oleksandr_JavaOOP.HW7;
/*
2) Реализуйте программу многопоточного копирования файла
блоками, с выводом прогресса на экран.
*/


import java.io.*;

public class Main2 {
    private static File folderIn = new File("A.csv");
    private static File folderOut = new File("C.csv");
    private static byte[] bytes = new byte[(int) folderIn.length()];

    public static void main(String[] args) throws InterruptedException, IOException {


        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            if (i < threads.length / 2) {
                threads[i] = new Thread(new FolderIn(folderIn, getBytes()));
            } else {
                threads[i] = new Thread(new FolderOut(folderOut, getBytes()));
            }
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
    }

    synchronized static public byte[] getBytes() {
        return bytes;
    }
}

class FolderIn implements Runnable {
    private File file;
    private byte[] bytes;

    FolderIn(File file, byte[] bytes) {
        this.file = file;
        this.bytes = bytes;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " starting reading from file");
        try (InputStream inputStream = new FileInputStream(file)) {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class FolderOut implements Runnable {

    private File file;
    private byte[] bytes;

    public FolderOut(File file, byte[] bytes) {
        this.file = file;
        this.bytes = bytes;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " starting writing from file");
        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}