package Tsymbaliuk_Oleksandr_JavaOOP.HW8;

/*
1)Реализуйте корректные методы equals, hashCode для классов Человек, Студент и
Группа. OK
2)Реализуйте вспомогательный метод для проверки факта отсутствия эквивалентных
студентов в группе OK
3)Создайте класс-контейнер типа стек (класс в который можно добавлять и удалять
объекты других классов, только в вершину стека), в который можно сохранять объекты
произвольного типа. Создайте стек на основе массива Object.
Реализуйте методы:
● public void push(Object obj) добавления элемента в стек
● public Object pop() получение с удалением элемента из вершины стека
● public Object peek() получение элемента с вершины стека без удаления.
 */

import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(123);
        stack.push(234567);
        System.out.println(stack.toString());
        for (Object integer : stack) {
            System.out.println(integer.toString());
        }
    }
}

class MyStack<T> implements Iterable {
    private T parameter;
    private int index = -1;
    private T[] array;
    public MyStack() {
        array = (T[]) new Object[10];
    }
    public T peek() {
        return array[index];
    }

    public T pop() {
        T o = array[index];
        array[index] = null;
        index--;
        selfReduce();
        return o;
    }

    public void push(T object) {
        selfAggrandizement();
        index++;
        array[index] = object;
    }

    public void selfAggrandizement() {
        if (array[array.length - 1] != null) {
            T[] newArray = Arrays.copyOf(array, array.length * 2);
            array = newArray;
        }
    }

    public void selfReduce() {
        if (array[array.length / 4] != null && array.length != 10) {
            T[] newArray = Arrays.copyOf(array, array.length / 2);
            array = newArray;
        }
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    @Override
    public Iterator iterator() {
        Iterator iterator = new Iterator() {
            int iterIndex = 0;
            @Override
            public boolean hasNext() {
                if(array[iterIndex] == null){
                    return false;
                }
                return true;
            }
            @Override
            public Object next() {
                return array[iterIndex++];
            }
        };
        return iterator;
    }
}
