package tsundere;

import tsundere.task.Task;

/**
 * Checked exception thrown by the tsundere.Tsundere class.
 */
public class TsundereException extends Exception {
    private final Task task;

    /**
     * Creates a new checked exception with a message.
     * @param message Details to be printed to the user.
     */
    public TsundereException(String message) {
        super(message);
        this.task = null;
    }

    /**
     * Creates a new checked exception with a message and task.
     * @param message Details to be printed to the user.
     * @param task Task that was involved in the exception.
     */
    public TsundereException(String message, Task task) {
        super(message);
        this.task = task;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n" + task;
    }
}
