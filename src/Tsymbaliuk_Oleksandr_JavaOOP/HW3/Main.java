package Tsymbaliuk_Oleksandr_JavaOOP.HW3;

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

1)Реализуйте корректные методы equals, hashCode для классов Человек, Студент и
Группа.
2)Реализуйте вспомогательный метод для проверки факта отсутствия эквивалентных
студентов в группе.
 */
public class Main {
    public static void main(String[] args) throws GroupOverflowException, StudentNotFoundException {
        Group group = new Group("A");

        group.addStudent(new Student("Nasia1", "ABC", Gender.Man, 1));
        group.addStudent(new Student("Nasfa2", "AAAA", Gender.Man, 2));
        group.addStudent(new Student("Rasia3", "Cendzo3", Gender.Man, 3));
        group.addStudent(new Student("Xasia4", "Gendzo4", Gender.Man, 5));
        group.addStudent(new Student("Nasia1", "ABC", Gender.Woman, 1));
//        group.addStudent(new Student("Ssenia1", "Xendzo6", Gender.Man, 6));
//        group.addStudent(new Student("Csenia2", "Hendzo7", Gender.Man, 7));
//        group.addStudent(new Student("Bsenia3", "Dendzo8", Gender.Man, 8));
        Student student = new Student("Azzazay", "verty", Gender.Woman, 10);
        group.addStudent(student);


//        Student student2 = WriteStudent.takeStudent();
//        WriteStudent.addedStudentToGroup(student2, group);
        group.addStudent(new Student("Rasia3", "Cendzo3", Gender.Man, 3));
        System.out.println(group.checkingIdenticalStudents());
        System.out.println(group.toString());
    }
}

