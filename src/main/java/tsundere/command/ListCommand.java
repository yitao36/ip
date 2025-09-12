package tsundere.command;

import tsundere.log.Log;
import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;
import tsundere.ui.UiMessages;

/**
 * Command to list out all the tasks to the user.
 */
public class ListCommand extends AbstractCommand {
    public ListCommand() {
        super(false, false);
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, TextStorage storage, Log log) {
        ui.displayMessage(UiMessages.LIST_TASKS, tasks);
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, TextStorage storage) {}

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand listCommand;
    }

    @Override
    public int hashCode() {
        return ListCommand.class.hashCode();
    }
}
