package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

public class InvalidFormatCommand extends AbstractCommand {
    private final String format;
    public InvalidFormatCommand(String format) {
        super(false);
        this.format = format;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        ui.showHelp(format);
    }
}
