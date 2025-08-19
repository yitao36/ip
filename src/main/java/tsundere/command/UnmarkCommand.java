package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
