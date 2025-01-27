package luna;

/**
 * This class parses user input and converts it into commands for the Luna chatbot.
 */
public class Parser {

    /**
     * Represents the possible commands that can be parsed.
     */
    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND
    }

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input as a String.
     * @return The parsed command.
     * @throws IllegalArgumentException If the input does not match any command in the enum.
     */
    public Command parseCommand(String input) throws IllegalArgumentException {
        String[] inputParts = input.split(" ", 2);
        return Command.valueOf(inputParts[0].toUpperCase());

    }

}
