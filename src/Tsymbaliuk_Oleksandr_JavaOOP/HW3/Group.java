package Tsymbaliuk_Oleksandr_JavaOOP.HW3;

import java.util.*;

public class Group {
    private String groupName;

    private ArrayList<Student> students;

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
        students = new ArrayList<>(10);
    }


    public boolean removeStudentByID(int id) throws StudentNotFoundException {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i) != null && students.get(i).getId() == id) {
                students.remove(i);
                return true;
            }
        }
        throw new StudentNotFoundException();
    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i) != null && students.get(i).getLastName().equals(lastName)) {
                return students.get(i);
            }
        }
        throw new StudentNotFoundException();
    }

    public void addStudent(Student student) throws GroupOverflowException {
        if (students.size() < 10) {
            students.add(student);
            student.setGroupName(groupName);
        } else {
            throw new GroupOverflowException();
        }

    }

    public void sortStudentsByLastName() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return student1.getLastName().compareTo(student2.getLastName());
            }
        });
    }

    public boolean checkingIdenticalStudents() {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            for (int j = i + 1; j < students.size(); j++) {
                if (student.equals(students.get(j))) {
                    return true;
                }
            }
        }
        return false;
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

    @Override
    public String toString() {
        sortStudentsByLastName();
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", students=" + students +
                '}';
    }
}
