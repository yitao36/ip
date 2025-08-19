package tsundere;

import tsundere.task.Task;

public class TsundereException extends Exception {
    private final Task task;

    public TsundereException(String message) {
        super(message);
        this.task = null;
    }

    public TsundereException(String message, Task task) {
        super(message);
        this.task = task;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n" + task;
    }
}
