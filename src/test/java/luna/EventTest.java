package luna;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.format.DateTimeParseException;

/**
 * This class is to test the functionality of the Event class.
 */
public class EventTest {

    /**
     * Tests the creation of an Event task.
     */
    @Test
    public void testEventCreation() {
        Event event = new Event("Birthday", "2025-12-30", "2025-12-31");
        assertEquals("[E][ ] Birthday (from: Dec 30 2025 to: Dec 31 2025)", event.toString());
    }

    /**
     * Tests marking an Event task as done.
     */
    @Test
    public void testEventMarkDone() {
        Event event = new Event("Birthday", "2025-12-30", "2025-12-31");
        event.markDone();
        assertEquals("X", event.getStatusIcon());
    }

    /**
     * Tests marking an Event task as not done.
     */
    @Test
    public void testEventMarkUndone() {
        Event event = new Event("Birthday", "2025-12-30", "2025-12-31");
        event.markDone();
        event.markUndone();
        assertEquals(" ", event.getStatusIcon());
    }

    /**
     * Tests the creation of an Event task with invalid dates.
     */
    @Test
    public void testEventInvalidDate() {
        assertThrows(DateTimeParseException.class, () -> new Event("Birthday", "ABC", "DEF"));
    }
}
