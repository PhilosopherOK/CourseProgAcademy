package JavaStart.HW5;

import java.util.Arrays;

public class MirrorArray {
    public static void main(String[] args) {
        int [] mainMassive = new int[]{1,2};
        for (int i = 0; i < mainMassive.length / 2; i++) {
            int temp = mainMassive[i];
            mainMassive[i] = mainMassive[(mainMassive.length - 1) - i];
            mainMassive[mainMassive.length - i - 1] = temp;
        }
        System.out.println(Arrays.toString(mainMassive));
    }
}
