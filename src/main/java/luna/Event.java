package luna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents an event task with a specific start and end date.
 */
public class Event extends Task {

    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructs a new Event task with the given description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param startDate The start date of the event task in the format "yyyy-MM-dd".
     * @param endDate The end date of the event task in the format "yyyy-MM-dd".
     * @throws DateTimeParseException If the date format is invalid.
     */
    public Event(String description, String startDate, String endDate) throws DateTimeParseException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.startDate = LocalDate.parse(startDate, formatter);
        this.endDate = LocalDate.parse(endDate, formatter);
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + " to: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
