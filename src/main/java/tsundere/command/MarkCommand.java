package tsundere.command;

import java.io.IOException;

import tsundere.TsundereException;
import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;
import tsundere.ui.UiMessages;

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
            tasks.mark(id);
            Task task = tasks.get(id);
            storage.storeAll(tasks);
            ui.displayMessage(UiMessages.MARK_TASK_SUCCESS, task);
        } catch (TsundereException | IOException e) {
            ui.displayMessage(e.getMessage());
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
