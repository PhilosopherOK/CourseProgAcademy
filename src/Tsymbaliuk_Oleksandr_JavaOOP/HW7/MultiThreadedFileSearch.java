package Tsymbaliuk_Oleksandr_JavaOOP.HW7;
/*
3) Реализуйте процесс многопоточного поиска файла в
файловой системе. Т.е. вы вводите название файла и в какой
части файловой системы его искать. Программа должна
вывести на экран все адреса в файловой системе файлов с
таким названием.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiThreadedFileSearch {
    public static void main(String[] args) throws InterruptedException {
        String fileName = "bib.txt";
        File file = new File("\\C:\\textFolder");

        Thread thread = new Thread(new Searcher(file, fileName));
        thread.start();
        thread.join();
    }
}

class Searcher implements Runnable {
    private File[] files;
    private String fileName;
    private List<Thread> threads;
    private boolean searched;

    public Searcher(File path, String fileName) {
        this.fileName = fileName;
        files = path.listFiles();
        threads = new ArrayList<>();
        searched = false;
    }

    public void searching() throws InterruptedException {
        if (files == null) {
            System.out.println("Nothing to search");
            return;
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile() && files[i].getName().equals(fileName)) {
                searched = true;
                System.out.println(Thread.currentThread().getName() + " found " + files[i].getAbsolutePath());
            }
            if (files[i].isDirectory()) {
                File newfile = new File(files[i].getAbsolutePath());
                Thread node = new Thread(new Searcher(newfile, fileName));
                threads.add(node);
            }
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).join();
        }
        if (searched == false) {
            System.out.println(Thread.currentThread().getName() + " did not find this file in his directory :(");
        }
    }

    @Override
    public void run() {
        try {
            searching();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public File[] getFiles() {
        return files;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public boolean isSearched() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }

    @Override
    public String toString() {
        return "Searcher{" +
                "files=" + Arrays.toString(files) +
                ", fileName='" + fileName + '\'' +
                ", threads=" + threads +
                ", searched=" + searched +
                '}';
    }
}

