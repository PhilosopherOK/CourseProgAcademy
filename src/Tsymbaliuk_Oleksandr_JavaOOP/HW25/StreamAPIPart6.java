package Tsymbaliuk_Oleksandr_JavaOOP.HW25;
/*
Stream API
Часть 6
Аккумулирующие терминальные
методы
Задание для самостоятельной проработки
1) Разбейте строку английского текста по символу пробел. Используя reduce верните суммарное
количество букв в словах длинна которых превышает 4.
2) Используя reduce верните произведение элементов потока полученных на основании списка целых
чисел.
3) Замените с помощью метода reduce терминальный метод max
*/

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamAPIPart6 {
    //#1
    public static int getSumOfLetterFromStr(String engStr) {
        return Arrays.stream(engStr.split(" "))
                .filter(s -> s.length() > 4)
                .reduce(0, (a, b) -> a + b.length(), (a, b) -> a + b);
    }

    //#2
    public static int getSumFromListInts(List<Integer> list) {
        return list.stream().reduce(0, (a, b) -> a + b);
    }

    //#3
    public static <T extends Comparable> T getMaxElement(List<T> list) {
        Optional<T> result = list.stream().reduce((a, b) -> (a.compareTo(b)) >= 0 ? a : b);
        return result.orElse(null);
    }
}
































