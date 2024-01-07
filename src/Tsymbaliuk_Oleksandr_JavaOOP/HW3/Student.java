package Tsymbaliuk_Oleksandr_JavaOOP.HW3;

import Tsymbaliuk_Oleksandr_JavaOOP.HW4.CSVConverter;
import java.util.Objects;

public class Student extends Human implements CSVConverter {
    private int id;
    private String groupName;

    public Student() {
    }

    public Student(String name, String lastName, Gender gender, int id) {
        super(name, lastName, gender);
        this.id = id;
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
        return this.groupName + "," + this.getLastName() + "," + this.getGender() + "," + this.id + "," + this.groupName;
    }

    @Override
    public Student fromCSVString(String str) {
        String[] arrayOfStudentValues = str.split(",");
        Student student = new Student(arrayOfStudentValues[0], arrayOfStudentValues[1],
                arrayOfStudentValues[2] == "Man" ? Gender.Man : Gender.Woman, Integer.parseInt(arrayOfStudentValues[3]));
        student.setGroupName(arrayOfStudentValues[4]);
        return student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id
                && Objects.equals(this.getName(), student.getName())
                && Objects.equals(this.getLastName(), student.getLastName())
                && Objects.equals(this.getGender(), student.getGender())
                && Objects.equals(groupName, student.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName, getName(), getLastName(), getGender());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", name='" + getName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", gender=" + getGender() +
                '}';
    }
}
