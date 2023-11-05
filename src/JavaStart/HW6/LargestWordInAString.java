package JavaStart.HW6;

import java.util.Scanner;

public class LargestWordInAString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        searchingMaxWord(str);
    }
    public static int searchingMaxWord(String str){
        String [] massiveStrings = str.split("\s+");
        int max = 0;
        for (int i = 0; i < massiveStrings.length; i++) {
            if(massiveStrings[i].length() > max){
                max = massiveStrings[i].length();
            }
        }
        System.out.println("Largest word in the string equals is "+ max + " letter");
        return max;
    }
}
