package JavaOOP.HW9;


/*
2. Напишіть програму з узагальненим методом, у якого є таки аргументи: масив
узагальненого типу, значення того самого узагальненого типу, і цілочислове
значення. Метод присвоює значення (другий аргумент) елементу масиву (перший
аргумент) з індексом, який визначається третім аргументом. Перевірити роботу
методу на масивах різних типів.
 */

import java.util.Arrays;

public class Main1 {
    public static void main(String[] args) {
        Integer[] array1 = new Integer[]{2, 1, 4, 3, 5, 6};
        String[] array2 = new String[]{"asdf", "qwer", "zxcv"};
        Character[] array3 = new Character[]{'A', 'B', 'C'};

        setValueForArray(array1, 555, 1);
        setValueForArray(array2, "qwerasdfzxcv", 1);
        setValueForArray(array3, 'Q', 1);

        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
        System.out.println(Arrays.toString(array3));
    }

    public static <T> void setValueForArray(T[] array, T value, int index) {
        array[index] = value;
    }
}
