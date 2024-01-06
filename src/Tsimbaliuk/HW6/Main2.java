package Tsimbaliuk.HW6;

import java.math.BigInteger;
import java.util.*;

/*
2) Написать код для многопоточного подсчета суммы элементов
массива целых чисел. Сравнить скорость подсчета с простым
алгоритмом.
 */
public class Main2 {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        //If i take more than 100_000_000 elements, multithreading give me wrong sum
        int[] array = new int[100000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(10);
        }

        BigInteger resultOfSumArray = BigInteger.ZERO;

        System.out.println("Multithreading timer start");
        long before = System.nanoTime();

        int howMuchThread = 4;
        MyThread2[] myThread2s = new MyThread2[howMuchThread];
        int partOfArray = array.length / howMuchThread;

        for (int i = 0, numOfThread = 0; i < array.length; i += partOfArray, numOfThread++) {
            myThread2s[numOfThread] = new MyThread2(i, i + partOfArray, array);
        }
        for (int i = 0; i < myThread2s.length; i++) {
            myThread2s[i].start();
        }
        for (int i = 0; i < myThread2s.length; i++) {
            myThread2s[i].join();
        }
        for (int i = 0; i < myThread2s.length; i++) {
            resultOfSumArray = resultOfSumArray.add(myThread2s[i].getResultOfSum());
        }
        long after = System.nanoTime();

        System.out.println("Sum from multithreading = " + resultOfSumArray.toString());
        System.out.println("Time from multithreading = " + (after - before));


        System.out.println("Iteration timer start");
        //or this
        long before2 = System.nanoTime();
        int sum2 = 0;
        for (int a = 0; a < array.length; a++) {
            sum2 += array[a];
        }
        long after2 = System.nanoTime();

        System.out.println("Sum from iteration = " + sum2);
        System.out.println("Time from iteration = " + (after2 - before2));

    }
}

class MyThread2 extends Thread {
    private int startIndex;
    private int finishIndex;
    private int[] array;

    private BigInteger resultOfSum = BigInteger.ZERO;

    public MyThread2(int startIndex, int finishIndex, int[] array) {
        this.startIndex = startIndex;
        this.finishIndex = finishIndex;
        this.array = array;
    }

    public BigInteger getResultOfSum() {
        return resultOfSum;
    }

    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " starting to make additions");
        for (int i = startIndex; i < finishIndex; i++) {
            resultOfSum = resultOfSum.add(BigInteger.valueOf(array[i]));
        }
    }

}
