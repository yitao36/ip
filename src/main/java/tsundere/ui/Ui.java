package tsundere.ui;

import tsundere.command.InvalidFormatCommand;

import static tsundere.command.InvalidFormatCommand.Format;

import tsundere.task.Task;
import tsundere.task.TaskList;

import static tsundere.Config.HORIZONTAL_LINE;

public class Ui {
    public Ui() {

    }

    /**
     * Prints a welcome message to the user on initialization of program.
     */
    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("Oh! I-I- didn't expect you to be here...");
        System.out.println("What do you want...? Hmph, I'm obviously busy!");

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints an exit message to the user on exiting the program.
     */
    public void showExit() {
        System.out.println(HORIZONTAL_LINE);

        String exitMessage = "Oh... Leaving already? N-not that I care! Bye...";
        System.out.println(exitMessage);

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the list of tasks and their indexes to the user.
     *
     * @param tasks The list of tasks to be printed.
     */
    public void listTasks(TaskList tasks) {
        System.out.println(HORIZONTAL_LINE);
        int num = 1;

        if (tasks.isEmpty()) {
            System.out.println("There's no tasks, dummy!");
        }

        for (Task task : tasks) {
            System.out.print(num + ". ");
            System.out.println(task);
            num++;
        }

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a success message upon adding the task to the task list.
     */
    public void addTaskSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("New task added!");
        System.out.println(task);

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a success message upon marking the task as completed.
     */
    public void markSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("Here, it's marked.");
        System.out.println(task);

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a special message if the task was already completed when marking it as completed.
     */
    public void markRedundant(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("It's already marked, dummy!");
        System.out.println(task);

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a success message upon unmarking the task as not completed.
     */
    public void unmarkSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("Why'd you mark it in the first place?");
        System.out.println(task);

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a special message if the task was not yet completed when unmarking the task.
     */
    public void unmarkRedundant(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("Hey!! You haven't even done the task!");
        System.out.println(task);

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a success message upon deleting the task from the task list.
     */
    public void deleteSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("There, it's done and dusted.");
        System.out.println(task);

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

        switch (format) {
        case HELP -> {
            System.out.println("Here's a list of commands for your pea-brain.\n");
            System.out.println("todo - Creates a todo task");
            System.out.println("event - Creates an event task");
            System.out.println("deadline - Creates a deadline task");
            System.out.println("list - Lists all your tasks");
            System.out.println("mark - Marks a task as completed");
            System.out.println("unmark - Marks a task as uncompleted");
            System.out.println("bye - Installs a virus! Don't input this");
        }
        case TODO -> {
            System.out.println("Wrong format!");
            System.out.println("Use `todo <name>`");
        }
        case DEADLINE -> {
            System.out.println("Wrong format!");
            System.out.println("Use `deadline <name> /by <YYYY-MM-DDTHH:MM>`");
            System.out.println("Example: deadline TASK /by 2025-02-28T23:59");
        }
        case EVENT -> {
            System.out.println("Wrong format!");
            System.out.println("Use `event <name> /from <YYYY-MM-DDTHH:MM> \n/to <YYYY-MM-DDTHH:MM>`");
            System.out.println("Example: `event TASK /from 2025-02-28T23:59 \n/to 2025-02-29T23:59`");
        }
        case EVENT_DATE -> {
            System.out.println("Invalid date order!");
            System.out.println("From date should be before To date!");
        }
        case FIND -> {
            System.out.println("Wrong format!");
            System.out.println("Use `find <name>`");
        }
        case MARK -> {
            System.out.println("Wrong format!");
            System.out.println("Use `mark <position of task in list>`");
        }
        case UNMARK -> {
            System.out.println("Wrong format!");
            System.out.println("Use `unmark <position of task in list>`");
        }
        case DELETE -> {
            System.out.println("Wrong format!");
            System.out.println("Use `delete <position of task in list>`");
        }
        }

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the user input back as output, if it does not match any of the available commands.
     */
    public void echo(String text) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(text);

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints an error message for trying to perform any operation on a non-existent task index.
     */
    public void taskIndexOutOfBounds() {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("That's not a valid index, baka!!");

        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints an error message for any errors associated with storage storing and retrieval.
     */
    public void storageException() {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("Storage data seems to be corrupted.");

        System.out.println(HORIZONTAL_LINE);
    }
}
