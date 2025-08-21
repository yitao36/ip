package tsundere.command;

import tsundere.storage.AlreadyMarkedException;
import tsundere.storage.StorageFormatException;
import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

import java.io.IOException;
import java.util.Objects;

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
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.taskIndexOutOfBounds();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddTaskCommand addTaskCommand) {
            return this.task.equals(addTaskCommand.task);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.task);
    }
}
