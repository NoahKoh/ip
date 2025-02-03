package luna;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

/**
 * This class is to test the functionality of the Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests the creation of a Deadline task.
     */
    @Test
    public void testDeadlineCreation() {
        Deadline deadline = new Deadline("Submit Report", "2025-12-31");
        assertEquals("[D][ ] Submit Report (by: Dec 31 2025)", deadline.toString());
    }

    /**
     * Tests marking a Deadline task as done.
     */
    @Test
    public void testDeadlineMarkDone() {
        Deadline deadline = new Deadline("Submit Report", "2025-12-31");
        deadline.markDone();
        assertEquals("X", deadline.getStatusIcon());
    }

    /**
     * Tests marking a Deadline task as not done.
     */
    @Test
    public void testDeadlineMarkUndone() {
        Deadline deadline = new Deadline("Submit Report", "2025-12-31");
        deadline.markDone();
        deadline.markUndone();
        assertEquals(" ", deadline.getStatusIcon());
    }

    /**
     * Tests the creation of a Deadline task with an invalid date.
     */
    @Test
    public void testDeadlineInvalidDate() {
        assertThrows(DateTimeParseException.class, () -> new Deadline("Submit Report", "ABC"));
    }
}
