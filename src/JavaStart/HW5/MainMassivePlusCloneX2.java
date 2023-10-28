package JavaStart.HW5;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class MainMassivePlusCloneX2 {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int[] mainMassive = new int[15];
        int[] cloneMassivePlusX2 = new int[30];
        for (int i = 0; i < mainMassive.length; i++) {
            int randomValue = random.nextInt(15);
            mainMassive[i] = randomValue;
            cloneMassivePlusX2[i] = randomValue;
        }
        for (int i = 0, j = 15; i < mainMassive.length; i++, j++) {
            cloneMassivePlusX2[j] = cloneMassivePlusX2[i] * 2;
        }
        System.out.println(Arrays.toString(mainMassive));
        System.out.println(Arrays.toString(cloneMassivePlusX2));
    }
}
