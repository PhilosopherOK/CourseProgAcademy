package Tsimbaliuk.HW0;

/*
1) Создайте пользовательский класс для описания товара (предположим, это задел для
интернет-магазина). В качестве свойств товара можете использовать значение цены,
описание, вес товара. Создайте пару экземпляров вашего класса и протестируйте их
работу.

 */
public class Main1 {
    public static void main(String[] args) {
        MyProduct myProduct1 = new MyProduct(250,"shines", 34);
        MyProduct myProduct2 = new MyProduct(1500,"drink", 500);
        MyProduct myProduct3 = new MyProduct(15,"food", 500 );

        System.out.println(myProduct1.toString());
        System.out.println(myProduct2.toString());
        System.out.println(myProduct3.toString());
    }
}

class MyProduct{
    private int price;
    private String description;
    private int weight;
    public MyProduct(int price, String description, int weight) {
        this.price = price;
        this.description = description;
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "MyProduct{" +
                "price=" + price +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                '}';
    }
}
