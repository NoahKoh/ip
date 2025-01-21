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

    /*
    public void echo() {
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                exit();
                break;
            } else {
                System.out.println(command); // Just print the command itself
            }
        }
    }
    */

    public void processInput() {
        while (true) {
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
            } else { // Action can only be about adding input to the list
                taskData.add(new Task(input));
                System.out.println("added: " + input);
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
