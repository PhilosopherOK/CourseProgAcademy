package JavaOOP.HW2;


/*
● Напишіть програму з класом, в якому є два поля (символьне і цілочислове) і метод,
аргументом якому передається об'єкт того ж класу. При виклику методу об'єкту, з
якого викликається метод, присвоюються значення полів об'єкта, переданого
аргументом методу. У об'єкта, переданого аргументом методу, значення полів
збільшуються на одиницю
 */
public class Main1 {
    public static void main(String[] args) {
        SelfExpandingClass selfExpandingClassA = new SelfExpandingClass();
        SelfExpandingClass selfExpandingClassB = new SelfExpandingClass();
        selfExpandingClassB.anInt = 20;
        selfExpandingClassB.aChar = 'c';
        System.out.println(selfExpandingClassA.toString());
        System.out.println(selfExpandingClassB.toString());

        selfExpandingClassA.selfExpanding(selfExpandingClassB);

        System.out.println(selfExpandingClassA.toString());
        System.out.println(selfExpandingClassB.toString());
    }
}
class SelfExpandingClass {
    int anInt = 10;
    char aChar = 'a';
    public void selfExpanding(SelfExpandingClass selfExpandingClass){
        this.anInt = selfExpandingClass.anInt;
        this.aChar = selfExpandingClass.aChar;
        selfExpandingClass.anInt++;
        selfExpandingClass.aChar++;
    }
    @Override
    public String toString() {
        return "{" +
                "anInt=" + anInt +
                ", aChar=" + aChar +
                '}';
    }
}

