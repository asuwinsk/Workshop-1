package pl.coderslaab;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        String[][] tasks = loadTasks();
        while (true) {
            showOptions();
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            if (option.equals ("add")) {
                tasks = addingTasks(tasks);
            } else if (option.equals ("remove")) {
                System.out.println("Please provide index of task to remove: ");
                int index = scanner.nextInt();
                tasks = removeTasks(tasks, index);
            } else if (option.equals ("list")) {
                int i = 0;
                for (String[] task : tasks) {
                    System.out.println(i + ". " + Arrays.toString(task));
                    i++;
                }
            } else if (option.equals ("exit")) {
                System.out.println("Exiting...");
                try {
                    saveTasks (tasks);
                } catch (IOException e) {
                    System.out.println("Error saving tasks: " + e.getMessage());
                }
                break;
            }
        }

    }

    public static String[][] addingTasks(String[][] tasks) {
        System.out.println ("Please add task description: ");
        Scanner scanner = new Scanner(System.in);
        String taskDescription = scanner.nextLine();
        System.out.println ("Please add task due date (yyyy-mm-dd): ");
        String taskDueDate = scanner.nextLine();
        System.out.println ("Is your task important? (true/false)?");
        String taskImportant = scanner.nextLine();
        String[] task = {taskDescription, taskDueDate, taskImportant};

        tasks = Arrays.copyOf (tasks, tasks.length + 1);
        tasks[tasks.length - 1] = task;

        return tasks;
    }

    public static String[][] removeTasks(String[][] tasks, int index) {
        if (index < 0 || index >= tasks.length) {
            System.out.println("Invalid index. No task removed.");
            return tasks;
        }
        String[][] removingTask = ArrayUtils.remove(tasks, index);
        return removingTask;
    }

    public static void showOptions() {
        System.out.println(ConsoleColors.BLUE + "Please select an option: " + ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

    public static String[][] loadTasks() {
        File file = new File("tasks.csv");
        String[][] tasks = {};

        try {
            Scanner scan = new Scanner (file);
            while (scan.hasNextLine ()) {
                String line = scan.nextLine ();
                String[] items = line.split (",");
                tasks = Arrays.copyOf (tasks, tasks.length + 1);
                tasks[tasks.length - 1] = items;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku.");
        }
        return tasks;

    }

    public static void saveTasks(String[][] tasks) throws IOException {
        FileWriter writer = new FileWriter ("tasks.csv", false);
        for (String[] task : tasks) {
            String line = String.join(",", task);
            writer.write(line + "\n");
        }
        writer.close();
    }


}
