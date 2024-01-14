package Tsymbaliuk_Oleksandr_JavaOOP.HW7;
/*
2) Реализуйте программу многопоточного копирования файла
блоками, с выводом прогресса на экран.
*/


import java.io.*;

public class Main2 {

    // i cant Integer.MAX_VALUE and Long.MAX_VALUE cause i have "Requested array size exceeds VM limit"
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


/*
class FolderIn extends Thread {

    private int partOfTwo;
    private File file;
    private int sizeOfFile;
    private byte[] partOfBytes;

    public FolderIn(int partOfTwo, File file) {
        this.partOfTwo = partOfTwo;
        this.file = file;
        this.sizeOfFile = (int) file.length();
        partOfBytes = new byte[sizeOfFile/partOfTwo];
    }
    public byte[] getPartOfBytes() {
        return partOfBytes;
    }
    private void getBytesFromFile() {
        try (InputStream inputStream = new FileInputStream(file)) {
            if (partOfTwo == 1) {
                inputStream.skip(0);
            } else {
                inputStream.skip(sizeOfFile / 2);
            }

            for (int i = 0; i < sizeOfFile/2; i++) {
                partOfBytes[i] = (byte) inputStream.read();
            }

//            inputStream.skip(partOfTwo == 1 ? 0l : (long) (sizeOfFile / partOfTwo));
//            inputStream.read(partOfBytes, partOfTwo == 1 ? 0 : (sizeOfFile / partOfTwo),
//                    partOfTwo == 1 ? (sizeOfFile / partOfTwo) : ((sizeOfFile / partOfTwo) + (sizeOfFile / partOfTwo)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        getBytesFromFile();
    }
}


class FolderOut implements Runnable {
    private byte[] partOfArr;
    private File file;
    private int partOfTwo;

    public FolderOut(byte[] bytes, File file,int partOfTwo) {
        this.partOfArr = bytes;
        this.file = file;
        this.partOfTwo = partOfTwo;
    }

    private void pasteFile() {

        try (OutputStream outputStream = new FileOutputStream(file, true)) {
            System.out.println(Arrays.toString(partOfArr));
//            for (int i = 0; i < partOfArr.length; i++) {
                outputStream.write(partOfArr);
//                if(partOfTwo == 1){
//                    outputStream.write(partOfArr, 1, partOfArr.length - 2);
//                }else{
//                    outputStream.write(partOfArr, partOfArr.length-4, (partOfArr.length + partOfArr.length) -4);
//                }
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        pasteFile();
    }
}
*/

//        FolderIn folderInThr = new FolderIn(folderIn);
//        folderInThr.start();
//        FolderOut folderOutThr = new FolderOut(folderOut, folderInThr.getInputStream());
//        folderOutThr.start();
//        folderInThr.join();
//        folderOutThr.join();
//        System.out.println(folderOutThr.getHowMuchTransferTo());


//
//
//class FolderIn extends Thread {
//    private File fileIn;
//
//    FolderIn(File fileIn) {
//        this.fileIn = fileIn;
//    }
//
//    private InputStream inputStream;
//
//    public InputStream getInputStream() {
//        return inputStream;
//    }
//
//    @Override
//    public synchronized void run() {
//        try {
//            inputStream = new FileInputStream(fileIn);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
//
//class FolderOut extends Thread {
//
//    private File fileOut;
//    private InputStream inputStream;
//
//    public FolderOut(File fileOut, InputStream inputStream) {
//        this.fileOut = fileOut;
//        this.inputStream = inputStream;
//    }
//
//    private long howMuchTransferTo;
//
//    public long getHowMuchTransferTo() {
//        return howMuchTransferTo;
//    }
//
//    @Override
//    public synchronized void run() {
//        try (OutputStream outputStream = new FileOutputStream(fileOut)) {
//            howMuchTransferTo = inputStream.transferTo(outputStream);
//            inputStream.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}