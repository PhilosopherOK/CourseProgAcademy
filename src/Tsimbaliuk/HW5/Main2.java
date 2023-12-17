package Tsimbaliuk.HW5;

import java.util.*;

/*
2) Написать код для многопоточного подсчета суммы элементов
массива целых чисел. Сравнить скорость подсчета с простым
алгоритмом.
 */
class MyThread2 extends Thread {
    int index;
    int[] array;
    long[] sum;
    int partOfArray;


    public MyThread2(int index, int[] array, long[] sum, int partOfArray) {
        this.index = index;
        this.array = array;
        this.sum = sum;
        this.partOfArray = partOfArray;
    }


    @Override
    public void run() {
        int tempSum = 0;
        for (int i = index; i < array.length / partOfArray; i++) {
            tempSum += array[i];
        }
        sum[0] += tempSum;
    }
}

public class Main2 {
    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        //CTRL + /      это убрать коментарий с выделенного текста

        //this
        int howMuchThread = 2;
        long before = System.currentTimeMillis();
        long[] sum = new long[1];
        MyThread2[] myThread2s = new MyThread2[howMuchThread];
        int partOfArray = howMuchThread;
        for (int i = 0, numOfThread = 0; i < array.length; i += array.length / howMuchThread, numOfThread++) {
            myThread2s[numOfThread] = new MyThread2(i, array, sum, partOfArray--);
        }
        for (int i = 0; i < myThread2s.length; i++) {
            myThread2s[i].start();
        }
        for (int i = 0; i < myThread2s.length; i++) {
            myThread2s[i].join();
        }

        long after = System.currentTimeMillis();

        System.out.println(Arrays.toString(sum));
        System.out.println(after - before);

        Thread main = Thread.currentThread();
        main.join(1000);
        System.out.println("next timer");
        //or this
        long before2 = System.currentTimeMillis();
        int sum2 = 0;
        for (int a = 0; a < array.length; a++) {
            sum2 += array[a];
        }
        long after2 = System.currentTimeMillis();

        System.out.println(sum2);
        System.out.println(after2 - before2);

    }
}

/*
ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(array.length / howMuchThread);
        for (int i = 0; i < array.length; i += array.length / howMuchThread) {
            threadPoolExecutor.execute(new MyThread2(i, array, sum, howMuchThread));
        }
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(100000, TimeUnit.HOURS);
 */