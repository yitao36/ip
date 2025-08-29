package tsundere.storage;

import static tsundere.storage.ValidateTask.validateTask;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
     * Finds the task on the corresponding row, and updates F to T.
     * @param id The row from the top, starting from 0.
     * @return The modified task, or null if it does not exist.
     * @throws AlreadyMarkedException The task is already marked.
     * @throws StorageFormatException Storage text is unable to be parsed.
     * @throws IOException Storage file is unable to be opened.
     */
    public Task mark(int id) throws AlreadyMarkedException, StorageFormatException, IOException {
        Scanner sc = new Scanner(new FileReader(storage));
        StringBuilder sb = new StringBuilder();

        Task task = null;

        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (id == 0) {
                task = validateTask(line);
                if (task == null) {
                    throw new StorageFormatException();
                } else if (task.isDone()) {
                    // Do not need to change file, return early
                    sc.close();
                    throw new AlreadyMarkedException(task);
                } else {
                    task.markDone();
                }
                sb.append(task.toStorageString());
                sb.append('\n');
            } else {
                sb.append(line);
                sb.append('\n');
            }
            id--;
        }
        FileWriter fw = new FileWriter(storage);
        fw.append(sb);
        fw.close();
        return task;
    }

    /**
     * Finds the task on the corresponding row, and updates T to F.
     * @param id The row from the top, starting from 0.
     * @return The modified task, or null if it does not exist
     * @throws AlreadyMarkedException The task is already marked.
     * @throws StorageFormatException Storage text is unable to be parsed.
     * @throws IOException Storage file is unable to be opened.
     */
    public Task unmark(int id) throws AlreadyMarkedException, StorageFormatException, IOException {
        Scanner sc = new Scanner(new FileReader(storage));
        StringBuilder sb = new StringBuilder();

        Task task = null;

        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (id == 0) {
                task = validateTask(line);
                if (task == null) {
                    throw new StorageFormatException();
                } else if (!task.isDone()) {
                    // Do not need to change file, return early
                    sc.close();
                    throw new AlreadyMarkedException(task);
                } else {
                    task.markUndone();
                }
                sb.append(task.toStorageString());
                sb.append('\n');
            } else {
                sb.append(line);
                sb.append('\n');
            }
            id--;
        }
        FileWriter fw = new FileWriter(storage);
        fw.append(sb);
        fw.close();
        return task;
    }

    /**
     * Fetches all the tasks from storage. Runs on initialization of application.
     *
     * @return new {@link TaskList}
     */
    public TaskList retrieveAll() {
        try {
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
        } catch (StorageFormatException | IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Appends the task in a new line at the end of the file.
     * @param task Task to be stored.
     */
    public void store(Task task) {
        try {
            String data = task.toStorageString();
            FileWriter fw = new FileWriter(storage, true);
            fw.append(data);
            fw.append('\n');
            fw.close();
        } catch (IOException e) {
            System.out.println("STORE_IOEXCEPTION");
        }
    }

    /**
     * Finds the task on corresponding row and deletes it.
     * @param id The row from the top, starting from 0.
     * @return The deleted task, or null if it does not exist.
     */
    public Task delete(int id) throws StorageFormatException {
        try {
            Scanner sc = new Scanner(new FileReader(storage));
            StringBuilder sb = new StringBuilder();

            Task deletedTask = null;

            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (id == 0) {
                    deletedTask = validateTask(line);
                } else {
                    sb.append(line);
                    sb.append("\n");
                }
                id--;
            }
            sc.close();

            FileWriter fw = new FileWriter(storage);
            fw.append(sb);
            fw.close();

            return deletedTask;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
