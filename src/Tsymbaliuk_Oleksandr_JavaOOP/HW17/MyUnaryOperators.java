package Tsymbaliuk_Oleksandr_JavaOOP.HW17;
/*
Функциональный интерфейс
UnaryOperator
Задание для самостоятельной проработки
1) С помощью реализации UnaryOperator замените все нечетные числа в списке целых
чисел на нули.
2) Создайте реализацию UnaryOperator<String> где результатом будет строка в которой
исключены все символы кроме цифровых. Т.е. если входящим параметром является
строка вида «Hello 123 world» результатом будет строка «123».
3) Создайте реализацию UnaryOperator<List<String>> где результатом будет список строк
(созданный на основе списка выступающего в качестве параметра) длинна которых
больше 5 символов.
 */

import java.util.*;
import java.util.function.UnaryOperator;

public class MyUnaryOperators {
    //#1
    public static UnaryOperator<List<Integer>> ifOddWillBeZero() {
        UnaryOperator<List<Integer>> unaryOperator = list -> {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) % 2 != 0) {
                    list.set(i, 0);
                }
            }
            return list;
        };
        return unaryOperator;
    }

    //#2
    public static UnaryOperator<String> deleteAllLetterFromStr() {
        UnaryOperator<String> unaryOperator = s -> {
            StringBuilder sb = new StringBuilder();
            Set<Character> characterSet = Set.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');
            for (int i = 0; i < s.length(); i++) {
                if (characterSet.contains(s.charAt(i))) {
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        };
        return unaryOperator;
    }

    //#3
    public static UnaryOperator<List<String>> deleteStrLessThanFive() {
        UnaryOperator<List<String>> unary = l -> {
            for (int i = 0; i < l.size(); i++) {
                if (l.get(i).length() < 5) {
                    l.remove(i);
                }
            }
            return l;
        };
        return unary;
    }
}
