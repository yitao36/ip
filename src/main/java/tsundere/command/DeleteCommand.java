package tsundere.command;

import tsundere.storage.StorageFormatException;
import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;

/**
 * Deletes a task from the task list and storage, and prints a message to the user.
 */
public class DeleteCommand extends AbstractCommand {
    private final int id;

    /**
     * Creates a new command to delete an existing task.
     * @param id Index of task in the task list to be deleted. Must be valid.
     */
    public DeleteCommand(int id) {
        super(false);
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, TextStorage storage) {
        try {
            Task task = storage.delete(id);
            if (task == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.remove(id);
            ui.deleteSuccess(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.taskIndexOutOfBounds();
        } catch (StorageFormatException e) {
            ui.storageException();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand deleteCommand) {
            return this.id == deleteCommand.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return DeleteCommand.class.hashCode();
    }
}
