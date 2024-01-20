package Tsymbaliuk_Oleksandr_JavaOOP.HW16;
/*
Функциональный интерфейс
Consumer
Задание для самостоятельной проработки
1)Реализуйте Consumer<Integer> с помощью которого выведите на экран все нечетные
числа содержащиеся в List<Integer>.
2)Реализуете Consumer<String> с побочным эффектом в виде занесения всех строк
содержащих цифры во вспомогательный список.
3) Реализуйте BiConsumer<String, File> добавляющий строку в конец файла указанного в
качестве параметра.
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MyConsumers {
    private static List<Integer> testListInt = new ArrayList<>();
    private static List<String> testListStr = new ArrayList<>();

    //#1
    public static Consumer<Integer> simplePrinter() {
        Consumer<Integer> consumer = l -> {
            for (int i = 0; i < testListInt.size(); i++) {
                if (testListInt.get(i) % 2 != 0) {
                    System.out.println(testListInt.get(i));
                }
            }
        };
        return consumer;
    }

    //#2
    public static Consumer<String> addStrWithNumToList() {
        Consumer<String> consumer = s -> {
            if (s.split("\\d").length > 1) {
                testListStr.add(s);
            }
        };
        return consumer;
    }

    //#3
    public static BiConsumer<String, File> addLineToFile() {
        BiConsumer<String, File> biConsumer = (s, f) -> {
            try (OutputStream outputStream = new FileOutputStream(f, true)) {
                s = System.lineSeparator() + s;
                outputStream.write(s.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        return biConsumer;
    }

}
