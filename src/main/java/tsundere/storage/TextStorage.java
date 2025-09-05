package tsundere.storage;

import static tsundere.storage.ValidateTask.validateTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tsundere.TsundereException;
import tsundere.task.Task;
import tsundere.task.TaskList;

/**
 * Handles the loading and storing of tasks.
 * Checks storage paths for existing storages. If it does not exist, attempts to create one.
 * Uses the alternate storage path if default fails to initialize.
 */
public class TextStorage {
    private static final String DEFAULT_STORAGE_PATH = "./src/main/java/tsundere/storage/tsundereStorage.txt";
    private static final String ALT_STORAGE_PATH = "./tsundereStorage.txt";

    private final String storage;

    private TextStorage(String storage) {
        this.storage = storage;
    }

    /**
     * Factory method to try and create a new storage file
     *
     * @return new TextStorage or IO Exception if none of the default storage paths work
     */
    public static TextStorage of() throws IOException {
        try {
            File file = new File(DEFAULT_STORAGE_PATH);
            if (!file.exists() && !file.createNewFile()) {
                throw new IOException();
            }
            return new TextStorage(DEFAULT_STORAGE_PATH);
        } catch (IOException e) {
            File file = new File(ALT_STORAGE_PATH);
            if (!file.exists() && !file.createNewFile()) {
                throw new IOException();
            }
            return new TextStorage(ALT_STORAGE_PATH);
        }
    }

    /**
     * Saves the task list to storage.
     * @param tasks Tasks to be stored.
     */
    public void storeAll(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(storage, false);
        StringBuilder sb = new StringBuilder();

        for (Task task : tasks) {
            sb.append(task.toStorageString());
            sb.append('\n');
        }
        fw.write(sb.toString());
        fw.close();
    }

    /**
     * Fetches all the tasks from storage. Runs on initialization of application.
     *
     * @return new {@link TaskList}
     */
    public TaskList retrieveAll() throws TsundereException, IOException {
        File file = new File(storage);

        Scanner sc = new Scanner(file);
        TaskList tasks = new TaskList();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            Task task = validateTask(line);

            tasks.add(task);
        }
        sc.close();
        return tasks;
    }

    /**
     * Deletes all data stored in the specified storage. Used for testing.
     */
    public void clear() {
        try {
            FileWriter fw = new FileWriter(storage);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
