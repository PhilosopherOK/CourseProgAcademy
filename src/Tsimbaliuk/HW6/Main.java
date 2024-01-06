package Tsimbaliuk.HW6;

/*
1) Создайте сто потоков которые будут вычислять факториал
числа равного номеру этого потока и выводить результат на
экран.
 */
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            MyThread myThread = new MyThread(i);
            myThread.start();
            myThread.join();
        }
    }
}

class MyThread extends Thread {
    int value;
    BigInteger bigInteger = BigInteger.ONE;

    public MyThread(int value) {
        this.value = value;
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


