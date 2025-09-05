package tsundere.command;

import tsundere.log.Log;
import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;

import java.io.IOException;

/**
 * Command that displays a help message to the user for the correct format for the specified Format type.
 * @see Format
 */
public class InvalidFormatCommand extends AbstractCommand {

    /**
     * Enumerates the types of commands that the user has.
     */
    public enum Format {
            TODO, DEADLINE, EVENT, EVENT_DATE, FIND, MARK, UNMARK, DELETE, HELP
    }

    private final Format format;

    /**
     * Creates a new command for one of the Format type.
     * @param format The type of Format
     * @see Format
     * */
    public InvalidFormatCommand(Format format) {
        super(false, false);
        this.format = format;
    }
    @Override
<<<<<<< Updated upstream
    public void execute(TaskList tasks, AbstractUi ui, TextStorage storage) {
        ui.showHelp(format);
=======
    public void execute(TaskList tasks, AbstractUi ui, TextStorage storage, Log log) {
        ui.displayMessage(format);
>>>>>>> Stashed changes
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, TextStorage storage) {}

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
