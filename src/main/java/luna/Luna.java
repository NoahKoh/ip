package luna;

import java.io.IOException;

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
    private CommandHandler commandHandler;

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
        commandHandler = new CommandHandler(ui, taskList, storage);
        assert filePath != null;
        assert ui != null;
        assert parser != null;
        assert storage != null;
        assert taskList != null;
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
            }
            switch (command) {
            case BYE:
                response = commandHandler.handleByeCommand();
                break;
            case LIST:
                response = commandHandler.handleListCommand();
                break;
            case MARK:
                response = commandHandler.handleMarkCommand(inputParts);
                break;
            case UNMARK:
                response = commandHandler.handleUnmarkCommand(inputParts);
                break;
            case DELETE:
                response = commandHandler.handleDeleteCommand(inputParts);
                break;
            case FIND:
                response = commandHandler.handleFindCommand(inputParts);
                break;
            case TODO:
                response = commandHandler.handleTodoCommand(inputParts);
                break;
            case DEADLINE:
                response = commandHandler.handleDeadlineCommand(inputParts);
                break;
            case EVENT:
                response = commandHandler.handleEventCommand(inputParts);
                break;
            default:
                return "Invalid Command";
            }
        } catch (NumberFormatException e) {
            return "Need a valid Task number";
        } catch (IndexOutOfBoundsException e) {
            return "Task number not in list";
        }
        storage.save(taskList.getTaskList());
        assert response != null;
        return response;
    }

    /**
     * The main method to start the Luna chatbot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Luna chatBot = new Luna();
    }
}
