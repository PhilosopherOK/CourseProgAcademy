package JavaOOP.HW4;

/*
2. Напишіть програму, в є два статичних методи first() та second(). Аргументом
кожному з методів передається об’єкт. Метод first() викликає з об’єкта методи
void show() та void set(int), а в методі second() з об’єкта викликаються методи
void set() та String get(). Описати клас MyClass, об’єкти якого можна передавати
аргументом кожному з методів. Клас повинен реалізувати два інтерфейсиAlpha та
Bravo. В Alpha оголошені методі show() та set(), а в Bravo оголошені методи show()
та get(). Аргумент методу first() має тип Alpha, а аргумент методу second() має тип
Bravo.
 */
public class Main4 {
    public static void main(String[] args) {
        MyClass myClass = new MyClass(5);
        first(myClass);
        System.out.println();
        second(myClass);
    }

    public static MyClass first(Alpha alpha) {
        System.out.println("Alpha show ");
        alpha.show();
        System.out.println("Alpha set ");
        alpha.set(10);
        return (MyClass) alpha;
    }

    public static MyClass second(Bravo bravo) {
        MyClass myClassBravo = (MyClass) bravo;
        System.out.println("Bravo set ");
        myClassBravo.set(15);
        System.out.println("Bravo get");
        System.out.println("class number now is " + myClassBravo.get());
        return (MyClass) bravo;
    }
}

interface Alpha {
    public abstract void show();

    public abstract void set(int number);
}

interface Bravo {
    public abstract void show();

    public abstract String get();

}

class MyClass implements Alpha, Bravo {
    int number;

    public MyClass(int number) {
        this.number = number;
    }

    @Override
    public void show() {
        System.out.println("class have number is " + number);
    }

    @Override
    public String get() {
        return "" + number;
    }

    @Override
    public void set(int number) {
        this.number = number;
        System.out.println("You set " + number);
    }
}