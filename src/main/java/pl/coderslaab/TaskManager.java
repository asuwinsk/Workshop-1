package pl.coderslaab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TaskManager {

    public static void main(String[] args) {
        File file = new File("tasks.csv");
        //StringBuilder reading = new StringBuilder();
        String[] strings = new String[20];
        try {
            Scanner scan = new Scanner(file);
            int i = 0;
            while (scan.hasNextLine()) {
                strings[i] = scan.nextLine();
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku.");
        }
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] == null) {
                break;
            }
            System.out.println((i + 1) + ": " + strings[i]);
        }

        System.out.println(ConsoleColors.BLUE + "Please select an option: " + ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }

}
