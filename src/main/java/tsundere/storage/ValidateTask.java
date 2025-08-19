package tsundere.storage;

import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.Task;
import tsundere.task.TodoTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static tsundere.Config.*;

public class ValidateTask {
    public static Task validateTask(String task) throws StorageFormatException {
        String[] params = task.split(",");
        if (params.length == 0) {
            return null;
        } else if (params[0].equals(TASK_TODO)) {
            return validateTodo(params);
        } else if (params[0].equals(TASK_DEADLINE)) {
            return validateDeadline(params);
        } else if (params[0].equals(TASK_EVENT)) {
            return validateEvent(params);
        }
        return null;
    }

    /**
     * The string format should be `todo,[checked],[name]`
     * @param task task parameters
     * @return Task
     */
    private static Task validateTodo(String[] task) throws StorageFormatException {
        if (task.length != 3) {
            throw new StorageFormatException();
        }
        String checked = task[1];
        String name = task[2];

        if (!(checked.equals("T") || checked.equals("F"))) {
            throw new StorageFormatException();
        }

        Task t = new TodoTask(name);
        if (checked.equals("T")) {
            t.markDone();
        }
        return t;
    }

    /**
     * The string format should be `deadline,[checked],[name],[byDate]`
     * @param task task parameters
     * @return Task
     */
    private static Task validateDeadline(String[] task) throws StorageFormatException {
        if (task.length != 4) {
            throw new StorageFormatException();
        }
        String checked = task[1];
        String name = task[2];
        String by = task[3];

        if (!(checked.equals("T") || checked.equals("F"))) {
            throw new StorageFormatException();
        }

        LocalDateTime byDate;
        try {
            byDate = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (DateTimeParseException e) {
            throw new StorageFormatException();
        }

        Task t = new DeadlineTask(name, byDate);
        if (checked.equals("T")) {
            t.markDone();
        }
        return t;
    }

    /**
     * The string format should be `event,[checked],[name],[startDate],[endDate]`
     * @param task task parameters
     * @return Task
     */
    private static Task validateEvent(String[] task) throws StorageFormatException {
        if (task.length != 5) {
            throw new StorageFormatException();
        }
        String checked = task[1];
        String name = task[2];
        String start = task[3];
        String end = task[4];

        if (!(checked.equals("T") || checked.equals("F"))) {
            throw new StorageFormatException();
        }

        LocalDateTime startDate;
        LocalDateTime endDate;
        try {
            startDate = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            endDate = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (DateTimeParseException e) {
            throw new StorageFormatException();
        }
        Task t = new EventTask(name, startDate, endDate);
        if (checked.equals("T")) {
            t.markDone();
        }

        return t;
    }
}
