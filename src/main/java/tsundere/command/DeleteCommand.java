package tsundere.command;

import tsundere.TsundereException;
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
            tasks.validateIndex(id);
            Task task = tasks.remove(id);
            storage.delete(id);
            ui.deleteSuccess(task);
        } catch (TsundereException e) {
            ui.echo(e.getMessage());
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
