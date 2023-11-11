package JavaOOP.HW2;

import java.util.Random;

/*
Напишіть програму з класом, в якому є поле -
цілочисловий масив. Опишіть перевантажений статичний
метод, котрий на основі об'єкта даного класу повертає
копію масиву (з даного об'єкта), а на основі переданого
аргументом масиву створює об'єкт відповідного класу
 */
public class Main5 {
    public static void main(String[] args) {
        Random random = new Random();
        TempClassE[] tempClassEClassesInArr = new TempClassE[10];
        for (int i = 0; i < tempClassEClassesInArr.length; i++) {
            tempClassEClassesInArr[i] = new TempClassE(random.nextInt(100));
        }
        System.out.println(searchingMaxNumAmongE(tempClassEClassesInArr));

    }

    public static String searchingMaxNumAmongE(TempClassE[] arrayWithTempClassE) {
        int max = arrayWithTempClassE[0].number;
        int indexForResult = 0;
        for (int i = 0; i < arrayWithTempClassE.length; i++) {
            if (max < arrayWithTempClassE[i].number) {
                max = arrayWithTempClassE[i].number;
                indexForResult = i;
            }
        }
        String result = "Index object with max value = " + indexForResult
                + " and his value = " + max;
        return result;
    }
}

class TempClassE {
    int number;

    public TempClassE(int number) {
        this.number = number;
    }
}
