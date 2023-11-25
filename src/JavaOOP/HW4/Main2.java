package JavaOOP.HW4;

/*
2. Напишіть програму, в якій створюється клас для опису такого фізичного
об'єкта, як "паралелепіпед". У класа три поля: ширина, глибина та висота, а також
метод для обчислення об'єму (добуток значень полів об'єкта). Передбачити
наявність конструктора та методу для відображення характеристик об'єкта.
Розгляньте механізм успадкування, створивши на основі класу підклас із
додатковим полем, що визначає масу "паралелепіпеда". Додати в підклас метод
для обчислення густини матеріалу, з якого зроблений "паралелепіпед" (масу
потрібно поділити на об’єм). Подумайте над тим, як додати до класа методи для
виконання деяких простих операцій з об'єктами: наприклад, метод зменшення
маси "паралелепіпеда" у певну кількість разів, або метод для створення нового
об'єкта на основу двох вже існуючих. Об’єм створеного об'єкта дорівнює сумі
об’ємів вихідних об'єктів, його маса дорівнює сумі мас вихідних об'єктів, а три
лінійних розміри однакові.
 */
public class Main2 {
    public static void main(String[] args) {
        ParallelepipedWithWeight parallelepipedWithWeight1 = new ParallelepipedWithWeight(2, 2, 2, 4);
        ParallelepipedWithWeight parallelepipedWithWeight2 = new ParallelepipedWithWeight(2, 2, 2, 4);
        parallelepipedWithWeight1.weightLoss(2);
        parallelepipedWithWeight1.show();
        parallelepipedWithWeight2.show();

        ParallelepipedWithWeight parallelepipedWithWeight3 = ParallelepipedWithWeight.newX2Parallelepiped(parallelepipedWithWeight1, parallelepipedWithWeight2);
        parallelepipedWithWeight3.show();

    }
}

class LikeAParallelepiped {
    double width, depth, height;

    public double volumeOfFigure() {
        return width * depth * height;
    }

    public void show() {
        System.out.println("width = " + width + "\n" + "depth = " + depth + "\n" + "height = " + height + "\n" + "volume = " + volumeOfFigure());
    }


    public LikeAParallelepiped(double width, double depth, double height) {
        this.width = width;
        this.depth = depth;
        this.height = height;
    }
}

class ParallelepipedWithWeight extends LikeAParallelepiped {
    double weight;

    public double densityOfMaterial() {
        double densityOfMaterial = weight / super.volumeOfFigure();
        return densityOfMaterial;
    }

    public void weightLoss(int howManyTimesToReduce) {
        weight = weight / howManyTimesToReduce;
    }

    public static ParallelepipedWithWeight newX2Parallelepiped(ParallelepipedWithWeight p1, ParallelepipedWithWeight p2) {
        double tempVolumeOfFigure = p1.volumeOfFigure() + p2.volumeOfFigure();
        double tempParameter = Math.cbrt(tempVolumeOfFigure);

        ParallelepipedWithWeight newX2Parallelepiped = new ParallelepipedWithWeight(tempParameter, tempParameter, tempParameter, p1.weight + p2.weight);
        return newX2Parallelepiped;
    }

    @Override
    public void show() {
        super.show();
        System.out.println("weight = " + weight + "\n" + "density of material = " + densityOfMaterial());
    }

    public ParallelepipedWithWeight(double width, double depth, double height, double weight) {
        super(width, depth, height);
        this.weight = weight;
    }
}
