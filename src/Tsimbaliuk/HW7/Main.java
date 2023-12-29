package Tsimbaliuk.HW7;

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

public class Main {
    public static void main(String[] args) {
        
    }
}

class MyStack{
    int index;
    Object [] array = new Object[10];

    public MyStack() {
        index = 0;
    }

    public Object peek(){
        return array[index];
    }
    public Object pop(){
        Object o = array[index];
        array[index] = null;
        index--;
        selfReduce();
        return o;
    }
    public void push(Object object){
        selfAggrandizement();
        array[index] = object;
        index++;
    }
    public void selfAggrandizement(){
        if(array[array.length - 1] == null){
            Object[] newArray = Arrays.copyOf(array, array.length * 2);
            array = newArray;
        }
    }
    public void selfReduce(){
        if(array[array.length / 4] == null && array.length != 10){
            Object[] newArray = Arrays.copyOf(array, array.length / 2);
            array = newArray;
        }
    }

}
