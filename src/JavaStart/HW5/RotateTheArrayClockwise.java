package JavaStart.HW5;

import java.util.Random;

public class RotateTheArrayClockwise {
    public static void main(String[] args) {
        int arrayWidthAndLength = 7;
        int[][] array = new int[arrayWidthAndLength][arrayWidthAndLength];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = random.nextInt(10,100);
            }
        }
        // Choose how much turn (default = 1(90 degrees))
        // assign: 2 = 180 degrees, 3 = 270 degrees ...
        int howManyTimesToTurnOver = 1;
        for (int q = 0; q < howManyTimesToTurnOver; q++) {
            for (int u = 0, z = array.length-1; u < array.length / 2; u++, z--) {
                for (int i = u, k = z; i < array.length - 1 - u; i++, k--) {
                    int temp = array[i][z];
                    array[i][z] = array[u][i];
                    array[u][i] = array[k][u];
                    array[k][u] = array[z][k];
                    array[z][k] = temp;
                }
            }
        }
    }
}
