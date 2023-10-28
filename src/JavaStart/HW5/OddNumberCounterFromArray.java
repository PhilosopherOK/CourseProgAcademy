package JavaStart.HW5;

public class OddNumberCounterFromArray {
    public static void main(String[] args) {
        int[] givenMassive = new int[]{0, 5, 2, 4, 7, 1, 3, 19};
        int counter = 0;
        for (int i = 0; i < givenMassive.length; i++) {
            if(givenMassive[i] % 2 == 1){
                counter++;
            }
        }
        System.out.println(counter);
    }
}
