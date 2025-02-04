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
     * Returns the list of tasks as a string.
     *
     * @return A string representation of the list of tasks.
     */
    public String listTask() {
        String taskList = "";
        for (int i = 0; i < this.taskData.size(); i++) {
            taskList += (i + 1) + "." + this.taskData.get(i).toString() + "\n";
        }
        return taskList;
    }

    /**
     * Marks the task at the specified index as done and returns a message indicating the task has been marked as done.
     *
     * @param index The index of the task to mark as done.
     * @return A string message indicating the task has been marked as done.
     */
    public String markDone(int index) {
        Task task = this.taskData.get(index);
        task.markDone();
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Marks the task at the specified index as not done and returns a message indicating the task has been marked as not done.
     *
     * @param index The index of the task to mark as not done.
     * @return A string message indicating the task has been marked as not done.
     */
    public String markUndone(int index) {
        Task task = this.taskData.get(index);
        task.markUndone();
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Deletes the task at the specified index and returns a message indicating the task has been deleted.
     *
     * @param index The index of the task to delete.
     * @return A string message indicating the task has been deleted.
     */
    public String deleteTask(int index) {
        Task task = this.taskData.get(index);
        this.taskData.remove(index);
        return "Noted. I've removed this task:\n  " + task.toString() + "\n" + "Now you have "
                + this.taskData.size() + " tasks in the list.";
    }

    /**
     * Adds a new task to the list and returns a message indicating the task has been added.
     *
     * @param task The task to add.
     * @return A string message indicating the task has been added.
     */
    public String addTask(Task task) {
        this.taskData.add(task);
        return task.printAddTaskMessage();
    }

    /**
     * Finds and returns tasks that contain the given description.
     *
     * @param description The keyword to search for the task.
     * @return A string representation of the tasks that match the description.
     */
    public String findTask(String description) {
        String taskList = "";
        for (int i = 0; i < this.taskData.size(); i++) {
            Task task = this.taskData.get(i);
            if (task.description.contains(description)) {
                taskList += (i + 1) + ". " + task.toString() + "\n";
            }
        }
        return taskList;
    }

}
