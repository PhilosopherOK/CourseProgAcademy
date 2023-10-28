package JavaStart.HW5;

import java.util.Arrays;
import java.util.Scanner;

public class DoItYourselfArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter array size");
        int[] newArray = new int[scanner.nextInt()];
        for (int i = 0; i < newArray.length; i++) {
            System.out.println("Enter " + i + " number");
            int value = scanner.nextInt();
            newArray[i] = value;
        }
        System.out.println(Arrays.toString(newArray));
    }
}
