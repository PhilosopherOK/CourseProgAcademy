package Tsimbaliuk.HW5;

/*
Многопоточное программирование часть 1Файл
1) Создайте сто потоков которые будут вычислять факториал
числа равного номеру этого потока и выводить результат на
экран.
 */
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

    public MyThread(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        if (value == 1) {
            System.out.println(value);
        } else {
            long result = 1;
            for (int i = 2; i <= value; i++) {
                result *= i;
            }
            System.out.println(result);
        }
    }
}


