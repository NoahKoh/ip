package luna;

import java.time.format.DateTimeParseException;

/**
 * Handles commands for the Luna chatbot.
 */
public class CommandHandler {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a CommandHandler with the given UI, task list, and storage.
     *
     * @param ui The user interface to interact with.
     * @param taskList The list of tasks to manage.
     * @param storage The storage to save tasks to.
     */
    public CommandHandler(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Handles the bye command.
     *
     * @return The exit message.
     */
    public String handleByeCommand() {
        String response = ui.exit();
        System.exit(0);
        return response;
    }

    /**
     * Handles the list command.
     *
     * @return A list of tasks.
     */
    public String handleListCommand() {
        String text = "Here are the tasks in your list:\n";
        text += taskList.listTask();
        return text;
    }

    /**
     * Handles the mark command.
     *
     * @param inputParts The command parts.
     * @return The result of marking the task.
     */
    public String handleMarkCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            return "The Task number to mark cannot be empty.";
        }
        int index = Integer.parseInt(inputParts[1]) - 1;
        return taskList.markDone(index);
    }

    /**
     * Handles the unmark command.
     *
     * @param inputParts The command parts.
     * @return The result of unmarking the task.
     */
    public String handleUnmarkCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            return "The Task number to unmark cannot be empty.";
        }
        int index = Integer.parseInt(inputParts[1]) - 1;
        return taskList.markUndone(index);
    }

    /**
     * Handles the delete command.
     *
     * @param inputParts The command parts.
     * @return The result of deleting the task.
     */
    public String handleDeleteCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            return "Task number to delete cannot be empty.";
        }
        int index = Integer.parseInt(inputParts[1]) - 1;
        return taskList.deleteTask(index);
    }

    /**
     * Handles the find command.
     *
     * @param inputParts The command parts.
     * @return The tasks matching the description.
     */
    public String handleFindCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            return "The description to find cannot be empty.";
        }
        String text = "Here are the matching tasks in your list:";
        String description = inputParts[1];
        text += taskList.findTask(description);
        return text;
    }

    /**
     * Handles the todo command.
     *
     * @param inputParts The command parts.
     * @return The result of adding the todo task.
     */
    public String handleTodoCommand(String[] inputParts) {
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            return "The description of a todo cannot be empty.";
        }
        String description = inputParts[1];
        Todo task = new Todo(description);
        return taskList.addTask(task);
    }

    /**
     * Handles the deadline command.
     *
     * @param inputParts The command parts.
     * @return The result of adding the deadline task.
     */
    public String handleDeadlineCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            return "The description of a deadline cannot be empty.";
        }
        String[] remainingInput = inputParts[1].split("/by", 2);
        if (remainingInput.length < 2) {
            return "Deadline must include a '/by'";
        }
        String description = remainingInput[0].trim();
        String dueDate = remainingInput[1].trim();
        try {
            Deadline task = new Deadline(description, dueDate);
            String response = taskList.addTask(task);
            response += "\n" + "Now you have " + taskList.getTaskList().size() + " tasks in the list.";
            return response;
        } catch (DateTimeParseException e) {
            return "Invalid date format. Use yyyy-MM-dd.";
        }
    }

    /**
     * Handles the event command.
     *
     * @param inputParts The command parts.
     * @return The result of adding the event task.
     */
    public String handleEventCommand(String[] inputParts) {
        if (inputParts.length < 2) {
            return "The description of an event cannot be empty.";
        }
        String[] remainingInput1 = inputParts[1].split("/from", 2);
        if (remainingInput1.length < 2) {
            return "Event must include a '/from'";
        }
        String description = remainingInput1[0].trim();
        String[] remainingInput2 = remainingInput1[1].split("/to", 2);
        if (remainingInput2.length < 2) {
            return "Event must include a '/to'";
        }
        String startDate = remainingInput2[0].trim();
        String endDate = remainingInput2[1].trim();
        try {
            Event task = new Event(description, startDate, endDate);
            String response = taskList.addTask(task);
            response += "\n" + "Now you have " + taskList.getTaskList().size() + " tasks in the list.";
            return response;
        } catch (DateTimeParseException e) {
            return "Invalid date format. Use yyyy-MM-dd.";
        }
    }

    public String handleHelpCommand() {
        StringBuilder text = new StringBuilder("Available commands:\n");
        text.append("• bye - Exit the chatbot\n");
        text.append("• list - List all tasks\n");
        text.append("• mark <number> - Mark a task as completed\n");
        text.append("• unmark <number> - Unmark a task as completed\n");
        text.append("• delete <number> - Delete a task\n");
        text.append("• find <description> - Find tasks by description\n");
        text.append("• todo <description> - Add a todo task\n");
        text.append("• deadline <description> /by <date> - Add a deadline task\n");
        text.append("• event <description> /from <start_date> /to <end_date> - Add an event task\n");
        text.append("• help - Display the list of available commands\n");
        return text.toString();
    }
}