package tsundere.command;

import java.io.IOException;

import tsundere.storage.AlreadyMarkedException;
import tsundere.storage.StorageFormatException;
import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;

/**
 * Command to set the status of a task to unmarked.
 */
public class UnmarkCommand extends AbstractCommand {
    private final int id;

    /**
     * Creates a new command to set the indexed task in the task list to unmarked.
     * @param id Index of the task in the TaskList to be unmarked. Must be valid.
     */
    public UnmarkCommand(int id) {
        super(false);
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, TextStorage storage) {
        try {
            Task task = storage.unmark(id);
            if (task == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.set(id, task);
            ui.unmarkSuccess(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.taskIndexOutOfBounds();
        } catch (AlreadyMarkedException e) {
            ui.unmarkRedundant(e.getTask());
        } catch (StorageFormatException | IOException e) {
            ui.storageException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnmarkCommand unmarkCommand) {
            return this.id == unmarkCommand.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return UnmarkCommand.class.hashCode();
    }
}
