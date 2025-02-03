package luna;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This class is to test the functionality of the Todo class.
 */
public class TodoTest {

    /**
     * Tests the creation of a Todo task.
     */
    @Test
    public void testTodoCreation() {
        Todo todo = new Todo("borrow book");
        assertEquals("[T][ ] borrow book", todo.toString());
    }

    /**
     * Tests marking a Todo task as done.
     */
    @Test
    public void testTodoMarkDone() {
        Todo todo = new Todo("borrow book");
        todo.markDone();
        assertEquals("X", todo.getStatusIcon());
    }

    /**
     * Tests marking a Todo task as not done.
     */
    @Test
    public void testTodoMarkUndone() {
        Todo todo = new Todo("borrow book");
        todo.markDone();
        todo.markUndone();
        assertEquals(" ", todo.getStatusIcon());
    }
}
