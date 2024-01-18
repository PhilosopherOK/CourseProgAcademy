package Tsymbaliuk_Oleksandr_JavaOOP.HW13;
/*
Функциональный интерфейс
Function
Задание для самостоятельной проработки
1) С помощью реализации Function<Integer[],Integer> и реализации Predicate<Integer> (да нужно
вспоминать и предыдущую лекцию ☺) реализуйте функцию которая вернет количество простых
чисел в массиве целых чисел. Например [5,6,7,8,9,10] => 2
2) Опишите такую реализацию Function<String, Integer> которая вернет количество согласных букв в
строке текста.
3) Используя композицию функций реализуйте функцию которая вернет сумму кодов каждого
символа в произвольной строке.
4) Опишите такую реализацию BiFunction<String,String,String[]> которая вернет в виде массива
слова которые одновременно присутствуют и в первой строке и во второй (слова разделены
пробелами). Например text1 = «Hello Java», text2 = «Hello Python» в результате должен быть
массив вида [«Hello»].
5) Используя примитивную специализацию Function реализуйте функционал который на основе
объекта типа java.util.Calendar вернет целое число значение которого равно году хранимому в
Calendar.
 */


import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyFunctions {
    //#1
    public static Integer howMuchPrimeNumbers(Integer[] arrayOfNum) {
        Set<Integer> primeNumbers = Set.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
                61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157,
                163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257,
                263, 269, 271);
        Integer couter = 0;

        Predicate<Integer> predicate = n -> primeNumbers.contains(n);
        Function<Integer, Boolean> function = p -> {
            return predicate.test(p);
        };

        for (int i = 0; i < arrayOfNum.length; i++) {
            if(function.apply(arrayOfNum[i])){
                couter++;
            }
        }
        return couter;
    }

    //#2
    public static Integer numberOfConsonantsInLineOfText(String text) {
        Function<String, Integer> function = t -> {
            int counter = 0;
            Set<Character> consonants = Set.of('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n',
                                                'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z');
            char[] chars = t.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (consonants.contains(chars[i])) {
                    counter++;
                }
            }
            return counter;
        };
        return function.apply(text);
    }

    //#3
    public static Integer theSumOfTheCodesOfEachSymbol(String str) {
        Function<String, char[]> f1 = s -> s.toCharArray();
        Function<char[], Integer> f2 = chars -> {
            int sum = 0;
            for (int i = 0; i < chars.length; i++) {
                sum += chars[i];
            }
            return sum;
        };
        Function<String, Integer> fResult = f1.andThen(f2);
        return fResult.apply(str);
    }

    //#4
    public static BiFunction<String, String, String[]> containsStrings() {
        BiFunction<String, String, String[]> biFunction = (s1, s2) -> {
            String[] words1 = s1.split(" ");
            String[] words2 = s2.split(" ");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < words1.length; i++) {
                for (int j = 0; j < words2.length; j++) {
                    if (words1[i].contains(words2[j])) {
                        list.add(words1[i]);
                    }
                }
            }
            String[] resultStr = new String[list.size()];
            for (int i = 0; i < resultStr.length; i++) {
                resultStr[i] = list.get(i);
            }
            return resultStr;
        };
        return biFunction;
    }

    //#5
    public static Function<Calendar, Integer> yearInTheCalendar() {
        Function<Calendar, Integer> function = c -> c.get(Calendar.YEAR);
        return function;
    }
}