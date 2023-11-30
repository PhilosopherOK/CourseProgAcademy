package JavaOOP.HW7;

import java.util.Scanner;

/*
Напишіть програму для розв’язання рівняння виду 𝐴𝑥 = 𝐵. Параметри 𝐴 та 𝐵
вводить користувач. Врахувати, що можливі три сценарії:
1) 𝐴 ≠ 0 - рівняння має єдиний розв’язок 𝑥 = 𝐵/𝐴.
2) 𝐴 = 0 та 𝐵 = 0 - рівняння має безліч розв’язків (розв’язком є будь-яке число).
3) 𝐴 = 0 але 𝐵 ≠ 0 - рівняння не має розв’язків.
Використати оброблення помилок.
*/
class BadEntered1 extends Exception {
}

class BadEntered2 extends Exception {
}

class BadEntered3 extends Exception {
}

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter first number");
        int a = scanner.nextInt();
        System.out.println("Please enter second number");
        int b = scanner.nextInt();
        try {
            if (a != 0) {
                throw new BadEntered1();
            } else if (a == 0 && b == 0) {
                throw new BadEntered2();
            } else if (a == 0 && b != 0) {
                throw new BadEntered3();
            }
        } catch (BadEntered1 e) {
            System.out.println("x = " + b / a);
        } catch (BadEntered2 e) {
            System.out.println("The equation has many solutions (the solution is any number)");
        } catch (BadEntered3 e) {
            System.out.println("the equation has no solutions");
        }
    }
}
