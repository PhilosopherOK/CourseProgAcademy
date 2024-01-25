package Tsymbaliuk_Oleksandr_JavaOOP.HW27;
/*
Stream API
Часть 8
Класс Collectors
Задание для самостоятельной проработки
1) Соберите элементы потока целых чисел в две строки (одна для четных чисел, вторая для нечетных
чисел) в качестве разделителя используйте «;».
2) Реализуйте файловый классификатор по размеру файлов.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamAPIPart8 {
    public static void main(String[] args) {
        System.out.println(getTwoStrFromInts(List.of(1, 2, 3, 4, 5, 6, 7)));
        System.out.println(getTireListFromFiles(new File("AAAA")));
    }

    //#1
    public static Map<String, String> getTwoStrFromInts(List<Integer> list) {
        return list.stream().collect(Collectors.toMap(n -> n % 2 == 0 ? "even" : "odd", n -> n + "; ", (n1, n2) -> n1 + n2));
    }

    //#2
    public static Map<String, List<File>> getTireListFromFiles(File folder) {
        Function<File, String> functionKey = file -> {
            if (file.length() > 100_000) {
                return "Large file";
            } else if (file.length() > 10_000) {
                return "Middle file";
            } else {
                return "Small file";
            }
        };
        Function<File, List<File>> functionValue = f -> {
            List<File> list = new ArrayList<>();
            list.add(f);
            return list;
        };
        BinaryOperator<List<File>> mergeLists = (fl1, fl2) -> {
            List<File> newList = new ArrayList<>(fl1);
            newList.addAll(fl2);
            return newList;
        };
        return Arrays.stream(folder.listFiles())
                .filter(f -> f.isFile())
                .collect(Collectors.toMap(functionKey, functionValue, mergeLists));
    }
}





























