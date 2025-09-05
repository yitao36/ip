package tsundere.command;

import java.util.Objects;

import tsundere.log.Log;
import tsundere.storage.TextStorage;
import tsundere.task.Task;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;

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
        super(false, true);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, AbstractUi ui, TextStorage storage, Log log) {
        try {
            tasks.add(task);
<<<<<<< Updated upstream
            storage.store(task);
            ui.addTaskSuccess(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.taskIndexOutOfBounds();
=======
            storage.storeAll(tasks);
            ui.displayMessage(UiMessages.ADD_TASK, task);
            log.add(this);
        } catch (IOException e) {
            ui.displayMessage(e.getMessage());
        }
    }

    @Override
    public void undo(TaskList tasks, AbstractUi ui, TextStorage storage) {
        try {
            tasks.undoAdd();
            storage.storeAll(tasks);
            ui.displayMessage("Successfully undid the last action of adding the following task: \n" + task + "\n");
        } catch (IOException e) {
            ui.displayMessage(e.getMessage());
>>>>>>> Stashed changes
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
