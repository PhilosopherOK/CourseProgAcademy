package Tsymbaliuk_Oleksandr_JavaOOP.HW7;
/*
2) Реализуйте программу многопоточного копирования файла
блоками, с выводом прогресса на экран.
*/


import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) throws InterruptedException, IOException {
        File folderIn = new File("A.csv");
        File folderOut = new File("C.csv");

        FolderIn thread1 = new FolderIn(1, folderIn);
        FolderIn thread2 = new FolderIn(2, folderIn);
        Thread thread3 = new Thread(new FolderOut(thread1.getPartOfBytes(), folderOut, 1));
        Thread thread4 = new Thread(new FolderOut(thread2.getPartOfBytes(), folderOut, 2));

        thread2.start();
        thread1.start();
        thread2.join();
        thread1.join();
        System.out.println(folderIn.length());


        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();



//        List<Byte> bytes = new ArrayList<>();
//        try (InputStream inputStream = new FileInputStream(folderIn)) {
//
//            for (int i = 0; i < folderIn.length(); i++) {
//                bytes.add((byte) inputStream.read());
//            }
//            System.out.println(bytes.toString());
//
//        }


    }
}

class FolderIn extends Thread {

    private int partOfTwo;
    private File file;
    private int sizeOfFile;
    private byte[] partOfBytes;

    public FolderIn(int partOfTwo, File file) {
        this.partOfTwo = partOfTwo;
        this.file = file;
        this.sizeOfFile = (int) file.length();
        partOfBytes = new byte[sizeOfFile];
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
        try (OutputStream outputStream = new FileOutputStream(file)) {
            for (int i = 0; i < partOfArr.length; i++) {
                if(partOfTwo == 1){
                    outputStream.write(partOfArr, 1, partOfArr.length - 2);
                }else{
                    outputStream.write(partOfArr, partOfArr.length-4, (partOfArr.length + partOfArr.length) -4);
                }

//                outputStream.write(partOfArr.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        pasteFile();
    }
}


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