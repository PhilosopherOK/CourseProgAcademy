package JavaStart.firstHW;

public class AreaOfTriangle {
    public static void main(String[] args) {
        double sideA = 0.3;
        double sideB = 0.4;
        double sideC = 0.5;

        // semi-perimeter
        double p = 0.5 * (sideA + sideB + sideC);

        // area of a triangle
        double S = Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));
        System.out.println("Area of triangle: " + S);
    }
}
