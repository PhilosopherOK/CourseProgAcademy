package JavaStart.HW6;

import java.util.Scanner;

public class LargestWordInAString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        searchingMaxWord(str);
    }
    public static String searchingMaxWord(String str){
        String [] massiveStrings = str.split("\s+");
        String maxStr = massiveStrings[0];
        for (int i = 1; i < massiveStrings.length; i++) {
            if(maxStr.length() < massiveStrings[i].length()){
                maxStr = massiveStrings[i];
            }
        }
        System.out.println("Largest word in the string is "+ maxStr);
        return maxStr;
    }
}
