package Tsymbaliuk_Oleksandr_JavaOOP.HW5;
/*
2) Реализуйте отдельный класс GroupFileStorage в котором будут следующие методы:
        ● void saveGroupToCSV(Group gr) — запись группы в CSV файл
        ● Group loadGroupFromCSV(File file) — вычитка и возврат группы из файла
        ● File findFileByGroupName(String groupName, File workFolder) — поиск файла в рабочем каталоге
        (workFolder). Название файла определяется названием группы в нем сохраненной.
 */

import Tsymbaliuk_Oleksandr_JavaOOP.HW3.Group;
import Tsymbaliuk_Oleksandr_JavaOOP.HW3.GroupOverflowException;
import Tsymbaliuk_Oleksandr_JavaOOP.HW3.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


class Main2 {
    public static void main(String[] args) throws GroupOverflowException, IOException {
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
            System.out.println(e.getMessage());
        }
    }

    public static Group loadGroupFromCSV(File file) throws GroupOverflowException {
        Group group = new Group(file.getName());
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        while (scanner.hasNext()) {
            Student testStudent = new Student();
            testStudent = testStudent.fromCSVString(scanner.next());

            group.addStudent(testStudent);
        }
        return group;
    }

    public static File findFileByGroupName(String groupName, File workFolder) throws FileNotFoundException {
        File[] files = workFolder.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().startsWith(groupName + ".")) {
                return files[i];
            }
        }
        throw new FileNotFoundException();
    }
}




















