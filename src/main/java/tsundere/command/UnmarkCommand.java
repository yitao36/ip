package tsundere.command;

import java.io.IOException;

import tsundere.TsundereException;
import tsundere.log.Log;
import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;
import tsundere.ui.UiMessages;

/**
 * Command to set the status of a task to unmarked.
 */
public class UnmarkCommand extends AbstractCommand {
    private final int id;
    private Task task;

    /**
     * Creates a new command to set the indexed task in the task list to unmarked.
     * @param id Index of the task in the TaskList to be unmarked. Must be valid.
     */
    public UnmarkCommand(int id) {
        super(false, true);
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, TextStorage storage, Log log) {
        try {
            tasks.unmark(id);
            storage.storeAll(tasks);
            Task task = tasks.get(id);
            this.task = task;
            ui.displayMessage(UiMessages.UNMARK_TASK_SUCCESS, task);
            log.add(this);
        } catch (TsundereException | IOException e) {
            ui.displayMessage(e.getMessage());
        }
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, TextStorage storage) {
        try {
            tasks.undoUnmark(id);
            storage.storeAll(tasks);
            ui.displayMessage("Successfully undid last command of unmarking the following task: \n" + task + '\n');
        } catch (IOException e) {
            ui.displayMessage(e.getMessage());
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

    @Override
    public String toString() {
        return "Unmark Command: " + task.toString();
    }
}
