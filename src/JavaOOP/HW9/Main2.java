package JavaOOP.HW9;

/*
3. Напишіть програму, в якій є узагальнений клас з одним узагальненим
параметром. У цього класу є закрите поле, що посилається на масив з елементів
узагальненого типу. Також мають бути методи, які за індексом повертають
значення елемента з масиву, а також присвоюють значення елементу з вказаним
індексом. Перевірити роботу об’єктів, створених на основі цього класу.
 */
public class Main2 {
    public static void main(String[] args) {
        Integer[] array1 = new Integer[]{2, 1, 4, 3, 5, 6};
        String[] array2 = new String[]{"asdf", "qwer", "zxcv"};
        Character[] array3 = new Character[]{'A', 'B', 'C'};

        GeneralizedClass generalizedClass1 = new GeneralizedClass<>(array1);
        GeneralizedClass generalizedClass2 = new GeneralizedClass<>(array2);
        GeneralizedClass generalizedClass3 = new GeneralizedClass<>(array3);

        generalizedClass1.setValueFromIndex(555, 0);
        generalizedClass2.setValueFromIndex("qwerasdfzxcv", 0);
        generalizedClass3.setValueFromIndex('Q', 0);

        System.out.println(generalizedClass1.getValueFromIndex(0));
        System.out.println(generalizedClass2.getValueFromIndex(0));
        System.out.println(generalizedClass3.getValueFromIndex(0));
    }
}

class GeneralizedClass<T> {
    private T[] array;

    public GeneralizedClass(T[] array) {
        this.array = array;
    }

    public T getValueFromIndex(int index) {
        return array[index];
    }

    public void setValueFromIndex(T value, int index) {
        array[index] = value;
    }
}