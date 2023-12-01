package JavaOOP.HW8;

/*
2. Напишіть програму, в якій створюється двовимірний цілочисловий масив.
Програма знаходить в кожному рядку масиву елемент з найбільшим значенням.
Пошук елементу з найбільшим значенням в кожному рядку виконується окремим
потоком
*/
public class Main2 {
    public static void main(String[] args) {
        int[][] array = new int[5][5];
        int counter = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = counter++;
            }
        }
        for (int i = 0; i < array.length; i++) {
            int[] singleArray = array[i];
            MyThread myThread = new MyThread(i, singleArray);
            myThread.start();
        }
    }
}

class MyThread extends Thread {
    int index;
    int[] array;
    int max;

    public MyThread(int index, int[] array) {
        this.index = index;
        this.array = array;
    }

    @Override
    public void run() {
        max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        System.out.println("Max number from " + index + " string = " + max);
    }
}
