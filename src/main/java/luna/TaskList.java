package luna;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a list of tasks and provides methods to manipulate the tasks.
 */
public class TaskList implements Serializable {
    protected ArrayList<Task> taskData;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskData = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param taskData The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> taskData) {
        this.taskData = taskData;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskData;
    }

    /**
     * Prints the list of tasks.
     */
    public void listTask() {
        for (int i = 0; i < this.taskData.size(); i++) {
            System.out.println((i + 1) + "." + this.taskData.get(i).toString());
        }
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     */
    public void markDone(int index) {
        Task task = this.taskData.get(index);
        task.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to mark as not done.
     */
    public void markUndone(int index) {
        Task task = this.taskData.get(index);
        task.markUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        Task task = this.taskData.get(index);
        this.taskData.remove(index);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + this.taskData.size() + " tasks in the list.");
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to add.
     */
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
