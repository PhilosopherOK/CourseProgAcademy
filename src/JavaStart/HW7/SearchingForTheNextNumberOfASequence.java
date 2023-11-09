package JavaStart.HW7;


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
















