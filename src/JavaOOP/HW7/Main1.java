package JavaOOP.HW7;

import java.util.Scanner;

/*
Напишіть програму-калькулятор, яка дозволяє користувачу вводити
математичні операції (додавання, віднімання, множення, ділення) та виконувати
їх. Програма повинна також вміти обробляти помилки, такі як ділення на нуль або
неправильний формат введених даних.
Умови задачі:
Програма повинна запитувати користувача ввести математичну операцію та два
числа, над якими вона буде виконуватися.
Після виконання операції, програма повинна виводити результат на екран.
Якщо користувач вводить некоректну математичну операцію або неправильний
формат чисел, програма повинна виводити відповідне повідомлення про помилку
і продовжувати роботу.
Якщо користувач вводить ділення на нуль, програма також повинна обробляти цю
помилку та виводити відповідне повідомлення.
Використайте конструкцію try-catch для обробки помилок у програмі.
*/
class BadMathematicalOperationsOrNumberFormat extends Exception {
}

class DivisionByZero extends Exception {
}

public class Main1 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please enter mathematical operations (addition, subtraction, multiplication, division)");
        String mathOperation = scanner.nextLine();
        mathOperation = checkForCorrectOperation(mathOperation);

        System.out.println("Please enter first num");
        int firstNum = checkForCorrectNumberFormat();


        System.out.println("Please enter second num");
        int secondNum = checkForCorrectNumberFormat();
        secondNum = checkForDivisionByZero(mathOperation, secondNum);

        System.out.println(doSomeOperation(mathOperation, firstNum, secondNum));
    }
    public static int checkForCorrectNumberFormat() {
        int num = 0;
        try{
            if(!scanner.hasNextInt()){
                scanner.next();
                throw new BadMathematicalOperationsOrNumberFormat();
            }else{
                num = scanner.nextInt();
            }
        } catch (BadMathematicalOperationsOrNumberFormat e) {
            System.out.println("Bad Mathematical Operations Or Number Format");
            System.out.println("Please enter correct number");
            num = checkForCorrectNumberFormat();
        }
        return num;
    }
    public static int checkForDivisionByZero(String mathOperation, int secondNum) {
        try {
            if (mathOperation.equals("division") && secondNum == 0) {
                throw new DivisionByZero();
            }
        } catch (DivisionByZero divisionByZero) {
            System.out.println("Division by zero is incorrect");
            System.out.println("Please enter correct number");
            secondNum = checkForDivisionByZero(mathOperation, checkForCorrectNumberFormat());
        }
        return secondNum;
    }
    public static String checkForCorrectOperation(String mathOperation) {
        try {
            switch (mathOperation) {
                case "addition", "subtraction", "multiplication", "division":
                    break;
                default:
                    throw new BadMathematicalOperationsOrNumberFormat();
            }
        } catch (BadMathematicalOperationsOrNumberFormat e) {
            System.out.println("Bad Mathematical Operations Or Number Format");
            System.out.println("Please enter mathematical operations (addition, subtraction, multiplication, division)");
            mathOperation = checkForCorrectOperation(scanner.nextLine());
        }
        return mathOperation;
    }
    public static double doSomeOperation(String operation, int firstNum, int secondNum) {
        switch (operation) {
            case "addition":
                return firstNum + secondNum;
            case "subtraction":
                return firstNum - secondNum;
            case "multiplication":
                return firstNum * secondNum;
            case "division":
                return firstNum / secondNum;
        }
        return 0;
    }
}