//package JavaStart.HW6;
//
//import java.util.Scanner;
//
//public class HowMuchBInString {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        String  str = scanner.nextLine();
//        System.out.println(counterBFromString(str));
//    }
//    public static int counterBFromString(String str){
//        char [] chars = str.toLowerCase().toCharArray();
//        int count = 0;
//        for (int i = 0; i < chars.length; i++) {
//            if(chars[i] == 'b'){
//                count++;
//            }
//        }
//        return count;
//    }
//}