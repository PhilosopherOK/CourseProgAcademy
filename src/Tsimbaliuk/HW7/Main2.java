package Tsimbaliuk.HW7;

import java.io.*;

/*
2) Реализуйте программу многопоточного копирования файла
блоками, с выводом прогресса на экран.
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        File folderIn = new File("A.csv");
        File folderOut = new File("C.csv");
        folderOut.mkdirs();
        FolderIn folderInThr = new FolderIn(folderIn);
        folderInThr.start();
        FolderOut folderOutThr = new FolderOut(folderOut, folderInThr.getInputStream());
        folderOutThr.start();
        folderInThr.join();
        folderOutThr.join();
        System.out.println(folderOutThr.getHowMuchTransferTo());
    }
}

class FolderIn extends Thread {
    private File fileIn;

    FolderIn(File fileIn) {
        this.fileIn = fileIn;
    }

    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    @Override
    public synchronized void run() {
        try {
            inputStream = new FileInputStream(fileIn);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class FolderOut extends Thread {

    private File fileOut;
    private InputStream inputStream;

    public FolderOut(File fileOut, InputStream inputStream) {
        this.fileOut = fileOut;
        this.inputStream = inputStream;
    }

    private long howMuchTransferTo;

    public long getHowMuchTransferTo() {
        return howMuchTransferTo;
    }

    @Override
    public synchronized void run() {
        try (OutputStream outputStream = new FileOutputStream(fileOut)) {
            howMuchTransferTo = inputStream.transferTo(outputStream);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}