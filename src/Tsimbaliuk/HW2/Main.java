package Tsimbaliuk.HW2;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/*
Задание для самостоятельной проработки. Основной уровень.
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

        group.addStudent(new Student("Nasia1", "Gendzo1", Gender.Man, 1, "A"));
        group.addStudent(new Student("Dasia2", "Gendzo2", Gender.Man, 2, "A"));
        group.addStudent(new Student("Rasia3", "Gendzo3", Gender.Man, 3, "A"));
        group.addStudent(new Student("Xasia4", "Gendzo4", Gender.Man, 4, "A"));
        group.addStudent(new Student("Fasia5", "Gendzo5", Gender.Man, 5, "A"));
        group.addStudent(new Student("Ssenia1", "Gendzo6", Gender.Man, 6, "A"));
        group.addStudent(new Student("Csenia2", "Gendzo7", Gender.Man, 7, "A"));
        group.addStudent(new Student("Bsenia3", "Gendzo8", Gender.Man, 8, "A"));
        group.addStudent(new Student("Ksenia4", "Gendzo9", Gender.Man, 9, "A"));
        group.addStudent(new Student("Asenia5", "Gendzo10", Gender.Man, 10, "A"));
//        group.addStudent(new Student("Ksenia6", "Gendzo1", Gender.Man, 11, "A"));

        System.out.println(group.toString());
        System.out.println(group.removeStudentByID(15));
        System.out.println(group.searchStudentByLastName("Gendzo10"));


    }
}

class Group {

    String groupName;
    Student[] students = new Student[10];

    public void sortStudents() {
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student, Student t1) {
                if (student.lastName.charAt(0) > t1.lastName.charAt(0)) {
                    return 1;
                } else if (student.lastName.charAt(0) < t1.lastName.charAt(0)) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }

    public boolean removeStudentByID(int id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].id == id) {
                students[i] = null;
                return true;
            }
        }
        return false;
    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
        for (int i = 0; i < students.length; i++) {
            if (students[i].lastName == lastName)
                return students[i];
        }
        throw new StudentNotFoundException();
    }

    public void addStudent(Student student) throws GroupOverflowException {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                return;
            }
        }
        throw new GroupOverflowException();
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        sortStudents();
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", students=" + Arrays.toString(students) +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}

class GroupOverflowException extends Exception {
    public GroupOverflowException() {
        System.out.println("SO much Students");
    }
}

class StudentNotFoundException extends Exception {
    public StudentNotFoundException() {
        System.out.println("Student Not Found Exception");
    }
}

class Human {
    String name;
    String lastName;
    Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Human(String name, String lastName, Gender gender) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "name = " + name + "" + "\n" + "lastName = " + lastName + "\n" + "gender = " + gender;
    }

}

class Student extends Human {
    int id;
    String groupName;

    public Student(String name, String lastName, Gender gender, int id, String groupName) {
        super(name, lastName, gender);
        this.id = id;
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}

enum Gender {
    Man, Woman;
}
