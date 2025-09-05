package tsundere.ui;

import tsundere.command.InvalidFormatCommand;
import tsundere.task.Task;
import tsundere.task.TaskList;

/**
 * Abstract Ui class that defines methods for printing output of Tsundere to the user.
 */
public abstract class AbstractUi {
    public abstract void showWelcome();
    public abstract void showExit();
    public abstract void listTasks(TaskList tasks);
    public abstract void addTaskSuccess(Task task);
    public abstract void markSuccess(Task task);
    public abstract void markRedundant(Task task);
    public abstract void unmarkSuccess(Task task);
    public abstract void unmarkRedundant(Task task);
    public abstract void deleteSuccess(Task task);
    public abstract void showHelp(InvalidFormatCommand.Format format);
    public abstract void echo(String s);
    public abstract void taskIndexOutOfBounds();
    public abstract void storageException();
}
