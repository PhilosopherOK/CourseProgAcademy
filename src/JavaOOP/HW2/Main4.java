package JavaOOP.HW2;
/*
Напишіть програму з класом, в якому є цілочислове поле. В класі
опишіть перевантажений нестатичний метод, який дозволяє
створювати копію об'єкту, з якого викликається метод (без
аргументів). Якщо метод викликається з цілочисловим аргументом,
то передане аргументом значення присвоюється полю об'єкта, з
якого викликається метод. Значення полю створюваного об'єкта
визначається "старим" значенням поля об'єкта, з якого
викликається метод
 */
public class Main4 {
    public static void main(String[] args) {
        TempClassD tempClassDClass = new TempClassD();
        tempClassDClass.number = 5;

        TempClassD newTempClassDClass = tempClassDClass.createCopyOfD();
        System.out.println(newTempClassDClass.number);

        TempClassD newOldTempClassDClass = newTempClassDClass.createCopyOfD(7);
        System.out.println(newTempClassDClass.number);
        System.out.println(newOldTempClassDClass.number);
    }
}

class TempClassD {
    int number;
    TempClassD createCopyOfD(){
        TempClassD tempClassDClass = new TempClassD();
        tempClassDClass.number = this.number;
        return tempClassDClass;
    }
    TempClassD createCopyOfD(int nextNum){
        TempClassD tempClassDClass = new TempClassD();
        tempClassDClass.number = this.number;
        this.number = nextNum;
        return tempClassDClass;
    }
}
