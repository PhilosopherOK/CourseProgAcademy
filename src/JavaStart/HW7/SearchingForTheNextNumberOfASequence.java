package JavaStart.HW7;

public class SearchingForTheNextNumberOfASequence {
    public static void main(String[] args) {
        int[] neededLine = new int[]{1, 8, 27, 64, 115};
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
        } else if ((step = searchForPowerSeriesProgression(array)[0]) != 0) {
            int lastNumberInTheArrayWithoutExponentiation = searchForPowerSeriesProgression(array)[1];
            result = lastNumberInTheArrayWithoutExponentiation;
            for (int i = 1; i < step; i++) {
                result *= lastNumberInTheArrayWithoutExponentiation;
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
        int pointOfStart = 0;
        if(array[0] == 1){
            pointOfStart = 1;
        }
        for (int num = 0; num < 20; num++) {
            int firstNumInArrWithoutExponentiation = num;
            for (int steps = 0; steps < 10; steps++) {

            }
        }


        return null;
    }

    public static int lastNumberInTheArrayWithoutExponentiation(int[] array, int firstNumWithoutExponentiation) {
        for (int i = 0; i < array.length; i++) {
            firstNumWithoutExponentiation++;
        }
        return firstNumWithoutExponentiation;
    }
}
















