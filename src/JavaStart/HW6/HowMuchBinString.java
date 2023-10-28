package JavaStart.HW6;

import java.util.Scanner;

public class HowMuchBinString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String  str = scanner.nextLine();
        char [] chars = str.toLowerCase().toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == 'b'){
                count++;
            }
        }
        System.out.println(count);
    }
}



