package JavaOOP.HW1;

public class OverloadedConstructor {
    int firstNum;
    int secondNum;

    public OverloadedConstructor() {
    }

    public OverloadedConstructor(int firstNum) {
        this.firstNum = firstNum;
    }

    public OverloadedConstructor(int firstNum, int secondNum) {
        this.firstNum = firstNum;
        this.secondNum = secondNum;
    }
}