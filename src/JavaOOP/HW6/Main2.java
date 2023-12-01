package JavaOOP.HW6;
/*
2. Напишіть програму, в якій є клас з закритим текстовим полем і два відкритих
методи: один дозволяє присвоїти значення полю, інший результатом повертає
значення цього поля. Створіть два функціональних інтерфейси, абстрактні методи
в яких відповідають описанню методів у класі. Створіть об’єкт класу. Для кожного
з інтерфейсів створіть інтерфейсну змінну і значенням присвойте посилання на
відповідний метод об’єкта.
 */
public class Main2 {
    public static void main(String[] args) {
        ClassWithString classWithString = new ClassWithString();
        GetStr getStrFunctional = classWithString::getStr;
        SetStr setStrFunctional = (str) -> classWithString.setStr(str);
    }
}

@FunctionalInterface
interface GetStr {
    public String getStr();
}

@FunctionalInterface
interface SetStr {
    public void setStr(String str);
}

class ClassWithString {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}