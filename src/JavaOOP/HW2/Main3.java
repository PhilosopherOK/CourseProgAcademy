package JavaOOP.HW2;

/*
Напишіть програму з класом, в якому є символьне поле. В
головному класі опишіть статичний метод, який на основі
символьного аргументу створює об'єкт відповідного класу
 */
public class Main3 {
    public static void main(String[] args) {
        TempClassC tempClassCClass = createCClassWithChar('c');
        System.out.println(tempClassCClass.symbol);
    }
    static TempClassC createCClassWithChar(char symbol){
        TempClassC tempClassC = new TempClassC();
        tempClassC.symbol = symbol;
        return tempClassC;
    }
}

class TempClassC {
    char symbol;
}
