package Tsymbaliuk_Oleksandr_JavaOOP.HW13Extra123;
/*
Функциональный интерфейс
Function
Задание для самостоятельной проработки
1) С помощью реализации Function<Integer[],Integer> и реализации Predicate<Integer> (да нужно
вспоминать и предыдущую лекцию ☺) реализуйте функцию которая вернет количество простых
чисел в массиве целых чисел. Например [5,6,7,8,9,10] => 2
2) Опишите такую реализацию Function<String, Integer> которая вернет количество согласных букв в
строке текста.
3) Используя композицeию функций реализуйте функцию которая вернет сумму кодов каждого
символа в произвольной строке.
4) Опишите такую реализацию BiFunction<String,String,String[]> которая вернет в виде массива
слова которые одновременно присутствуют и в первой строке и во второй (слова разделены
пробелами). Например text1 = «Hello Java», text2 = «Hello Python» в результате должен быть
массив вида [«Hello»].
5) Используя примитивную специализацию Function реализуйте функционал который на основе
объекта типа java.util.Calendar вернет целое число значение которого равно году хранимому в
Calendar.
 */


import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyFunction {
    public static void main(String[] args) {
        String s = "text";
        System.out.println(theSumOfTheCodesOfEachSymbol(s));
    }

    public static Integer howMuchPrimeNumbers(Integer[] arrayOfNum) {
        Set<Integer> primeNumbers = Set.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
                61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157,
                163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257,
                263, 269, 271);
        Integer couter = 0;
        AtomicInteger next = new AtomicInteger();
        next.set(0);

        Function<Integer[], Integer> function = p -> {
            Integer num = p[next.getAndIncrement()];
            return num;
        };
        Predicate<Integer> predicate = n -> primeNumbers.contains(n);
        for (int i = 0; i < arrayOfNum.length; i++) {
            if (predicate.test(function.apply(arrayOfNum))) {
                couter++;
            }
        }
        return couter;
    }

    //#2
    public static Integer numberOfConsonantsInLineOfText(String text) {
        Function<String, Integer> function = t -> {
            int counter = 0;
            Set<Character> consonants = Set.of('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z');
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
/*
3) Используя композицeю функций реализуйте функцию которая вернет сумму кодов каждого
символа в произвольной строке.
 */
    public static Integer theSumOfTheCodesOfEachSymbol(String str){
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


    //#5
}
