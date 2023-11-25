package JavaOOP.HW4;

/*
1. Створіть абстрактний клас Animal, який описує свійську тварину. У класу
повинні бути поля: name (назва тварини), color (колір хутра), mass (маса тварини),
age (вік тварини). У класу повинен бути конструктор, яким визначаються значення
полів. Також там мають бути абстрактні методи: voice (повертає текст звуку, який
створює тварина), repeat (повторює звук вказану кількість разів), jump (повертає
результатом число, який визначає відстань, на яку може стрибнути тварина) , а
також неабстрактний метод show (виводить повну інформацію про тварину).
На основі абстрактного класу Animal шляхом спадкування створити класи Cat та
Dog. Перевірити функціональність об’єктів класу.
 */
public class Main3 {
    public static void main(String[] args) {
        Animal cat = new Cat("Betsi", "orange", 5, 12);
        Animal dog = new Dog("Pinky", "pink", 15, 7);
        cat.show();
        cat.voice();
        cat.repeat();
        System.out.println("cat is jump at " + cat.jump() + " milimetrs");
        dog.show();
        dog.voice();
        dog.repeat();
        System.out.println("dog is jump at " + dog.jump() + " milimetrs");
    }
}

abstract class Animal {
    String name;
    String color;
    //In kilo
    int mass;
    int age;

    public Animal(String name, String color, int mass, int age) {
        this.name = name;
        this.color = color;
        this.mass = mass;
        this.age = age;
    }

    public abstract void voice();

    public abstract void repeat();

    //return in milimetrs
    public abstract int jump();

    public void show() {
        System.out.println("name is " + name + "\n" + "color is " + color + "\n" + "mass is " + mass + "\n" + "age is " + age);
    }
}

class Cat extends Animal {

    public Cat(String name, String color, int mass, int age) {
        super(name, color, mass, age);
    }

    @Override
    public void voice() {
        System.out.print("Meow ");
    }

    @Override
    public void repeat() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            voice();
        }
        System.out.println("at 5 am, god damn it !");
    }

    @Override
    public int jump() {
        return 2000;
    }
}

class Dog extends Animal {
    public Dog(String name, String color, int mass, int age) {
        super(name, color, mass, age);
    }

    @Override
    public void voice() {
        System.out.print("Woof ");
    }

    @Override
    public void repeat() {
        System.out.println("");
        for (int i = 0; i < 2; i++) {
            voice();
        }
        System.out.println("something happened !");
    }

    @Override
    public int jump() {
        return 500;
    }
}