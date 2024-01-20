package Tsymbaliuk_Oleksandr_JavaOOP.HW19;
/*
Функциональный интерфейс
Supplier
Задание для самостоятельной проработки
1) Реализуйте с помощью класса Supplier<String> который будет при каждом вызове get()
возвращать слово полученное из строки переданной в качестве параметра конструктору этого
класса. Для упрощения задания выполните разбиение строки по символу пробел.
2) Реализуйте Supplier<String> с помощью Predicate<String> реализующий следующий функционал:
возвращать из List<String> только строки соответствующие реализации Predicate. Например если
у вас есть список [«Hello», «world», «Java»], а Predicate возвращает true только если слово
начинается с большой буквы, ваша реализация Supplier должна последовательно вернуть
«Hello», «Java» и после чего возвращать null (закончились подходящие значения).
3) Реализуйте IntSupplier последовательно возвращающий элемент из массива целых чисел.
 */

import java.util.Arrays;
import java.util.List;
import java.util.function.IntSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;

//#1
public class MySupplier implements Supplier<String> {
    private String string;
    private Integer index;
    private String[] strings;

    public MySupplier() {
    }

    public MySupplier(String string) {
        this.string = string;
        index = 0;
        strings = string.split(" ");
    }

    @Override
    public String get() {
        if (index == strings.length) {
            index = 0;
        }
        return strings[index++];
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    @Override
    public String toString() {
        return "MySupplier{" +
                "string='" + string + '\'' +
                ", index=" + index +
                ", strings=" + Arrays.toString(strings) +
                '}';
    }
}


//#2
class MySupplier2 implements Supplier<String> {
    private List<String> testListStr;
    private int index;
    Predicate<String> predicate;

    public MySupplier2() {
    }

    public MySupplier2(List<String> testListStr, Predicate<String> predicate) {
        this.testListStr = testListStr;
        this.predicate = predicate;
        index = 0;
    }

    @Override
    public String get() {
        for (int i = index; i < testListStr.size(); i++) {
            String result = testListStr.get(index++);
            if (predicate.test(result)) {
                return result;
            }
        }
        return null;
    }

    public List<String> getTestListStr() {
        return testListStr;
    }

    public void setTestListStr(List<String> testListStr) {
        this.testListStr = testListStr;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Predicate<String> getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate<String> predicate) {
        this.predicate = predicate;
    }

    @Override
    public String toString() {
        return "MySupplier2{" +
                "testListStr=" + testListStr +
                ", index=" + index +
                ", predicate=" + predicate +
                '}';
    }
}


//#3
class MyIntSupplier implements IntSupplier {
    private int[] array;
    private int index;

    public MyIntSupplier() {
    }

    public MyIntSupplier(int[] array) {
        this.array = array;
        index = 0;
    }

    @Override
    public int getAsInt() {
        if (index == array.length) {
            index = 0;
        }
        return array[index++];
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "MyIntSupplier{" +
                "array=" + Arrays.toString(array) +
                ", index=" + index +
                '}';
    }
}















