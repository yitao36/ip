package tsundere.ui;

import static tsundere.Config.HORIZONTAL_LINE;
import static tsundere.command.InvalidFormatCommand.Format;

import tsundere.task.Task;
import tsundere.task.TaskList;

/**
 * Interface that handles the printing of strings to the terminal.
 */
public class TerminalUi extends AbstractUi {
    public TerminalUi() {

    }

    /**
     * Prints a welcome message to the user on initialization of program.
     */
    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.WELCOME));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints an exit message to the user on exiting the program.
     */
    public void showExit() {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.EXIT));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the list of tasks and their indexes to the user.
     *
     * @param tasks The list of tasks to be printed.
     */
    public void listTasks(TaskList tasks) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.LIST_TASKS, tasks));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a success message upon adding the task to the task list.
     */
    public void addTaskSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.ADD_TASK, task));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a success message upon marking the task as completed.
     */
    public void markSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.MARK_TASK_SUCCESS, task));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a special message if the task was already completed when marking it as completed.
     */
    public void markRedundant(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.MARK_TASK_REDUNDANT, task));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a success message upon unmarking the task as not completed.
     */
    public void unmarkSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.UNMARK_TASK_SUCCESS, task));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a special message if the task was not yet completed when unmarking the task.
     */
    public void unmarkRedundant(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.UNMARK_TASK_REDUNDANT, task));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a success message upon deleting the task from the task list.
     */
    public void deleteSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.DELETE_SUCCESS, task));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * The specific format use is shown to the user when their command does not follow the correct format.
     * Also shows all available commands when input is empty or 'help'.
     *
     * @param format The format to be explained to the user.
     */
    public void showHelp(Format format) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.HELP, format));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the user input back as output, if it does not match any of the available commands.
     */
    public void echo(String text) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.ECHO, text));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints an error message for trying to perform any operation on a non-existent task index.
     */
    public void taskIndexOutOfBounds() {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.INDEX_ERROR));

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints an error message for any errors associated with storage storing and retrieval.
     */
    public void storageException() {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(UiMessages.getMessage(UiMessages.STORAGE_ERROR));

        System.out.println(HORIZONTAL_LINE);
    }
}
