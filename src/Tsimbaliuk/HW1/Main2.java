package Tsimbaliuk.HW1;

/*
2) Описать класс Треугольник. В качестве свойств возьмите длины сторон треугольника.
Реализуйте метод, который будет возвращать площадь этого треугольника. Создайте
несколько объектов этого класса и протестируйте их.
 */
public class Main2 {
    public static void main(String[] args) {
        Triangle triangle1 = new Triangle(4,3,5);
        Triangle triangle2 = new Triangle(4,8,6);
        System.out.println("First triangle have S = " + triangle1.getS());
        System.out.println("Second triangle have S = " + triangle2.getS());

    }
}

class Triangle {
    int a;
    int b;
    int c;

    int s;  // S = √p · (p — a)(p — b)(p — c)

    int p;  // half perimeter

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        p = (a + b + c) / 2;
    }

    public int getS() {
        s = (int) Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return s;
    }
}
