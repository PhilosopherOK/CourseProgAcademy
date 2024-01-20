package Tsymbaliuk_Oleksandr_JavaOOP.HW21;
/*
Stream API
Часть 2.
Промежуточные методы для
фильтрации данных
Задание для самостоятельной проработки
1) Удалите из строки текста все слова длиннее 5 символов.
2) Удалите из строки символы которые не являются буквами английского алфавита.
3) Удалите из List<Cat> всех кошек вес которых меньше 3 кг, отсортируйте их по именам (в
лексикографическом порядке) и соберите результат в новый список Cat.
4) Ниже приведен фрагмент XML документа (pom.xml — используется в Maven для указания
зависимостей)
<dependency>
<groupId>junit</groupId>
<artifactId>junit</artifactId>
<version>4.4</version>
<scope>test</scope>
</dependency>
<dependency>
<groupId>org.powermock</groupId>
<artifactId>powermock-reflect</artifactId>
<version>3.2</version>
</dependency>
Поместите эти строки в массив строк, создайте поток на его основе и выделите значения в теге <groupid> и
соберите результат в список строк.
 */

import Tsymbaliuk_Oleksandr_JavaOOP.HW20.Cat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StreamAPIPart2 {
    //#1
    public static String deleteWordsFromStr(String str) {
        String result = Arrays.stream(str.split(" ")).filter(s -> s.length() <= 5).collect(Collectors.joining(" "));
        return result;
    }

    //#2
    public static String deleteFromStrAnySymbols(String str) {
        Pattern p = Pattern.compile("[a-zA-Z]");
        str = Arrays.stream(str.split("")).filter(p.asPredicate()).collect(Collectors.joining());
        return str;
    }

    //#3
    public static List<Cat> sortLargestCatsFromList(List<Cat> list) {
        return list.stream().filter(cat -> cat.getWeight() < 3).sorted(Comparator.comparing(cat -> cat.getName())).collect(Collectors.toList());
    }

    //#4
    public static List<String> getGroupIDFromXML(File xml) throws IOException {
        String blank = "<groupId>";
        List<String> resultStrings = Files.lines(Path.of(xml.getAbsolutePath())).filter(s -> s.startsWith(blank)).map(s -> s.substring(blank.length(), s.length() - blank.length() - 1)).collect(Collectors.toList());
        return resultStrings;
    }
}
