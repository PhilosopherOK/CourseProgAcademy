package JavaOOP.HW11;
/*
1. Створіть колекцію з випадкових чисел. Відсортуйте її в порядку зростання. При
порівнянні чисел використати компаратор, який порівнює числа на основі суми
цифр, які входять до складу числа. Наприклад, число 89 буде "більшим" за число
123, оскільки 8+9=17, а 1+2+3=6 і 17>6.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            integerList.add(random.nextInt(5000));
        }
        System.out.println(integerList);
        sortCollection(integerList);
        System.out.println(integerList);
    }

    public static List<Integer> sortCollection(List<Integer> list) {
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                String intInStr1 = integer.toString();
                String intInStr2 = t1.toString();
                String[] firstIntToStr = intInStr1.split("");
                String[] secondIntToStr = intInStr2.split("");
                Integer sumFirstNum = 0;
                Integer sumSecondNum = 0;
                for (int i = 0; i < firstIntToStr.length; i++) {
                    sumFirstNum += Integer.parseInt(firstIntToStr[i]);
                }
                for (int i = 0; i < secondIntToStr.length; i++) {
                    sumSecondNum += Integer.parseInt(secondIntToStr[i]);
                }
                if (sumFirstNum > sumSecondNum) {
                    return 1;
                } else if (sumFirstNum < sumSecondNum) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return list;
    }
}
