package JavaOOP.HW8;

/*
1. Напишіть програму, в якій створюються два потоки. Обидва потоки працюють
з числовим масивом, створеним у головному потоці. Перший потік розраховує
суму елементів на парних позиціях у масиві (елементи з індексами 0, 2, 4 і так
далі), а другий потік розраховує суму елементів на непарних позиціях у масиві
(елементи з індексами 1,3, 5 і так далі). Результати розрахунків виводяться у
консоль.
 */
public class Main1 {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10};
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int sumOfPairedIndex = 0;
                for (int i = 0; i < array.length; i += 2) {
                    sumOfPairedIndex += array[i];
                }
                System.out.println("Sum Of Paired Numbers = " + sumOfPairedIndex );
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int sumOfUnpairedIndex = 0;
                for (int i = 1; i < array.length; i += 2) {
                    sumOfUnpairedIndex += array[i];
                }
                System.out.println("Sum Of Unpaired Numbers = " + sumOfUnpairedIndex );
            }
        });
        thread1.start();
        thread2.start();
    }
}













