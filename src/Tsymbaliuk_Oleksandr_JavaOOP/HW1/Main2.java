package Tsymbaliuk_Oleksandr_JavaOOP.HW1;

/*
2) Описать класс Треугольник. В качестве свойств возьмите длины сторон треугольника.
Реализуйте метод, который будет возвращать площадь этого треугольника. Создайте
несколько объектов этого класса и протестируйте их.
 */
public class Main2 {
    public static void main(String[] args) {
        Triangle triangle1 = new Triangle(4,3,5);
        Triangle triangle2 = new Triangle(4,8,6);
        System.out.println("First triangle have S = " + triangle1.getArea());
        System.out.println("Second triangle have S = " + triangle2.getArea());

    }
}

class Triangle {
    private int a;
    private int b;
    private int c;
    private int area;  // S = √p · (p — a)(p — b)(p — c)
    private int halfPerimeter;
    public Triangle() {
    }
    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public int getHalfPerimeter() {
        halfPerimeter = (a + b + c) / 2;
        return halfPerimeter;
    }
    public int getArea() {
        getHalfPerimeter();
        area = (int) Math.sqrt(halfPerimeter * (halfPerimeter - a) * (halfPerimeter - b) * (halfPerimeter - c));
        return area;
    }
    public int getA() {
        return a;
    }
    public void setA(int a) {
        this.a = a;
    }
    public int getB() {
        return b;
    }
    public void setB(int b) {
        this.b = b;
    }
    public int getC() {
        return c;
    }
    public void setC(int c) {
        this.c = c;
    }
    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", area=" + area +
                ", halfPerimeter=" + halfPerimeter +
                '}';
    }
}
