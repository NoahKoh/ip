package luna;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.format.DateTimeParseException;

public class EventTest {
    @Test
    public void testEventCreation() {
        Event event = new Event("Birthday", "2025-12-30", "2025-12-31");
        assertEquals("[E][ ] Birthday (from: Dec 30 2025 to: Dec 31 2025)", event.toString());
    }

    @Test
    public void testEventMarkDone() {
        Event event = new Event("Birthday", "2025-12-30", "2025-12-31");
        event.markDone();
        assertEquals("X", event.getStatusIcon());
    }

    @Test
    public void testEventMarkUndone() {
        Event event = new Event("Birthday", "2025-12-30", "2025-12-31");
        event.markDone();
        event.markUndone();
        assertEquals(" ", event.getStatusIcon());
    }

    @Test
    public void testEventInvalidDate() {
        assertThrows(DateTimeParseException.class, () -> new Event("Birthday", "ABC", "DEF"));
    }
}
