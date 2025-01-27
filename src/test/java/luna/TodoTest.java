package luna;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("borrow book");
        assertEquals("[T][ ] borrow book", todo.toString());
    }

    @Test
    public void testTodoMarkDone() {
        Todo todo = new Todo("borrow book");
        todo.markDone();
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void testTodoMarkUndone() {
        Todo todo = new Todo("borrow book");
        todo.markDone();
        todo.markUndone();
        assertEquals(" ", todo.getStatusIcon());
    }
}