package JavaStart.HW7;



/*
Дополнительное задание для самостоятельной проработки
1) Существуют такие последовательности чисел
0,2,4,6,8,10,12
1,4,7,10,13
1,2,4,8,16,32
1,3,9,27
1,4,9,16,25
1,8,27,64,125
Реализуйте программу которая выведет следующий член этой последовательности (либо
подобной им). Например пользователь вводит строку 0,5,10,15,20 ответом программы должно быть
число 25 (это тоже арифметическая прогрессия). Если введенная последовательность не является
(арифметической, геометрической прогрессией или степенным рядом) то вывести сообщение о
ошибке.
2) Число-палиндром с обеих сторон (справа налево и слева направо) читается одинаково. Самое
большое число-палиндром, полученное умножением двух двузначных чисел – 9009 = 91 × 99.
Найдите самый большой палиндром, полученный умножением двух трехзначных чисел.
3) Существует массив {1,2,3,4,5} — размер массива может быть произвольным. Напишите программу
которая выведет на экран все возможные комбинации из элементов этого массива. Внимание
повторений и пропусков быть не должно. Подробнее в видео - https://youtu.be/bp5_gvIdLEU
 */

public class SearchingForTheNextNumberOfASequence {
    public static void main(String[] args) {
        int[] neededLine = new int[]{1,8,22,64};
        int result = searchingNextNumberPerSteps(neededLine);
        if (result == 0) {
            System.out.println("The entered sequence is not (arithmetic, geometric progression or power series)");
        } else {
            System.out.println(result);
        }
    }

    public static int searchingNextNumberPerSteps(int[] array) {
        int result = 0;
        int step = 0;
        if ((step = searchForArithmeticProgression(array)) != 0) {
            result = array[array.length - 1] + step;
        } else if ((step = searchForGeometricProgression(array)) != 0) {
            result = array[array.length - 1] * step;
        } else if (searchForPowerSeriesProgression(array)[1] != 0) {
            step = searchForPowerSeriesProgression(array)[0];
            int nextNumberInTheArrayWithoutExponentiation = searchForPowerSeriesProgression(array)[1] + 1;
            result = nextNumberInTheArrayWithoutExponentiation;
            for (int i = 1; i < step; i++) {
                result *= nextNumberInTheArrayWithoutExponentiation;
            }
        }
        return result;
    }

    public static int searchForArithmeticProgression(int[] array) {
        int valueOfStep = array[1] - array[0];
        for (int i = 1; i < array.length - 1; i++) {
            int temp = array[i + 1] - array[i];
            if (valueOfStep == temp) {
                valueOfStep = temp;
            } else {
                return 0;
            }
        }
        return valueOfStep;
    }

    public static int searchForGeometricProgression(int[] array) {
        int valueOfStep = array[1] / array[0];
        for (int i = 1; i < array.length - 1; i++) {
            int temp = array[i + 1] / array[i];
            if (valueOfStep == temp) {
                valueOfStep = temp;
            } else {
                return 0;
            }
        }
        return valueOfStep;
    }

    public static int[] searchForPowerSeriesProgression(int[] array) {
        int[] resultArrFirstStepsSecondValueInEndOfArr = new int[]{0, 0};

        int[] tempResultArray = findStepsAndFirstValueAndPointOfStart(array);
        int steps = tempResultArray[0];
        int firstNumInArrWithoutExponentiation = tempResultArray[1];
        int pointOfStart = tempResultArray[2];

        for (int i = pointOfStart + 1; i < array.length; i++) {
            firstNumInArrWithoutExponentiation++;
            int tempNum = firstNumInArrWithoutExponentiation;
            for (int j = 1; j < steps; j++) {
                tempNum *= firstNumInArrWithoutExponentiation;
            }
            if (tempNum != array[i]) {
                firstNumInArrWithoutExponentiation = 0;
                break;
            }
        }
        resultArrFirstStepsSecondValueInEndOfArr[0] = steps;
        resultArrFirstStepsSecondValueInEndOfArr[1] = firstNumInArrWithoutExponentiation;
        return resultArrFirstStepsSecondValueInEndOfArr;
    }

    public static int[] findStepsAndFirstValueAndPointOfStart(int[] array) {
        int[] resultArrFirstStepsSecondFirstValueThirdPointOfStart = new int[]{0, 0, 0};
        int pointOfStart = 0;
        if (array[0] == 1) {
            pointOfStart = 1;
        }
        int firstNumInArrWithoutExponentiation = 0;
        int steps = 0;
        for (int num = pointOfStart; num < 25; num++) {
            firstNumInArrWithoutExponentiation = num;
            for (steps = 2; steps < 10; steps++) {
                firstNumInArrWithoutExponentiation *= num;
                if (firstNumInArrWithoutExponentiation == array[pointOfStart]) {
                    firstNumInArrWithoutExponentiation = num;
                    num = 25;
                    break;
                }
            }
        }
        resultArrFirstStepsSecondFirstValueThirdPointOfStart[0] = steps;
        resultArrFirstStepsSecondFirstValueThirdPointOfStart[1] = firstNumInArrWithoutExponentiation;
        resultArrFirstStepsSecondFirstValueThirdPointOfStart[2] = pointOfStart;
        return resultArrFirstStepsSecondFirstValueThirdPointOfStart;
    }
}
















