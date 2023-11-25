package JavaOOP.HW4;

/*1. Напишіть програму з класом, у якому є два цілочислових поля. У класі має бути
дві версії конструктора: з двома аргументами та без аргументу. У класі має бути
метод, що дозволяє створювати новий об'єкт того самого класу. Метод
викликається з об'єкта класу та аргументом йому передається об'єкт того ж класу.
Результатом повертається об'єкт, значення полів якого дорівнюють сумі значень
полів вихідних об'єктів.
*/

public class Main1 {
    public static void main(String[] args) {
        SelfSummingClass selfSummingClass1 = new SelfSummingClass(5, 10);
        SelfSummingClass resultSelfSummingClass = selfSummingClass1.selfCreating(new SelfSummingClass(5, 10));
        System.out.println(resultSelfSummingClass.firstNum + " " + resultSelfSummingClass.secondNum);
    }
}

class SelfSummingClass {
    int firstNum;
    int secondNum;

    public SelfSummingClass() {
    }

    public SelfSummingClass(int firstNum, int secondNum) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }

    public SelfSummingClass selfCreating(SelfSummingClass selfSummingClass) {
        return new SelfSummingClass(this.firstNum + selfSummingClass.firstNum,
                this.secondNum + selfSummingClass.secondNum);
    }
}