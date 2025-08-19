package tsundere.command;

import tsundere.storage.AlreadyMarkedException;
import tsundere.storage.StorageFormatException;
import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

import java.io.IOException;

public class UnmarkCommand extends AbstractCommand {
    private int id;

    public UnmarkCommand(int id) {
        super(false);
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        try {
            Task task = storage.unmark(id);
            if (task == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.set(id, task);
            ui.unmarkSuccess(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.taskIndexOutOfBounds();
        } catch (AlreadyMarkedException e) {
            ui.markRedundant(e.getTask());
        } catch (StorageFormatException | IOException e) {
            ui.storageException();
        }
    }
}
