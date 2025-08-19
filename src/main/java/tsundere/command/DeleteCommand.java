package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

public class DeleteCommand extends AbstractCommand {
    private final int id;

    public DeleteCommand(int id) {
        super(false);
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        try {
            Task task = storage.delete(id);
            if (task == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.remove(id);
            ui.deleteSuccess(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }
}
