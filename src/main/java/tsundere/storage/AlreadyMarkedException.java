package tsundere.storage;

import tsundere.TsundereException;
import tsundere.task.Task;

/**
 * Thrown if the Mark command attempts to mark a task that is already marked.
 */
public class AlreadyMarkedException extends TsundereException {
    /**
     * Creates a new Exception with the specified task.
     * @param task The task involved in the exception
     */
    public AlreadyMarkedException(Task task) {
        super("Hey!! It's already marked!\n" + task.toString() + '\n');
    }
}
