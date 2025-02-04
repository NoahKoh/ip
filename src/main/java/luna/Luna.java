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
    }

    /**
     * Processes user input and executes the corresponding commands.
     * Returns the response as a string.
     *
     * @param input The user input to be processed.
     * @return The response from Luna.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            String[] inputParts = input.split(" ", 2);
            try {
                command = parser.parseCommand(input);
            } catch (IllegalArgumentException e) {
                return "Invalid Command";
                //throw new LunaException("Invalid Command");
            }
            if (command == Parser.Command.BYE) {
                response = ui.exit();
                System.exit(0);
            } else if (command == Parser.Command.LIST) {
                String text = "Here are the tasks in your list:\n";
                text += taskList.listTask();
                response = text;
            } else if (command == Parser.Command.MARK) {
                if (inputParts.length < 2) {
                    return "The Task number to mark cannot be empty.";
                    //throw new LunaException("The Task number to mark cannot be empty.");
                }
                int index = Integer.parseInt(inputParts[1]) - 1;
                response = taskList.markDone(index);
            } else if (command == Parser.Command.UNMARK) {
                if (inputParts.length < 2) {
                    return "The Task number to unmark cannot be empty.";
                    //throw new LunaException("The Task number to unmark cannot be empty.");
                }
                int index = Integer.parseInt(inputParts[1]) - 1;
                response = taskList.markUndone(index);
            } else if (command == Parser.Command.DELETE) {
                if (inputParts.length < 2) {
                    return "Task number to delete cannot be empty.";
                    //throw new LunaException("Task number to delete cannot be empty.");
                }
                int index = Integer.parseInt(inputParts[1]) - 1;
                response = taskList.deleteTask(index);
            } else if (command == Parser.Command.FIND) {
                if (inputParts.length < 2) {
                    return "The description to find cannot be empty.";
                    //throw new LunaException("The description to find cannot be empty.");
                }
                String text = "Here are the matching tasks in your list:";
                String description = inputParts[1];
                text += taskList.findTask(description);
                response = text;
            } else { // Action can be any of the 3 types of Task
                if (command == Parser.Command.TODO) {
                    if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                        return "The description of a todo cannot be empty.";
                        //throw new LunaException("The description of a todo cannot be empty.");
                    }
                    String description = inputParts[1];
                    Todo task = new Todo(description);
                    response = taskList.addTask(task);
                } else if (command == Parser.Command.DEADLINE) {
                    if (inputParts.length < 2) {
                        return "The description of a deadline cannot be empty.";
                        //throw new LunaException("The description of a deadline cannot be empty.");
                    }
                    String[] remainingInput = inputParts[1].split("/by", 2);
                    if (remainingInput.length < 2) {
                        return "Deadline must include a '/by'";
                        //throw new LunaException("Deadline must include a '/by'");
                    }
                    String description = remainingInput[0].trim();
                    String by = remainingInput[1].trim();
                    try {
                        Deadline task = new Deadline(description, by);
                        response = taskList.addTask(task);
                    } catch (DateTimeParseException e) {
                        return "Invalid date format. Use yyyy-MM-dd.";
                        //System.err.println("Invalid date format. Use yyyy-MM-dd.");
                    }
                } else if (command == Parser.Command.EVENT) {
                    if (inputParts.length < 2) {
                        return "The description of an event cannot be empty.";
                        //throw new LunaException("The description of an event cannot be empty.");
                    }
                    String[] remainingInput1 = inputParts[1].split("/from", 2);
                    if (remainingInput1.length < 2) {
                        return "Event must include a '/from'";
                        //throw new LunaException("Event must include a '/from'");
                    }
                    String description = remainingInput1[0].trim();
                    String[] remainingInput2 = remainingInput1[1].split("/to", 2);
                    if (remainingInput2.length < 2) {
                        return "Event must include a '/to'";
                        //throw new LunaException("Event must include a '/to'");
                    }
                    String from = remainingInput2[0].trim();
                    String to = remainingInput2[1].trim();
                    try {
                        Event task = new Event(description, from, to);
                        response = taskList.addTask(task);
                    } catch (DateTimeParseException e) {
                        return "Invalid date format. Use yyyy-MM-dd.";
                        //System.err.println("Invalid date format. Use yyyy-MM-dd.");
                    }
                } else {
                    return "Invalid command";
                    //throw new LunaException("Invalid command");
                }
                response += "\n" + "Now you have " + taskList.getTaskList().size() + " tasks in the list.";
            }
        } catch (NumberFormatException e) {
            return "Need a valid Task number";
            //System.err.println("Need a valid Task number");
        } catch (IndexOutOfBoundsException e) {
            return "Task number not in list";
            //System.err.println("Task number not in list");
        }
        storage.save(taskList.getTaskList());
        return response;
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
        //chatBot.run();
    }
}
