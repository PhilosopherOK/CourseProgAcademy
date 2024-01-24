package Tsymbaliuk_Oleksandr_JavaOOP.HW23;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
Stream API
Часть 4
Промежуточные методы для
изменения порядка потока
Задание для самостоятельной проработки
1) Разбейте строку английского текста по символу пробел. Полученный набор слов отсортируйте в
порядке возрастания количества гласных букв в нем. Слова в которых нет гласных букв стоит
исключить из результата.
2) Из массива студентов (да тех самых из основного в д.з) выберите студентов старше 20 лет, и
отсортируйте их по фамилии. Результат поместите в список.
3) Из списка целых чисел, выделите те значения которых больше 10, отсортируйте по значению
последней цифры результат выведите на экран

 */
public class StreamAPIPart4 {
    public static void main(String[] args) {
        System.out.println(getSortedEngStr("asdfh aettdfg ptrtpg oooeoaoew"));
    }
    //#1
    public static String  getSortedEngStr(String startingStr){
        Set<Character> setOddLetter = Set.of('a', 'e', 'u', 'o', 'i', 'y');
        return Arrays.stream(startingStr.split(" "))
                .map(String::toLowerCase)
                .filter(charactersInStr -> {
                    for (int i = 0; i < charactersInStr.length(); i++) {
                        if(setOddLetter.contains(charactersInStr.charAt(i))){
                            return true;
                        }
                    }
                    return false;
                })
                .sorted((charactersInStr1, charactersInStr2) -> {
                    int counter1 = 0;
                    int counter2 = 0;
                    for (int i = 0; i < charactersInStr1.length(); i++) {
                        if(setOddLetter.contains(charactersInStr1.charAt(i))){
                            counter1++;
                        }
                    }
                    for (int i = 0; i < charactersInStr2.length(); i++) {
                        if(setOddLetter.contains(charactersInStr2.charAt(i))){
                            counter2++;
                        }
                    }
                    return counter1 - counter2;
                })
                .collect(Collectors.joining(" "));
    }


}


























