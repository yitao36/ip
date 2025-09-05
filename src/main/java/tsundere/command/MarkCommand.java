package tsundere.command;

import java.io.IOException;

import tsundere.storage.AlreadyMarkedException;
import tsundere.storage.StorageFormatException;
import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;

/**
 * Command to set the status of a task to marked.
 */
public class MarkCommand extends AbstractCommand {
    private final int id;

    /**
     * Creates a new command to mark the task as completed
     * @param id The index of the task to be marked. Must be valid.
     */
    public MarkCommand(int id) {
        super(false);
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, TextStorage storage) {
        try {
            Task task = storage.mark(id);
            if (task == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.set(id, task);
            ui.markSuccess(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.taskIndexOutOfBounds();
        } catch (AlreadyMarkedException e) {
            ui.markRedundant(e.getTask());
        } catch (StorageFormatException | IOException e) {
            ui.storageException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkCommand markCommand) {
            return this.id == markCommand.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return MarkCommand.class.hashCode();
    }
}
