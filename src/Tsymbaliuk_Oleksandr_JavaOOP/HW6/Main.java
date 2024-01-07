package Tsymbaliuk_Oleksandr_JavaOOP.HW6;

/*
1) Создайте сто потоков которые будут вычислять факториал
числа равного номеру этого потока и выводить результат на
экран.
 */
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread[] arrayThreads = new Thread[100];
        for (int i = 1; i <= 100; i++) {
            arrayThreads[i-1] = new Thread(new MyThread(i));
        }
        for (int i = 1; i <= 100; i++) {
            arrayThreads[i-1].start();
        }
        for (int i = 1; i <= 100; i++) {
            arrayThreads[i-1].join();
        }

    }
}

class MyThread implements Runnable {
    int value;
    BigInteger bigInteger;

    public MyThread(int value) {
        this.value = value;
        bigInteger = BigInteger.ONE;
    }

    @Override
    public void run() {
        if (value == 1) {
            System.out.println(bigInteger.toString());
        } else {
            for (int i = 2; i <= value; i++) {
                bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
            }
            System.out.println(bigInteger.toString());
        }
    }
}


