package tsundere.ui;

import java.util.Objects;

import tsundere.command.InvalidFormatCommand;
import tsundere.task.Task;
import tsundere.task.TaskList;

/**
 * Lists the available commands that should display a message, and a function to return the message.
 */
public enum UiMessages {
    WELCOME, EXIT, ECHO,
    LIST_TASKS,
    ADD_TASK, MARK_TASK_SUCCESS, UNMARK_TASK_SUCCESS,
    MARK_TASK_REDUNDANT, UNMARK_TASK_REDUNDANT,
    DELETE_SUCCESS, HELP,
    INDEX_ERROR, STORAGE_ERROR;

    public static String getMessage(UiMessages ui) {
        StringBuilder sb = new StringBuilder();

        switch (ui) {
        case WELCOME -> {
            sb.append("Oh! I-I- didn't expect you to be here...\n");
            sb.append("What do you want...? Hmph, I'm obviously busy!\n");
            sb.append("Anyways, if you need help, just type `help` or an empty input.\n");
        }
        case EXIT -> {
            sb.append("Oh... Leaving already? N-not that I care! Bye...\n");
        }
        case INDEX_ERROR -> {
            sb.append("That's not a valid index, baka!!\n");
        }
        case STORAGE_ERROR -> {
            sb.append("Storage data seems to be corrupted.\n");
        }
        default -> { }
        }
        return sb.toString();
    }

    /**
     * Returns a message listing out all the tasks.
     * @param ui Should be LIST_TASKS
     * @param tasks Tasks to be displayed
     * @return String that lists out the tasks
     */
    public static String getMessage(UiMessages ui, TaskList tasks) {
        StringBuilder sb = new StringBuilder();

        if (Objects.requireNonNull(ui) == UiMessages.LIST_TASKS) {
            if (tasks.isEmpty()) {
                sb.append("There's no tasks, dummy!\n");
            } else {
                int num = 1;
                for (Task task : tasks) {
                    sb.append(num).append(". ").append(task).append('\n');
                    num++;
                }
            }
        }
        return sb.toString();
    }

    /**
     * Returns a message for the respective type, and prints the task.
     * @param ui Type of command
     * @param task Task to be displayed
     * @return String containing message for the respective command
     */
    public static String getMessage(UiMessages ui, Task task) {
        StringBuilder sb = new StringBuilder();

        switch (ui) {
        case ADD_TASK -> {
            sb.append("New task added!\n");
        }
        case MARK_TASK_SUCCESS -> {
            sb.append("Here, it's marked.\n");
        }
        case MARK_TASK_REDUNDANT -> {
            sb.append("It's already marked, dummy!\n");
        }
        case UNMARK_TASK_SUCCESS -> {
            sb.append("Why'd you mark it in the first place?\n");
        }
        case UNMARK_TASK_REDUNDANT -> {
            sb.append("Hey!! You haven't even done the task!\n");
        }
        case DELETE_SUCCESS -> {
            sb.append("There, it's done and dusted.\n");
        }
        default -> { }
        }
        sb.append(task);
        return sb.toString();
    }

    /**
     * Echoes back the user's input.
     * @param ui Should be ECHO
     * @param s The user input to be echoed back
     * @return A String echoing the user's input
     */
    public static String getMessage(UiMessages ui, String s) {
        if (Objects.requireNonNull(ui) == UiMessages.ECHO) {
            return s;
        }
        return null;
    }

    /**
     * Returns a help message, depending on the category of help.
     * @param ui Should be HELP
     * @param format The command that needs help with
     * @return A String containing helpful messages for the commands
     */
    public static String getMessage(UiMessages ui, InvalidFormatCommand.Format format) {
        if (ui != UiMessages.HELP) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        switch (format) {
        case HELP -> {
            sb.append("Here's a list of commands for your pea-brain.\n\n");
            sb.append("todo - Creates a todo task\n");
            sb.append("event - Creates an event task\n");
            sb.append("deadline - Creates a deadline task\n");
            sb.append("list - Lists all your tasks\n");
            sb.append("mark - Marks a task as completed\n");
            sb.append("unmark - Marks a task as uncompleted\n");
            sb.append("bye - Installs a virus! Don't input this\n");
        }
        case TODO -> {
            sb.append("Wrong format!\n");
            sb.append("Use `todo <name>`\n");
        }
        case DEADLINE -> {
            sb.append("Wrong format!\n");
            sb.append("Use `deadline <name> /by <YYYY-MM-DDTHH:MM>`\n");
            sb.append("Example: deadline TASK /by 2025-02-28T23:59");
        }
        case EVENT -> {
            sb.append("Wrong format!");
            sb.append("Use `event <name> /from <YYYY-MM-DDTHH:MM> \n/to <YYYY-MM-DDTHH:MM>`\n");
            sb.append("Example: `event TASK /from 2025-02-28T23:59 \n/to 2025-02-29T23:59`\n");
        }
        case EVENT_DATE -> {
            sb.append("Invalid date order!\n");
            sb.append("From date should be before To date!\n");
        }
        case FIND -> {
            sb.append("Wrong format!\n");
            sb.append("Use `find <name>`\n");
        }
        case MARK -> {
            sb.append("Wrong format!\n");
            sb.append("Use `mark <position of task in list>`\n");
        }
        case UNMARK -> {
            sb.append("Wrong format!\n");
            sb.append("Use `unmark <position of task in list>`\n");
        }
        case DELETE -> {
            sb.append("Wrong format!\n");
            sb.append("Use `delete <position of task in list>`\n");
        }
        default -> { }
        }
        return sb.toString();
    }
}
