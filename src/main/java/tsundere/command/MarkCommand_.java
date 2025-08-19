package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

public class MarkCommand_ extends AbstractCommand {
    private int id;

    public MarkCommand_(int id) {
        super(false);
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        try {
            Task task = storage.mark(id);
            if (task == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.set(id, task);
            ui.markSuccess(task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
