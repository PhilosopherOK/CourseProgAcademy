package JavaStart.HW6;

public class TenValueOfP {
    public static void main(String[] args) {
        printTenValueOfP();
    }
    public static void printTenValueOfP(){
        for (int i = 2; i < 12; i++) {
            System.out.printf("%." + i + "f", Math.PI);
            System.out.println();
        }
    }
}
