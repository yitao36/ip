package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

/**
 * Command to list out all the tasks to the user.
 */
public class ListCommand extends AbstractCommand {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        ui.listTasks(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand listCommand;
    }

    @Override
    public int hashCode() {
        return ListCommand.class.hashCode();
    }
}
