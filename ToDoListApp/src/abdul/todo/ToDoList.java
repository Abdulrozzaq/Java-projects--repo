package abdul.todo;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    // Assume there is a list to hold tasks
    private List<String> tasks = new ArrayList<>();

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            System.out.println("To-Do List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    // Other methods like addTask, removeTask, markTaskAsCompleted
    public void addTask(String task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public void markTaskAsCompleted(int index) {
        // Assume we mark a task as completed
        tasks.set(index, tasks.get(index) + " (Completed)");
    }

    public int getTaskCount() {
        return tasks.size();
    }
}
