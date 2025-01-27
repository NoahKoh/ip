package luna;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.format.DateTimeParseException;

public class DeadlineTest {
    @Test
    public void testDeadlineCreation() {
        Deadline deadline = new Deadline("Submit Report", "2025-12-31");
        assertEquals("[D][ ] Submit Report (by: Dec 31 2025)", deadline.toString());
    }

    @Test
    public void testDeadlineMarkDone() {
        Deadline deadline = new Deadline("Submit Report", "2025-12-31");
        deadline.markDone();
        assertEquals("X", deadline.getStatusIcon());
    }

    @Test
    public void testDeadlineMarkUndone() {
        Deadline deadline = new Deadline("Submit Report", "2025-12-31");
        deadline.markDone();
        deadline.markUndone();
        assertEquals(" ", deadline.getStatusIcon());
    }

    @Test
    public void testDeadlineInvalidDate() {
        assertThrows(DateTimeParseException.class, () -> new Deadline("Submit Report", "ABC"));
    }
}
