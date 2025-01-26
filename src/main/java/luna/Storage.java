package luna;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            outputStream.writeObject(taskList);
        } catch (IOException e) {
            System.err.println("Error saving file");
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList;
        File file = new File(filePath);

        if (!file.exists()) {
            taskList = new ArrayList<>(); // No file to load, start fresh
        } else {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                // Suppressing unchecked cast warning because taskList is always ArrayList<luna.Task> even when saving
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
