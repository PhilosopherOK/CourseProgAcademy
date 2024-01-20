package Tsymbaliuk_Oleksandr_JavaOOP.HW18;
/*
Функциональный интерфейс
BinaryOperator
Задание для самостоятельной проработки
1)Создайте реализацию BinaryOperator<String> которая будет возвращать самое
длинное слово из двух строк переданных в качестве параметров.
2)Создайте реализацию BinaryOperator<List<Integer>> которая вернет список в котором
будут только те элементы которые одновременно присутствуют в первом и во втором
списке целых чисел использованных в качестве параметров.
3) Создайте реализацию BinaryOperator<List<T extends Comparable<T>> который будет
возвращать список в котором находиться минимальный элемент среди двух списков
использованных в качестве параметра. Например для списков [5,0,3,4] и [10,-2, 5]
нужно вернуть [10, -2, 5] так как элемент -2 минимален среди элементов этих списков.
 */


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;

public class MyBinaryOperators {
    //#1
    public static BinaryOperator<String> getBiggestWordFromStrs() {
        BinaryOperator<String> binaryOperator = (s1, s2) -> {
            Comparator<String> comparator = (str1, str2) -> str1.length() - str2.length();
            List<String> list1 = Arrays.stream(s1.split(" ")).toList();
            List<String> list2 = Arrays.stream(s2.split(" ")).toList();
            String result = Collections.max(list1, comparator).length() > Collections.max(list2, comparator).length() ?
                    Collections.max(list1, comparator) : Collections.max(list2, comparator);
            return result;
        };
        return binaryOperator;
    }

    //#2
    public static BinaryOperator<List<Integer>> similarNumsFromLists() {
        BinaryOperator<List<Integer>> binaryOperator = (list1, list2) -> {
            list1.retainAll(list2);
            return list1;
        };
        return binaryOperator;
    }

    //#3
    public static <T extends Comparable<T>> BinaryOperator<List<T>> getCollectionWithMinNum() {
        BinaryOperator<List<T>> binaryOperator = (l1, l2) -> {
            if (Collections.min(l1).compareTo(Collections.min(l2)) <= 0) {
                return l1;
            } else {
                return l2;
            }
        };
        return binaryOperator;
    }
}