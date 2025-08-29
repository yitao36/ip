package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

/**
 * Prints a goodbye message to the user and exits the program.
 */
public class ByeCommand extends AbstractCommand {
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        ui.showExit();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }

    @Override
    public int hashCode() {
        return ByeCommand.class.hashCode();
    }
}
