package luna;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This class handles the storage of tasks to and from a file.
 * It provides methods to save and load tasks to and from the file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage instance with the specified file path.
     *
     * @param filePath The file path where tasks will be saved and loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the file.
     *
     * @param taskList The list of tasks to be saved.
     */
    public void save(ArrayList<Task> taskList) {
        File file = new File(filePath);
        File parentDirectory = file.getParentFile();

        // If ./data/ directory doesn't exist
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        // If luna.txt doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating file");
            }
        }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(taskList);
        } catch (IOException e) {
            System.err.println("Error saving file");
        }
    }

    /**
     * Loads the list of tasks from the file.
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while loading the file.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList;
        File file = new File(filePath);

        if (!file.exists()) {
            taskList = new ArrayList<>(); // No file to load, start fresh
        } else {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                // Suppressing unchecked cast warning because taskList is always ArrayList<Task> even when saving
                taskList = (ArrayList<Task>) inputStream.readObject();
            } catch (IOException e) {
                System.err.println("File corrupted");
                taskList = new ArrayList<>(); // Can't read data, start fresh
            } catch (ClassNotFoundException e) {
                System.err.println("Class not found");
                taskList = new ArrayList<>(); // Can't read data, start fresh
            }
        }
        return taskList;
    }
}
