package Tsimbaliuk.HW3;

import Tsimbaliuk.HW4.WriteStudent;

/*
1) Создать класс Human.
Поля:
● String name (имя)
● String lastName (фамилия)
● Gender gender (пол. Реализовать с помощью Enum)
 Методы:
● Стандартные (методы получения и установки, toString() и т. д.)
2) Создать класс Student как подкласс Human.
Поля:
● int id (номер зачетки)
● String groupName (название группы где он учится)
Методы:
● Стандартные (методы получения и установки, toString() и т. д.)
3) Создать классы GroupOverflowException, StudentNotFoundException (наследники Exception) в качестве пользовательских
исключений.
4) Создать класс Group
Поля:
● String groupName (название)
● Student[] studens = new Student[10]; (массив из 10 студентов)
 Методы:
● Стандартные (методы получения и установки, toString() и т. д.)
● public void addStudent(Student student) throws GroupOverflowException (метод добавления студента в группу. В случае
добавления 11 студента должно быть возбужденно пользовательское исключение)
● public Student searchStudentByLastName(String lastName) throws StudentNotFoundException (метод поиска студента в группе.
Если студент не найден должно быть возбужденно пользовательское исключение)
● public boolean removeStudentByID(int id) (метод удаления студента по номеру зачетки, вернуть true если такой студент был и он
был удален и false в противном случае)

Продвинутый уровень.
1) Реализуйте метод для сортировки массива студентов по фамилии. Примените его в методе
toString() класса Group что бы получить список студентов в алфавитном порядке.
 */
public class Main {
    public static void main(String[] args) throws GroupOverflowException, StudentNotFoundException {
        Group group = new Group("A");

        group.addStudent(new Student("Nasia1", "Qendzo1", Gender.Man, 1));
        group.addStudent(new Student("Dasia2", "Bendzo2", Gender.Man, 2));
        group.addStudent(new Student("Rasia3", "Cendzo3", Gender.Man, 3));
        group.addStudent(new Student("Xasia4", "Gendzo4", Gender.Man, 4));
        group.addStudent(new Student("Fasia5", "Wendzo5", Gender.Man, 5));
        group.addStudent(new Student("Ssenia1", "Xendzo6", Gender.Man, 6));
        group.addStudent(new Student("Csenia2", "Hendzo7", Gender.Man, 7));
        group.addStudent(new Student("Bsenia3", "Dendzo8", Gender.Man, 8));
        group.addStudent(new Student("Ksenia4", "Gendzo9", Gender.Man, 9));
        Student student = new Student("Azzazay", "Antipoverty", Gender.Woman, 10);
        group.addStudent(student);
        String csvStudent = student.toCSVString();
        System.out.println(csvStudent);
        System.out.println(student.fromCSVString(csvStudent));

        Student student2 = WriteStudent.takeStudent();
        WriteStudent.addedStudentToGroup(student2, group);
        group.addStudent(new Student("Ksenia6", "Gendzo1", Gender.Man, 11));
        System.out.println(group.toString());
    }
}

