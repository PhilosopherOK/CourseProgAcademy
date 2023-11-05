package JavaStart.HW7;

public class LinearSearchTheElementInArray {
    public static void main(String[] args) {
        int [] array = {1,2,3,123,23,45,6,2,85};
        int neededElement = 3;
        int indexOfElement = elementSearch(neededElement, array);
        if(indexOfElement == -1){
            System.out.println("Not found element");
        }else {
            System.out.println("found an element at index = " + indexOfElement);
        }
    }

    public static int elementSearch(int value, int[] array){
        int indexOfElement = -1;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == value)
                return i;
        }
        return indexOfElement;
    }
}
