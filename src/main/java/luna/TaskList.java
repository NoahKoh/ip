package luna;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    protected ArrayList<Task> taskData;

    public TaskList() {
        this.taskData = new ArrayList<>();
    }

    // For loading tasks
    public TaskList(ArrayList<Task> taskData) {
        this.taskData = taskData;
    }

    // Use for Saving
    public ArrayList<Task> getTaskList() {
        return this.taskData;
    }

    public void listTask() {
        for (int i = 0; i < this.taskData.size(); i++) {
            System.out.println((i + 1) + "." + this.taskData.get(i).toString());
        }
    }

    public void markDone(int index) {
        Task task = this.taskData.get(index);
        task.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void markUndone(int index) {
        Task task = this.taskData.get(index);
        task.markUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public void deleteTask(int index) {
        Task task = this.taskData.get(index);
        this.taskData.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + this.taskData.size() + " tasks in the list.");
    }

    public void addTask(Task task) {
        this.taskData.add(task);
        task.printAddTaskMessage();
    }

    public void findTask(String description) {
        for (int i = 0; i < this.taskData.size(); i++) {
            Task task = this.taskData.get(i);
            if (task.description.contains(description)) {
                System.out.println((i + 1) + ". " + task.toString());
            }
        }
    }

}
