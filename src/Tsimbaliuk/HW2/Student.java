package Tsimbaliuk.HW2;

import Tsimbaliuk.HW3.CSVConverter;

import java.util.Objects;

/*
Класс Студент должен реализовывать этот интерфейс. Логика реализации следующая — на
основе Студента создать строку с его CSV представлением и наоборот на основе этой строки
создать Студента.
 */
public class Student extends Human implements CSVConverter {
    public Student() {
    }

    int id;
    String groupName;

    public Student(String name, String lastName, Gender gender, int id) {
        super(name, lastName, gender);
        this.id = id;

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

    @Override
    public String toCSVString() {
        return this.name + "," + this.lastName + "," + this.gender + "," + this.id + "," +  this.groupName;
    }
    @Override
    public Student fromCSVString(String str) {
        String [] arrayOfStudentValues = str.split(",");
        Student student = new Student(arrayOfStudentValues[0], arrayOfStudentValues[1],
                arrayOfStudentValues[2] == "Man"? Gender.Man:Gender.Woman, Integer.parseInt(arrayOfStudentValues[3]));
        student.setGroupName(arrayOfStudentValues[4]);
        return student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(groupName, student.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName);
    }
}
