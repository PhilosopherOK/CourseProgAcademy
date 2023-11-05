package JavaStart.HW7;

import java.util.Scanner;

public class PrintRectangleFromStars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the length of the rectangle");
        int length = scanner.nextInt();
        System.out.println("Enter the width of the rectangle");
        int width = scanner.nextInt();
        printRectangleWithThisParameters(length, width);
    }

    public static void printRectangleWithThisParameters(int lengthOfRectangle, int widthOfRectangle) {
        for (int i = 0; i < lengthOfRectangle; i++) {
            for (int j = 0; j < widthOfRectangle; j++) {
                System.out.print(" *");
            }
            System.out.println();
        }
    }
}
