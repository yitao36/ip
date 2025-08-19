package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

public class ByeCommand extends AbstractCommand {
    public ByeCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        ui.showExit();
    }
}
