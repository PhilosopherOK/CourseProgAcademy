package JavaStart.firstHW;

public class TotalPriceForFuel {
    public static void main(String[] args) {
        double costPerLiterOfFuel = 1.2;
        double litersForHundredKilometer = 8;
        double travelDistance = 120;

        double litersPerKilometer = litersForHundredKilometer / 100 ;

        double totalLitersForFullRoad = travelDistance * litersPerKilometer;

        int totalPriceForFuel = (int) (totalLitersForFullRoad * costPerLiterOfFuel);

        System.out.println("Total price for fuel: " + totalPriceForFuel + " $");
    }
}
