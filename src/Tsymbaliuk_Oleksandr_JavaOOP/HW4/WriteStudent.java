package Tsymbaliuk_Oleksandr_JavaOOP.HW4;
/*
Задание для самостоятельной проработки.
Основной уровень.
1) Дополните реализацию группы Студентов (задание прошлой лекции) возможностью сортировки
массива студентов по фамилии. Для этого в класс Группа добавьте метод
sortStudentsByLastName().
2) Создайте отдельный класс который реализует считывание характеристик студента с клавиатуры
(имя, фамилии и т. д.). Создание и возврат студента на основе считанных данных. Используете
методы этого класса для считывания и добавления студента в группу.
 */


import Tsymbaliuk_Oleksandr_JavaOOP.HW3.Gender;
import Tsymbaliuk_Oleksandr_JavaOOP.HW3.Group;
import Tsymbaliuk_Oleksandr_JavaOOP.HW3.GroupOverflowException;
import Tsymbaliuk_Oleksandr_JavaOOP.HW3.Student;

import java.util.Scanner;

public class WriteStudent {
    public static void addedStudentToGroup(Student student, Group group) throws GroupOverflowException {
        group.addStudent(student);

    }

    public static Student takeStudent() {
        Scanner sc = new Scanner(System.in);
        Student student = new Student();

        System.out.println("Enter name for student");
        student.setName(sc.nextLine());
        System.out.println("Enter lastName for student");
        student.setLastName(sc.nextLine());
        System.out.println("Enter gender for student (Man, Woman)");
        switch (sc.nextLine()) {
            case "Man":
                student.setGender(Gender.Man);
                break;
            case "Woman":
                student.setGender(Gender.Woman);
                break;
        }
        System.out.println("Enter id for student");
        student.setId(sc.nextInt());
        return student;
    }
}
