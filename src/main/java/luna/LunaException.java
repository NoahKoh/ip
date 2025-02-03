package luna;

/**
 * This class represents an exception specific to the Luna chatbot application.
 */
public class LunaException extends Exception {

    /**
     * Constructs a new LunaException with the given detail message.
     *
     * @param message The detail message.
     */
    public LunaException(String message) {
        super(message);
    }
}
