package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

public class AddTaskCommand extends AbstractCommand {
    private final Task task;

    public AddTaskCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        try {
            tasks.add(task);
            storage.store(task);
            ui.addTaskSuccess(task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
