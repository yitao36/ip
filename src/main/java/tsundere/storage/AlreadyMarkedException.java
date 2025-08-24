package tsundere.storage;

import tsundere.task.Task;

public class AlreadyMarkedException extends Exception {
    private final Task task;
    public AlreadyMarkedException(Task task) {
        super();
        this.task = task;
    }

    public Task getTask() {
        return task;
    }
}
