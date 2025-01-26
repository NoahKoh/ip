import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Luna {

    protected Scanner sc = new Scanner(System.in);
    protected ArrayList<Task> taskData = new ArrayList<>();

    protected enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    protected Command command;

    protected String filePath = "./data/luna.txt";

    public Luna() {
        loadTasks();
    }

    public void greet() {
        System.out.println("Hello! I'm " + this.getClass().getSimpleName() + "\nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void processInput() {
        //while (true) {
        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                String[] inputParts = input.split(" ", 2);
                try {
                    command = Command.valueOf(inputParts[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new LunaException("Invalid Command");
                }
                if (command == Command.BYE) {
                    exit();
                    break;
                } else if (command == Command.LIST) {
                    System.out.println("Here are the tasks in your list:");
                    listTask();
                } else if (command == Command.MARK) {
                    if (inputParts.length < 2) {
                        throw new LunaException("The Task number to mark cannot be empty.");
                    }
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    taskData.get(index).markDone();
                } else if (command == Command.UNMARK) {
                    if (inputParts.length < 2) {
                        throw new LunaException("The Task number to unmark cannot be empty.");
                    }
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    taskData.get(index).markUndone();
                } else if (command == Command.DELETE) {
                    if (inputParts.length < 2) {
                        throw new LunaException("The Task number to delete cannot be empty.");
                    }
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    Task task = taskData.get(index);
                    taskData.remove(index);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + task.toString());
                    System.out.println("Now you have " + taskData.size() + " tasks in the list.");
                } else { // Action can be any of the 3 types of Task
                    if (command == Command.TODO) {
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new LunaException("The description of a todo cannot be empty.");
                        }
                        String description = inputParts[1];
                        Todo task = new Todo(description);
                        taskData.add(task);
                        task.printAddTaskMessage();
                    } else if (command == Command.DEADLINE) {
                        if (inputParts.length < 2) {
                            throw new LunaException("The description of a deadline cannot be empty.");
                        }
                        String[] remainingInput = inputParts[1].split("/by", 2);
                        if (remainingInput.length < 2) {
                            throw new LunaException("Deadline must include a '/by'");
                        }
                        String description = remainingInput[0].trim();
                        String by = remainingInput[1].trim();
                        Deadline task = new Deadline(description, by);
                        taskData.add(task);
                        task.printAddTaskMessage();
                    } else if (command == Command.EVENT) {
                        if (inputParts.length < 2) {
                            throw new LunaException("The description of an event cannot be empty.");
                        }
                        String[] remainingInput1 = inputParts[1].split("/from", 2);
                        if (remainingInput1.length < 2) {
                            throw new LunaException("Event must include a '/from'");
                        }
                        String description = remainingInput1[0].trim();
                        String[] remainingInput2 = remainingInput1[1].split("/to", 2);
                        if (remainingInput2.length < 2) {
                            throw new LunaException("Event must include a '/to'");
                        }
                        String from = remainingInput2[0].trim();
                        String to = remainingInput2[1].trim();
                        Event task = new Event(description, from, to);
                        taskData.add(task);
                        task.printAddTaskMessage();
                    } else {
                        throw new LunaException("Invalid command");
                    }
                    System.out.println("Now you have " + taskData.size() + " tasks in the list.");
                }
            } catch (LunaException e) {
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Need a valid Task number");
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Task number not in list");
            }

            saveTasks();
        }
    }
    
    public void listTask() {
        for (int i = 0; i < taskData.size(); i++) {
            System.out.println((i + 1) + "." + taskData.get(i).toString());
        }
    }

    public void saveTasks() {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();

        // If ./data/ directory doesn't exist
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        // If luna.txt doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file");
            }
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            outputStream.writeObject(taskData);
        } catch (IOException e) {
            System.err.println("Error saving file");
        }
    }

    @SuppressWarnings("unchecked")
    public void loadTasks() {
        File file = new File(filePath);

        if (!file.exists()) {
            taskData = new ArrayList<>(); // No file to load, start fresh
        } else {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                // Suppressing unchecked cast warning because taskData is always ArrayList<Task> even when saving
                taskData = (ArrayList<Task>) inputStream.readObject();
            } catch (IOException e) {
                System.err.println("File corrupted");
                taskData = new ArrayList<>(); // Can't read data, start fresh
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found");
                taskData = new ArrayList<>(); // Can't read data, start fresh
            }
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
