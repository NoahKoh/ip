package luna;

import java.util.Scanner;

/**
 * This class handles the user interface for the Luna chatbot application.
 * Provides methods for interacting with the user.
 */
public class Ui {
    protected Scanner sc;

    /**
     * Constructs a new Ui instance and initializes the Scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Greets the user with a welcome message.
     *
     * @param name The name of the chatbot.
     */
    public void greet(String name) {
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
    }

    /**
     * Returns an exit message to the user.
     *
     * @return A string message indicating that the user is exiting.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads and returns the user's input.
     *
     * @return The user's input as a String.
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
