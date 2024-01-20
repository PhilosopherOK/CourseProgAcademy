package Tsymbaliuk_Oleksandr_JavaOOP.HW20;
/*
Stream API
Часть 1. Вступление
Задание для самостоятельной проработки
1)Используя Stream API и List<Cat> верните самое длинное имя кота в этом списке.
2)Используя Stream API и List<Integer> выделите только нечетные числа, отсортируйте их
по возрастанию и соберите в новый список.
3)Используя Stream API верните адрес файла с максимальным размером в заданном
каталоге.
*/

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamAPIPart1 {
    //#1
    public static String getMaxNameCat(List<Cat> list) {
        Optional<String> result = list.stream().map(cat -> cat.getName()).max((s1, s2) -> s1.length() - s2.length());
        return result.get();
    }

    //#2
    public static List<Integer> getOddNums(List<Integer> list) {
        List<Integer> newList = list.stream().filter(i -> i % 2 != 0).sorted().collect(Collectors.toList());
        return newList;
    }

    //#3
    public static String getAddressFileWithMaxSize(File catalog) {
        Optional<File> resultFile = Arrays.stream(catalog.listFiles()).max((f1, f2) -> Math.toIntExact(f1.length() - f2.length()));
        return resultFile.get().getAbsolutePath();
    }
}

