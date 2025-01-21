import java.util.ArrayList;
import java.util.Scanner;

public class Luna {

    Scanner sc = new Scanner(System.in);
    ArrayList<Task> taskData = new ArrayList<>();

    public void greet() {
        System.out.println("Hello! I'm " + this.getClass().getSimpleName() + "\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void processInput() {
        //while (true) {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] inputParts = input.split(" ", 2);
            String command = inputParts[0];
            if (command.equals("bye")) {
                exit();
                break;
            } else if (command.equals("list")){
                System.out.println("Here are the tasks in your list:");
                listTask();
            } else if (command.equals("mark")) {
                int index = Integer.parseInt(inputParts[1]) - 1;
                taskData.get(index).markDone();
            } else if (command.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(inputParts[1]) - 1;
                taskData.get(index).markUndone();
            } else { // Action can be any of the 3 types of Task
                if (command.equals("todo")) {
                    String description = inputParts[1];
                    Todo task = new Todo(description);
                    taskData.add(task);
                    task.printAddTaskMessage();
                } else if (command.equals("deadline")) {
                    String[] remainingInput = inputParts[1].split("/by", 2);
                    String description = remainingInput[0].trim();
                    String by = remainingInput[1].trim();
                    Deadline task = new Deadline(description, by);
                    taskData.add(task);
                    task.printAddTaskMessage();
                } else if (command.equals("event")) {
                    String[] remainingInput1 = inputParts[1].split("/from", 2);
                    String description = remainingInput1[0].trim();
                    String[] remainingInput2 = remainingInput1[1].split("/to", 2);
                    String from = remainingInput2[0].trim();
                    String to = remainingInput2[1].trim();
                    Event task = new Event(description, from, to);
                    taskData.add(task);
                    task.printAddTaskMessage();
                } else {
                    taskData.add(new Task(input));
                }
                System.out.println("Now you have " + taskData.size() + " tasks in the list.");
            }
        }
    }
    
    public void listTask() {
        for (int i = 0; i < taskData.size(); i++) {
            System.out.println((i + 1) + "." + taskData.get(i).toString());
        }
    }

    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        Luna chatBot = new Luna();
        chatBot.greet();
        chatBot.processInput();
    }
}
