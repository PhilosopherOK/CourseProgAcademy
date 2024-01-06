package Tsimbaliuk.HW3;

import java.util.*;

public class Group {

    String groupName;
    ArrayList<Student> students = new ArrayList<>(10);


    public boolean removeStudentByID(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getLastName().equals(lastName)) {
                return students.get(i);
            }
        }
        throw new StudentNotFoundException();
    }

    public void addStudent(Student student) throws GroupOverflowException {
        if (students.get(9) == null) {
            students.add(student);
            student.setGroupName(groupName);
            return;
        }
        throw new GroupOverflowException();
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public void sortStudentsByLastName() {
        Collections.sort(students, new Comparator<Student>() {
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
                ", students=" + students +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupName, group.groupName) && Objects.equals(students, group.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, students);
    }

    public boolean checkingIdenticalStudents() {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            students.remove(student);
            if(students.contains(student)){
                students.add(student);
                return true;
            }else {
                students.add(student);
            }
        }
        return false;
    }
}
