package Tsimbaliuk.HW2;
/*
1) Создать класс Animal.
У него должны быть поля:
● String ration (чем питается животное)
● String color (какого оно цвета)
● int weight (вес)
 Методы:
● Стандартные (методы получения и установки, toString() и т. д.)
● String getVoice() (подать голос)
● void eat() (есть)
● void sleep() (спать)
2) Создать классы Cat, Dog как подклассы Animal. Добавьте новое поле String name. Переопределите методы
getVoice(), eat(), sleep().
3) Создайте класс Veterinarian.
 Поля:
● String name
 Методы:
● Стандартные
● void treatment(Animal animal) (Лечение животного. Лечить можно и кошек и собак)
 */
public class Main1 {
    public static void main(String[] args) {
        Animal cat = new Cat("milk", "black", 4, "Barsik");
        Animal dog = new Dog("meat", "orange", 15, "Muhtar");
        Veterinarian veterinarian = new Veterinarian("John");
        veterinarian.treatment(cat);
        veterinarian.treatment(dog);
    }
}

class Veterinarian{
    private String name;

    public void treatment(Animal animal){
        if(animal.getClass() == Cat.class){
            System.out.println(name + " treats a " + ((Cat) animal).getName());
        }else if(animal.getClass() == Dog.class){
            System.out.println(name + " treats a " + ((Dog) animal).getName());
        }
    }

    @Override
    public String toString() {
        return "Veterinarian{" +
                "name='" + name + '\'' +
                '}';
    }

    public Veterinarian(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class Dog extends Animal {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void getVoice() {
        System.out.println("Dog say woff woff");
    }

    @Override
    public void eat() {
        System.out.println("Dog is eating");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is sleeping'");
    }

    public Dog(String ration, String color, int weight, String name) {
        super(ration, color, weight);
        this.name = name;
    }

}

class Cat extends Animal {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void getVoice() {
        System.out.println("Cat say Mau mau");
    }
    @Override
    public void eat() {
        System.out.println("Cat is eating");
    }

    @Override
    public void sleep() {
        System.out.println("Cat is sleeping'");
    }

    public Cat(String ration, String color, int weight, String name) {
        super(ration, color, weight);
        this.name = name;
    }
}

class Animal {
    private String ration;
    private String color;
    private int weight;

    public void getVoice() {
        System.out.println("i am saying!");
    }

    public void eat() {
        System.out.println("i am eating");
    }

    public void sleep() {
        System.out.println("i am sleeping ZZZ");
    }

    public Animal(String ration, String color, int weight) {
        this.ration = ration;
        this.color = color;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "ration='" + ration + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }

    public String getRation() {
        return ration;
    }

    public void setRation(String ration) {
        this.ration = ration;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
