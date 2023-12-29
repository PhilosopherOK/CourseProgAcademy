package Tsimbaliuk.HW6;


/*
Многопоточное программирование часть 2Файл

1) Существуют три корабля. На каждом из них 10 ящиков груза.
Они одновременно прибыли в порт в котором только два
дока. Скорость разгрузки 1 ящик в 0.5 сек. Напишите
программу которая управляя кораблями позволит им
правильно разгрузить груз.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread[] ships = new Thread[3];

        for (int i = 0; i < 3; i++) {
            ships[i] = new Thread(new Ship(i + 1));

            if (i == 1) {
                for (int j = 0; j < 2; j++) {
                    ships[j].start();
                }
                for (int q = 0; q < 2; q++) {
                    ships[q].join();
                }
            }
            if(i == 2){
                ships[i].start();
                ships[i].join();
            }
        }
    }
}

class Ship implements Runnable {

    private int numberOfShip;

    Ship(int numberOfShip) {
        this.numberOfShip = numberOfShip;
    }

    synchronized
    @Override
    public void run() {
        // unloads 10 boxes
        for (int i = 1; i <= 10; i++) {
            try {
                System.out.println("Ship " + numberOfShip + " unload " + i + " box");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getNumberOfShip() {
        return numberOfShip;
    }
}