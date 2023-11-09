package JavaOOP.HW1;

public class OverloadedMethod {
    char firstChar;
    char secondChar;

    public void getValuesForFields() {
    }

    public void getValuesForFields(char firstChar) {
        this.firstChar = firstChar;
    }

    public void getValuesForFields(char firstChar, char secondChar) {
        this.firstChar = firstChar;
        this.secondChar = secondChar;
    }
}
