package luna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents an event task with a specific start and end date.
 */
public class Event extends Task{

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs a new Event task with the given description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from The start date of the event task in the format "yyyy-MM-dd".
     * @param to The end date of the event task in the format "yyyy-MM-dd".
     * @throws DateTimeParseException If the date format is invalid.
     */
    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.from = LocalDate.parse(from, formatter);
        this.to = LocalDate.parse(to, formatter);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
