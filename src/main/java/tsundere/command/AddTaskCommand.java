package tsundere.command;

import java.io.IOException;
import java.util.Objects;

import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;
import tsundere.ui.UiMessages;

/**
 * Adds a task to the task list, storage, and prints a message to the user.
 */
public class AddTaskCommand extends AbstractCommand {
    private final Task task;

    /**
     * Adds a new task to the task list and storage.
     * @param task Task to be added
     */
    public AddTaskCommand(Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, TextStorage storage) {
        try {
            tasks.add(task);
            storage.store(task);
            ui.displayMessage(UiMessages.ADD_TASK, task);
        } catch (IOException e) {
            ui.displayMessage(e.getMessage());
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
