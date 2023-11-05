package JavaStart.HW7;

public class MaxValueFromArray {
    public static void main(String[] args) {
        int [] newArray = {5,1,2,44,7,8,12,23,44};
        int maxValue = searchingMaxValueFromArray(newArray);
        System.out.println("Max value from the array = " + maxValue);
    }

    public static int searchingMaxValueFromArray(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (max < array[i])
                max = array[i];
        }
        return max;
    }
}
