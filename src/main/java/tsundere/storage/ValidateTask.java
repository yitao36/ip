package tsundere.storage;

import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.Task;
import tsundere.task.TodoTask;

import static tsundere.Config.*;

public class ValidateTask {
    public static Task validateTask(String task) {
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
    private static Task validateTodo(String[] task) {
        if (task.length != 3) {
            return null;
        }
        String checked = task[1];
        String name = task[2];

        if (!(checked.equals("T") || checked.equals("F"))) {
            return null;
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
    private static Task validateDeadline(String[] task) {
        if (task.length != 4) {
            return null;
        }
        String checked = task[1];
        String name = task[2];
        String byDate = task[3];

        if (!(checked.equals("T") || checked.equals("F"))) {
            return null;
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
    private static Task validateEvent(String[] task) {
        if (task.length != 5) {
            return null;
        }
        String checked = task[1];
        String name = task[2];
        String startDate = task[3];
        String endDate = task[4];

        if (!(checked.equals("T") || checked.equals("F"))) {
            return null;
        }

        Task t = new EventTask(name, startDate, endDate);
        if (checked.equals("T")) {
            t.markDone();
        }

        return t;
    }
}
