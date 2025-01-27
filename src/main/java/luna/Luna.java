package luna;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * This is the main class for the Luna chatbot application.
 * This class handles the initialization and execution of the chatbot.
 */
public class Luna {

    private String filePath = "./data/luna.txt";
    private Parser.Command command;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for Luna, initializes the necessary components.
     */
    public Luna() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            System.err.println("Error, can't load");
            taskList = new TaskList();
        }
        //loadTasks();
    }

    /**
     * Starts the Luna chatbot and begins processing user input.
     */
    public void run() {
        ui.greet(this.getClass().getSimpleName());
        processInput();
    }

    /**
     * Processes user input and executes the corresponding commands.
     * Continuously reads input until the "BYE" command is received.
     */
    public void processInput() {
        while (true) {
        //while (sc.hasNextLine()) {
            try {
                String input = ui.getInput();
                String[] inputParts = input.split(" ", 2);
                try {
                    command = parser.parseCommand(input);
                } catch (IllegalArgumentException e) {
                    throw new LunaException("Invalid Command");
                }
                if (command == Parser.Command.BYE) {
                    ui.exit();
                    break;
                } else if (command == Parser.Command.LIST) {
                    ui.printMessage("Here are the tasks in your list:");
                    taskList.listTask();
                } else if (command == Parser.Command.MARK) {
                    if (inputParts.length < 2) {
                        throw new LunaException("The luna.Task number to mark cannot be empty.");
                    }
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    taskList.markDone(index);
                } else if (command == Parser.Command.UNMARK) {
                    if (inputParts.length < 2) {
                        throw new LunaException("The luna.Task number to unmark cannot be empty.");
                    }
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    taskList.markUndone(index);
                } else if (command == Parser.Command.DELETE) {
                    if (inputParts.length < 2) {
                        throw new LunaException("The luna.Task number to delete cannot be empty.");
                    }
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    taskList.deleteTask(index);
                } else { // Action can be any of the 3 types of luna.Task
                    if (command == Parser.Command.TODO) {
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new LunaException("The description of a todo cannot be empty.");
                        }
                        String description = inputParts[1];
                        Todo task = new Todo(description);
                        taskList.addTask(task);
                    } else if (command == Parser.Command.DEADLINE) {
                        if (inputParts.length < 2) {
                            throw new LunaException("The description of a deadline cannot be empty.");
                        }
                        String[] remainingInput = inputParts[1].split("/by", 2);
                        if (remainingInput.length < 2) {
                            throw new LunaException("luna.Deadline must include a '/by'");
                        }
                        String description = remainingInput[0].trim();
                        String by = remainingInput[1].trim();
                        try {
                            Deadline task = new Deadline(description, by);
                            taskList.addTask(task);
                        } catch (DateTimeParseException e) {
                            System.err.println("Invalid date format. Use yyyy-MM-dd.");
                        }
                    } else if (command == Parser.Command.EVENT) {
                        if (inputParts.length < 2) {
                            throw new LunaException("The description of an event cannot be empty.");
                        }
                        String[] remainingInput1 = inputParts[1].split("/from", 2);
                        if (remainingInput1.length < 2) {
                            throw new LunaException("luna.Event must include a '/from'");
                        }
                        String description = remainingInput1[0].trim();
                        String[] remainingInput2 = remainingInput1[1].split("/to", 2);
                        if (remainingInput2.length < 2) {
                            throw new LunaException("luna.Event must include a '/to'");
                        }
                        String from = remainingInput2[0].trim();
                        String to = remainingInput2[1].trim();
                        try {
                            Event task = new Event(description, from, to);
                            taskList.addTask(task);
                        } catch (DateTimeParseException e) {
                            System.err.println("Invalid date format. Use yyyy-MM-dd.");
                        }
                    } else {
                        throw new LunaException("Invalid command");
                    }
                    ui.printMessage("Now you have " + taskList.getTaskList().size() + " tasks in the list.");
                }
            } catch (LunaException e) {
                System.err.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Need a valid luna.Task number");
            } catch (IndexOutOfBoundsException e) {
                System.err.println("luna.Task number not in list");
            }

            storage.save(taskList.getTaskList());
        }
    }


    /**
     * The main method to start the Luna chatbot application.
     *
     * @param args Command-line arguments.
     */
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
        chatBot.run();
    }
}
