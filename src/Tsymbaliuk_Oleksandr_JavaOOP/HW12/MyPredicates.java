package Tsymbaliuk_Oleksandr_JavaOOP.HW12;
/*
Функциональный интерфейс
Predicate
Задание для самостоятельной проработки
1) С помощью реализации Predicate<String> реализуйте удаление со списка строк начинающихся с
определенной буквы. Например удалить все строки которые начинаются на букву F.
2) Используя реализацию Predicate<String> реализуйте удаление со списка строк, строки первая
буква которых задается отдельным массивом символов. Например если этот массив содержит
['h','a','t'] то со списка стоит удалить все строки которые начинаются с этих букв.
3) Используя реализацию Predicate<Cat> (в качестве Cat взять класс используемый в лекции) и
методы для логических функций, реализуйте удаление из списка Cat котов возраст которых
меньше определенного значения (задается как параметр), а имя по алфавиту идет после буквы
(задается так же как параметр). Например если первый параметр задан как 5, а второй как 'C' то
будет удален Cat [name=Timka, age=4], Cat [name=Kuzia, age=2].
4) Используя реализацию BiPredicate<Integer, String> реализуйте удаление из Map<Integer, String>
всех пар ключ-значение для которых длинна строки значения не равна ключу этого значения.
Например такая пара как {3: «Hello»} должна быть удалена так как длинна «Hello» не равна 3, а
пара вида {4: «Java»} останется.
5) Создайте такую реализацию IntPredicate которая будет возвращать true в случае когда сумма
цифр числа число четное. Например для 103 — вернет true, так 1+0+3 = 4.
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class MyPredicates {
    // №1
    public static void deleteFromListWithStartLetter(List<String> list, String startLetter) {
        list.removeIf(p -> p.startsWith(startLetter));
    }

    // №2
    public static void deleteFromListWithStartArrLetters(List<String> list, String[] arrStr) {
        for (int i = 0; i < arrStr.length; i++) {
            final int tempIndex = i;
            list.removeIf(p -> p.startsWith(arrStr[tempIndex]));
        }
    }

    // №3
    public static void deleteFromListWithStartingNameAndYearLessWhen(List<Cat> list, int lessWhen, char startingWith) {
        Predicate<Cat> pr = cat -> cat.getName().charAt(0) > startingWith;
        list.removeIf(pr.and(cat -> cat.getAge() < lessWhen));

    }

    // №4
    public static void deleteFromMapWhenValueLengthNEqualsKey(Map<Integer, String> map) {
        BiPredicate<Integer, String> biPredicate = (i, s) -> s.length() != i;
        Map<Integer, String> newMap = new HashMap<>();
        for (var mapKV : map.entrySet()) {
            if (!biPredicate.test(mapKV.getKey(), mapKV.getValue())) {
                newMap.put(mapKV.getKey(), mapKV.getValue());
            }
        }
        map.clear();
        map.putAll(newMap);
    }

    // №5
    public static boolean returnTrueIfSumOfIntNumIsEven(int num) {
        IntPredicate intPredicate = (i) -> {
            Integer newI = i;
            String[] nums = newI.toString().split("");
            int sum = 0;
            for (int j = 0; j < nums.length; j++) {
                sum += Integer.parseInt(nums[j]);
            }
            if (sum % 2 != 0) {
                return true;
            } else {
                return false;
            }
        };
        return intPredicate.test(num);
    }
}





















