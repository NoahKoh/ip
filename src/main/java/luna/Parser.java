package luna;

public class Parser {

    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    public Command parseCommand(String input) throws IllegalArgumentException {
        String[] inputParts = input.split(" ", 2);
        return Command.valueOf(inputParts[0].toUpperCase());

    }

}
