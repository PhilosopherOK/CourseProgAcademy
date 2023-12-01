package JavaOOP.HW6;

/*
1. Напишіть програму, в якій має бути функціональний інтерфейс. Абстрактний
метод функціонального інтерфейсу має такі характеристики: у нього два
аргументи (типу String та типу int) і результатом він повертає значення типу char.
Створіть змінну інтерфейсного типу, і як значення присвойте цій змінній лямбда-вираз такий,
щоб метод з функціонального інтерфейсу результат розраховував
так: з тексту, переданого першим аргументом методу, повертається символ з
індексом, який визначається другими аргументом методу.
 */
public class Main1 {
    public static void main(String[] args) {
        String str = "hello";
        int index = 2;
        MyFunctionalInterface result = ((str1, index1) -> str.charAt(index));
    }
}

@FunctionalInterface
interface MyFunctionalInterface {
    public char takeCharFromStr(String str, int index);
}
