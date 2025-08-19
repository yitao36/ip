package tsundere.storage;

import tsundere.task.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static tsundere.storage.ValidateTask.validateTask;

public class TextStorage {
    private final String storage;

    public TextStorage(String storage) {
        this.storage = storage;
    }

    /**
     * Finds the task on the line id, and updates F to T.
     * @param id The row from the top, starting from 0.
     * @return The modified task, or null if it does not exist.
     * @throws AlreadyMarkedException The task is already marked.
     * @throws StorageFormatException Storage text is unable to be parsed.
     * @throws IOException Storage file is unable to be opened.
     */
    public Task mark(int id) throws AlreadyMarkedException, StorageFormatException, IOException {
        Scanner sc = new Scanner(new FileReader(storage));
        StringBuilder sb = new StringBuilder();

        Task t = null;

        while (sc.hasNext()) {
            String line = sc.nextLine();
            if (id == 0) {
                t = validateTask(line);
                if (t == null) {
                    throw new StorageFormatException();
                } else if (t.isDone()) {
                    // Do not need to change file, return early
                    sc.close();
                    throw new AlreadyMarkedException(t);
                } else {
                    t.markDone();
                }
                sb.append(t.toStorageString());
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
        return t;
    }

    /**
     * Finds the task on the line id, and updates T to F.
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

    public Task retrieve(int id) throws StorageFormatException {
        try {
            Scanner sc = new Scanner(new FileReader(storage));

            while (sc.hasNext() && id > 0) {
                String line = sc.nextLine();

                Task task = validateTask(line);

                if (task == null) {
                    throw new StorageFormatException();
                }

                id--;
                if (id == 0) {
                    sc.close();
                    return task;
                }
            }
            sc.close();
            return null;
        } catch (FileNotFoundException | StorageFormatException e) {
            return null;
        }
    }

    public Task[] retrieveAll() {
        try {
            File file = new File(storage);

            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.out.println("Error creating storage file..");
                }
            }

            Scanner sc = new Scanner(file);
            List<Task> arr = new ArrayList<>();

            while (sc.hasNext()) {
                String line = sc.nextLine();
                Task task = validateTask(line);

                if (task == null) {
                    throw new StorageFormatException();
                }

                arr.add(task);
            }
            sc.close();
            return arr.toArray(new Task[0]);
        } catch (FileNotFoundException e) {
            System.out.println("NOTFOUND");
            return null;
        } catch (StorageFormatException e) {
            System.out.println("STORAGE");
            return null;
        } catch (IOException e) {

            System.out.println("IO");
            return null;
        }
    }

    public void store(Task t) {
        try {
            String data = t.toStorageString();
            FileWriter fw = new FileWriter(storage, true);
            fw.append(data);
            fw.append('\n');
            fw.close();
        } catch (IOException e) {
            System.out.println("STORE_IOEXCEPTION");
        }
    }

    public Task delete(int id) {
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
}
