package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

public class InvalidFormatCommand extends AbstractCommand {
    public enum Format {
            TODO, DEADLINE, EVENT, EVENT_DATE, FIND, MARK, UNMARK, DELETE, HELP
    }

    private final Format format;

    public InvalidFormatCommand(Format format) {
        super(false);
        this.format = format;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        ui.showHelp(format);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InvalidFormatCommand command) {
            return this.format.equals(command.format);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.format.hashCode();
    }
}
