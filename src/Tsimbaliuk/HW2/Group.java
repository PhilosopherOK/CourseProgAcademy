package Tsimbaliuk.HW2;

import java.util.Arrays;
import java.util.Comparator;

public class Group {

    String groupName;
    Student[] students = new Student[10];


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
                student.setGroupName(groupName);
                return;
            }
        }
        throw new GroupOverflowException();
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public void sortStudentsByLastName() {
        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student, Student student2) {
                if (student.lastName.charAt(0) > student2.lastName.charAt(0)) {
                    return 1;
                }
                if (student.lastName.charAt(0) < student2.lastName.charAt(0)) {
                    return -1;
                }
                return 0;
            }
        });
    }

    @Override
    public String toString() {
        sortStudentsByLastName();
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
