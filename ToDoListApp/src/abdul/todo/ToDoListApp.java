package abdul.todo;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class ToDoListApp {

    private static final Map<Integer, Runnable> commands = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        // Initialize commands map
        commands.put(1, () -> addTask(scanner, toDoList));
        commands.put(2, () -> removeTask(scanner, toDoList));
        commands.put(3, () -> markTaskAsCompleted(scanner, toDoList));
        commands.put(4, toDoList::displayTasks);  // This now references the correct method
        commands.put(5, () -> exitApp(scanner));

        while (true) {
            System.out.println("\n--- To-Do List Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. View Tasks");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int option = getValidOption(scanner);
            Runnable command = commands.get(option);

            if (command != null) {
                command.run();
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addTask(Scanner scanner, ToDoList toDoList) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();
        if (!title.isEmpty()) {
            toDoList.addTask(title);
            System.out.println("Task added.");
        } else {
            System.out.println("Task title cannot be empty.");
        }
    }

    private static void removeTask(Scanner scanner, ToDoList toDoList) {
        System.out.print("Enter task number to remove: ");
        int index = getValidTaskIndex(scanner, toDoList);
        if (index != -1) {
            toDoList.removeTask(index);
            System.out.println("Task removed.");
        }
    }

    private static void markTaskAsCompleted(Scanner scanner, ToDoList toDoList) {
        System.out.print("Enter task number to mark as completed: ");
        int index = getValidTaskIndex(scanner, toDoList);
        if (index != -1) {
            toDoList.markTaskAsCompleted(index);
            System.out.println("Task marked as completed.");
        }
    }

    private static int getValidTaskIndex(Scanner scanner, ToDoList toDoList) {
        int index = -1;
        if (toDoList.getTaskCount() == 0) {
            System.out.println("No tasks to select.");
            return index;
        }

        try {
            index = scanner.nextInt() - 1;
            scanner.nextLine();  // Consume newline
            if (index < 0 || index >= toDoList.getTaskCount()) {
                System.out.println("Invalid task number.");
                index = -1;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid task number.");
            scanner.nextLine();  // Clear the invalid input from the scanner buffer
        }

        return index;
    }

    private static void exitApp(Scanner scanner) {
        System.out.println("Exiting To-Do List App. Goodbye!");
        scanner.close();
        System.exit(0);  // Explicit exit to ensure the program terminates
    }

    private static int getValidOption(Scanner scanner) {
        int option = -1;
        while (option < 1 || option > 5) {
            try {
                option = scanner.nextInt();
                scanner.nextLine();  // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine();  // Clear the invalid input from the scanner buffer
            }
        }
        return option;
    }
}
