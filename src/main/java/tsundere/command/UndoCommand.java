package tsundere.command;

import tsundere.log.Log;
import tsundere.log.TsundereNothingToUndoException;
import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;

/**
 * Resets the state of the task list to before the most recently executed command.
 */
public class UndoCommand extends AbstractCommand {
    public UndoCommand() {
        super(false, false);
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, TextStorage storage, Log log) {
        try {
            log.undo();
        } catch (TsundereNothingToUndoException e) {
            ui.displayMessage(e.getMessage());
        }
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, TextStorage storage) {}
}
