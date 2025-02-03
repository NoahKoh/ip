package luna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents a deadline task with a specific due date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a new Deadline task with the given description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task in the format "yyyy-MM-dd".
     * @throws DateTimeParseException If the date format is invalid.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, formatter);
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
