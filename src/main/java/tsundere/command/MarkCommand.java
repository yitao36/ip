package tsundere.command;

import java.io.IOException;

<<<<<<< Updated upstream
import tsundere.storage.AlreadyMarkedException;
import tsundere.storage.StorageFormatException;
=======
import tsundere.TsundereException;
import tsundere.log.Log;
>>>>>>> Stashed changes
import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;

/**
 * Command to set the status of a task to marked.
 */
public class MarkCommand extends AbstractCommand {
    private final int id;
    private Task task;

    /**
     * Creates a new command to mark the task as completed
     * @param id The index of the task to be marked. Must be valid.
     */
    public MarkCommand(int id) {
        super(false, true);
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, TextStorage storage, Log log) {
        try {
<<<<<<< Updated upstream
            Task task = storage.mark(id);
            if (task == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.set(id, task);
            ui.markSuccess(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.taskIndexOutOfBounds();
        } catch (AlreadyMarkedException e) {
            ui.markRedundant(e.getTask());
        } catch (StorageFormatException | IOException e) {
            ui.storageException();
=======
            tasks.mark(id);
            Task task = tasks.get(id);
            this.task = task;
            storage.storeAll(tasks);
            ui.displayMessage(UiMessages.MARK_TASK_SUCCESS, task);
            log.add(this);
        } catch (TsundereException | IOException e) {
            ui.displayMessage(e.getMessage());
>>>>>>> Stashed changes
        }
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, TextStorage storage) {
        try {
            tasks.undoMark(id);
            storage.storeAll(tasks);
            ui.displayMessage("Successfully undid last command of marking the following task: \n" + task + '\n');
        } catch (IOException e) {
            ui.displayMessage(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MarkCommand markCommand) {
            return this.id == markCommand.id;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return MarkCommand.class.hashCode();
    }
}
