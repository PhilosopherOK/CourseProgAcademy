package Tsymbaliuk_Oleksandr_JavaOOP.HW15;
/*
Функциональный интерфейс
Comparator
Задание для самостоятельной проработки
1) С помощью реализации Comparator<Cat> отсортируйте массив Cat в порядке возрастания
длинны имени. Т.е. сначала должны идти коты чье имя самое короткое.
2) Используя составной Comparator реализуйте в List<Integer> поиск числа модуль которого
наиболее близок к нулю. Если встретятся два модуль которых одинаково близок к нулю(например
5 и -5) то верните положительное значение.
3) Создайте реализацию Comparator<Integer> для сортировки списка целых чисел. Но сравнение
должно выполняться на основание суммы первой и последней цифры числа. Т.е например список
чисел подобного вида [62,2000,306,55] должен быть отсортирован как [2000, 62, 306,55].
4) Создайте такую реализацию Comparator<Integer> что бы с ее помощью можно было найти
максимальное простое число в списке целых чисел, если такого числа в списке нет то должно
быть возвращено минимальное число.
5) Создайте Comparator<File> для сортировке списка текстовых файлов по количеству знаков
препинания в них. При решении принять, что знаки препинания ограниченны символами {, . - ? !}
и символ пробел.
 */

import Tsymbaliuk_Oleksandr_JavaOOP.HW12.Cat;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyComparators {
    //#1
    public static Comparator<Cat> sortCatByNameL() {
        return (a, b) -> Integer.compare(a.getName().length(), b.getName().length());
    }

    //#2
    public static Integer sortListByConstructComparator(List<Integer> list) {
        Comparator<Integer> comparator1 = (a, b) -> Math.abs(a) - Math.abs(b);
        Comparator<Integer> comparator2 = (a, b) -> a + b;
        Integer result = Collections.min(list, comparator1.thenComparing(comparator2));
        return result;
    }


    //#3
    public static Comparator<Integer> sortListByFirstLastNum() {
        Function<Integer, Integer> function = a -> a.toString().charAt(0) + a.toString().charAt(a.toString().length() - 1);
        return Comparator.comparing(function);
    }

    //#4
    public static Comparator<Integer> searchPrimeNumberOrMin() {
        //I took some nums but not all
        Set<Integer> primeNumbers = Set.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59,
                61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157,
                163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257,
                263, 269, 271);

        Predicate<Integer> predicate = n -> primeNumbers.contains(n);

        Comparator<Integer> comparator1 = (a, b) -> {
            if (predicate.test(a) && predicate.test(b)) {
                return a.compareTo(b);
            } else if (predicate.test(a) && !predicate.test(b)) {
                return 1;
            } else if (!predicate.test(a) && predicate.test(b)) {
                return -1;
            } else if (!predicate.test(a) && !predicate.test(b)) {
                return 0;
            }
            return 0;
        };

        Comparator<Integer> comparator2 = (a, b) -> -Integer.compare(a, b);

        Comparator<Integer> constrComp = comparator1.thenComparing(comparator2);
        return constrComp;
    }


    //#5
    public static Comparator<File> sortFileByCountDot() {
        String[] punctuationMarks = new String[]{",", ".", "-", "\\?", "!", " "};
        Function<File, String> fileToFileName = f -> f.getName();
        Comparator<String> comparator = (s1, s2) -> {
            int counterForS1 = 0;
            int counterForS2 = 0;
            for (int i = 0; i < punctuationMarks.length; i++) {
                if (s1.split(punctuationMarks[i]).length > 1) {
                    String[] temp1 = s1.split(punctuationMarks[i]);
                    counterForS1 += temp1.length - 1;
                }
                if (s2.split(punctuationMarks[i]).length > 1) {
                    String[] temp2 = s2.split(punctuationMarks[i]);
                    counterForS2 += temp2.length - 1;
                }
            }
            return counterForS1 - counterForS2;
        };
        Comparator<File> constrComparator = Comparator.comparing(fileToFileName, comparator);
        return constrComparator;
    }
}
