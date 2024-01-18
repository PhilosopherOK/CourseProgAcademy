package Tsymbaliuk_Oleksandr_JavaOOP.HW14;
/*
Функциональный интерфейс
Comparable
Задание для самостоятельной проработки
1) Создайте класс прямоугольник, с двумя полями - длинной и высотой прямоугольника. Задайте
отношение естественного порядка для объектов этого класса (реализуйте интерфейс Comparable)
на основании площади этого прямоугольника. Используйте эту реализацию для сортировки массива
объектов класса прямоугольник.
2) Реализуйте обобщенный метод поиска максимального элемента в массиве объектов. Для этого
укажите ограничение для параметра типа этого метода как Comparable<T>. Сигнатура такого
метода должна выглядеть следующим образом:
<T extends Comparable<T>> T max(T[] array)
3) Установите отношение естественного порядка для класса Cat на основании длинны имени. Т.е. чем
длиннее имя тем больше объект. Ваша реализация Comparable<Cat> должна быть совместима с
отношением эквивалентности (метод equals при необходимости можете также заменить).
 */


import java.util.Arrays;
import java.util.Objects;

public class MyComparables {
    public static void main(String[] args) {
        Rectangle[] rectangles = new Rectangle[]{
                new Rectangle(5, 78),
                new Rectangle(214, 234),
                new Rectangle(15, 10),
                new Rectangle(18, 32)};
        System.out.println(Arrays.toString(rectangles));
        Arrays.sort(rectangles);
        System.out.println(Arrays.toString(rectangles));

        System.out.println(max(rectangles).toString());

        Cat[] cats = new Cat[]{
                new Cat("Misa", 15),
                new Cat("Kisa", 12),
                new Cat("Duniasha", 13),
                new Cat("Barmalei", 5),
        };
        System.out.println(max(cats).toString());
    }

    //#2
    public static <T extends Comparable<T>> T max(T[] array) {
        T result = array[0];
        for (int i = 1; i < array.length; i++) {
            if (result.compareTo(array[i]) < 0) {
                result = array[i];
            }
        }
        return result;
    }
}


//#1
class Rectangle implements Comparable {
    private int length;
    private int height;
    private int area;

    public Rectangle() {
    }

    public Rectangle(int length, int height) {
        this.length = length;
        this.height = height;
        area = length * height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) throw new NullPointerException();
        if (this.getClass() != o.getClass()) return false;

        Rectangle rectangle = (Rectangle) o;

        return area == rectangle.area;
    }

    @Override
    public int hashCode() {
        return area;
    }

    @Override
    public int compareTo(Object o) {
        Rectangle obj = (Rectangle) o;
        if (o == null) {
            throw new NullPointerException();
        }
        if (this.area > obj.area) {
            return 1;
        } else if (this.area < obj.area) {
            return -1;
        } else return 0;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", height=" + height +
                ", area=" + area +
                '}';
    }
}

//#3
class Cat implements Comparable {
    private String name;
    private int age;

    public Cat(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public Cat() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            throw new NullPointerException();
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Cat other = (Cat) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Object o) {
        Cat cat = (Cat) o;
        if (cat == null) throw new NullPointerException();
        if (this.name.length() > cat.name.length()) {
            return 1;
        } else if (this.name.length() < cat.name.length()) {
            return -1;
        } else {
            return 0;
        }
    }
    @Override
    public String toString() {
        return "Cat [name=" + name + ", age=" + age + "]";
    }


}



















