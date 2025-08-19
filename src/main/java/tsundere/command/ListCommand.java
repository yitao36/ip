package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

public class ListCommand extends AbstractCommand {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        ui.listTasks(tasks);
    }
}
