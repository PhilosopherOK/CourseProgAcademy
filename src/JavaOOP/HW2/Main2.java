package JavaOOP.HW2;

/*
Напишіть програму з двома класами. У першого класу є цілочислове поле. У
другого класу є символьне поле.
В першому класі є метод, аргументом якому передається об'єкт другого класу. При
виклику цього методу полю об'єкта, з якого викликається метод, значенням
присвоюється код символу, що є значенням поля об'єкта, переданого аргументом
методу.
У другого класу є метод, аргументом якому передається об'єкт першого класу.
Значенням полю об'єкта, з якого викликається метод, присвоюється символ з кодом,
що визначається значенням поля об'єкта, переданого аргументом методу
 */
public class Main2 {
    public static void main(String[] args) {
        TempClassA tempClassA = new TempClassA();
        TempClassB tempClassB = new TempClassB();
        tempClassA.showInt();
        tempClassB.showChar();

        tempClassA.takeChangeFromB(tempClassB);
        tempClassA.showInt();

        tempClassA.number = 75;
        tempClassB.takeChangeFromA(tempClassA);
        tempClassB.showChar();
    }
}
class TempClassA {
    int number = 37;
    public void takeChangeFromB(TempClassB tempClassB){
        this.number = tempClassB.symbol;
    }
    public void showInt(){
        System.out.println(number);
    }
}
class TempClassB {
    char symbol = 'a';
    public void takeChangeFromA(TempClassA tempClassA){
        this.symbol = (char) tempClassA.number;
    }
    public void showChar(){
        System.out.println(symbol);
    }
}
