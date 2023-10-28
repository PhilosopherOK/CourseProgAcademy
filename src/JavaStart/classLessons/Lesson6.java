package JavaStart.classLessons;

import java.util.Scanner;

public class Lesson6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text;
        System.out.println("Input text");
        text = sc.nextLine();
        int count = 0;
        char[] charText = text.toCharArray();
        char[] vowels = new char[]{'a','o','u','e','i'};
        for (int i = 0; i < charText.length; i++) {
            for (int j = 0; j < vowels.length; j++) {
                if(charText[i] == vowels[j]){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
