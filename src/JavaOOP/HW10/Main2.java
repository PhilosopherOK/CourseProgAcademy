package JavaOOP.HW10;

/*
2. Список завдань. Створіть програму для створення списку завдань, які
користувач може додавати, видаляти та відзначати як виконані. Використайте
стандартні колекції.
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Task task1 = taskList.createTask();
        Task task2 = taskList.createTask();
        taskList.findByNameOfTask("sport");
        taskList.findByNameOfTask("sport1");
        taskList.markAsDone(task1);
        taskList.showReadyTasks();
        taskList.showUnfinishedTasks();
        taskList.updateTask(task1);
        taskList.deleteTask(task2);
        taskList.showAllTasks();
    }
}

class TaskList {
    private List<Task> listOfTasks = new LinkedList<>();
    private Scanner scanner = new Scanner(System.in);

    public Task createTask() {
        System.out.println("Please enter name of task");
        String name = scanner.nextLine();
        System.out.println("Please enter sense of the task");
        String senseOfTheTask = scanner.nextLine();
        Task task = new Task(name, senseOfTheTask);
        listOfTasks.add(task);
        return task;
    }

    public Task findByNameOfTask(String name) {
        Task task = null;
        for (int i = 0; i < listOfTasks.size(); i++) {
            if (listOfTasks.get(i).getNameOfTask().equals(name)) {
                task = listOfTasks.get(i);
            }
        }
        if (task != null) {
            System.out.println(task);
            return task;
        } else {
            System.out.println("Not found");
            return null;
        }
    }

    public void markAsDone(Task task) {
        task.setDone(true);
        System.out.println(task.getNameOfTask() + " is finished successful !");
    }

    public void showAllTasks() {
        listOfTasks.stream().forEach(System.out::println);
    }

    public void showReadyTasks() {
        listOfTasks.stream().filter(task -> task.isDone()).forEach(System.out::println);
    }

    public void showUnfinishedTasks() {
        listOfTasks.stream().filter(task -> !task.isDone()).forEach(System.out::println);
    }

    public void updateTask(Task task) {
        System.out.println("Please enter new name for task");
        task.setNameOfTask(scanner.nextLine());
        System.out.println("Please enter new sense of the task");
        task.setSenseOfTheTask(scanner.nextLine());
    }

    public void deleteTask(Task task) {
        listOfTasks.remove(task);
    }
}

class Task {
    private String nameOfTask;
    private String senseOfTheTask;
    private boolean done;

    public Task(String nameOfTask, String senseOfTheTask) {
        this.nameOfTask = nameOfTask;
        this.senseOfTheTask = senseOfTheTask;
        done = false;
    }

    @Override
    public String toString() {
        return "Task{" +
                "nameOfTask='" + nameOfTask + '\'' +
                ", senseOfTheTask='" + senseOfTheTask + '\'' +
                ", done=" + done +
                '}';
    }

    public String getSenseOfTheTask() {
        return senseOfTheTask;
    }

    public void setSenseOfTheTask(String senseOfTheTask) {
        this.senseOfTheTask = senseOfTheTask;
    }

    public String getNameOfTask() {
        return nameOfTask;
    }

    public void setNameOfTask(String nameOfTask) {
        this.nameOfTask = nameOfTask;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}