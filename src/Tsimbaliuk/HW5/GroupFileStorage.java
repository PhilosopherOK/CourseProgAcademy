package Tsimbaliuk.HW5;
/*
2) Реализуйте отдельный класс GroupFileStorage в котором будут следующие методы:
        ● void saveGroupToCSV(Group gr) — запись группы в CSV файл
        ● Group loadGroupFromCSV(File file) — вычитка и возврат группы из файла
        ● File findFileByGroupName(String groupName, File workFolder) — поиск файла в рабочем каталоге
        (workFolder). Название файла определяется названием группы в нем сохраненной.
 */

import Tsimbaliuk.HW3.Group;
import Tsimbaliuk.HW3.GroupOverflowException;
import Tsimbaliuk.HW3.Student;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


class Main2 {
    public static void main(String[] args) throws GroupOverflowException, IOException {
//        Group group = new Group("A");
//        group.addStudent(new Student("Nasia1", "Qendzo1", Gender.Man, 1));
//        group.addStudent(new Student("Dasia2", "Bendzo2", Gender.Man, 2));
//        group.addStudent(new Student("Rasia3", "Cendzo3", Gender.Man, 3));
//        group.addStudent(new Student("Xasia4", "Gendzo4", Gender.Man, 4));
//        group.addStudent(new Student("Fasia5", "Wendzo5", Gender.Man, 5));
//        group.addStudent(new Student("Ssenia1", "Xendzo6", Gender.Man, 6));
//        group.addStudent(new Student("Csenia2", "Hendzo7", Gender.Man, 7));
//        group.addStudent(new Student("Bsenia3", "Dendzo8", Gender.Man, 8));
//        group.addStudent(new Student("Ksenia4", "Gendzo9", Gender.Man, 9));
//        group.addStudent(new Student("Azzazay", "Antipoverty", Gender.Woman, 10));
//        GroupFileStorage.saveGroupToCSV(group);

//        File file = new File("AAAA/A.csv");
//        Group group = GroupFileStorage.loadGroupFromCSV(file);
//        System.out.println(group.toString());

        Group group = new Group("B");
        File workFolder = new File("AAAA");
        File file = GroupFileStorage.findFileByGroupName(group.getGroupName(), workFolder);
        System.out.println(file.getName());
    }
}

public class GroupFileStorage {
    public static void saveGroupToCSV(Group group) throws IOException {
        File file = new File("AAAA/" + group.getGroupName() + ".csv");
        file.createNewFile();
        ArrayList<Student> students = group.getStudents();

        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (int i = 0; i < students.size(); i++) {
                printWriter.println(students.get(i).toCSVString());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Group loadGroupFromCSV(File file) throws FileNotFoundException, GroupOverflowException {
        Group group = new Group(file.getName());
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            Student testStudent = new Student();
            testStudent = testStudent.fromCSVString(scanner.next());

            group.addStudent(testStudent);
        }
        return group;
    }

    public static File findFileByGroupName(String groupName, File workFolder) {
        return new File(workFolder.getName() + groupName + ".csv");
    }
}
