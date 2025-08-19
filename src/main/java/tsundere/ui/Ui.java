package tsundere.ui;

import tsundere.task.Task;
import tsundere.task.TaskList;

import static tsundere.Config.HORIZONTAL_LINE;

public class Ui {
    public Ui() {

    }

    public void showWelcome() {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("Oh! I-I- didn't expect you to be here...");
        System.out.println("What do you want...? Hmph, I'm obviously busy!");

        System.out.println(HORIZONTAL_LINE);
    }

    public void showExit() {
        System.out.println(HORIZONTAL_LINE);

        String exitMessage = "Oh... Leaving already? N-not that I care! Bye...";
        System.out.println(exitMessage);

        System.out.println(HORIZONTAL_LINE);
    }

    public void listTasks(TaskList tasks) {
        System.out.println(HORIZONTAL_LINE);
        int num = 1;

        if (tasks.isEmpty()) {
            System.out.println("There's no tasks, dummy!");
        }

        for (Task task : tasks) {
            System.out.println(num + ". ");
            System.out.println(task);
            num++;
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public void addTaskSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("New task added!");
        System.out.println(task);

        System.out.println(HORIZONTAL_LINE);
    }

    public void markSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("Here, it's marked.");
        System.out.println(task);

        System.out.println(HORIZONTAL_LINE);
    }

    public void unmarkSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("Why'd you mark it in the first place?");
        System.out.println(task);

        System.out.println(HORIZONTAL_LINE);
    }

    public void deleteSuccess(Task task) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("There, it's done and dusted.");
        System.out.println(task);

        System.out.println(HORIZONTAL_LINE);
    }

    public void showHelp(String format) {
        System.out.println(HORIZONTAL_LINE);
        if (format.equals("help")) {
            System.out.println("Here's a list of commands for your pea-brain.\n");
            System.out.println("todo - Creates a todo task");
            System.out.println("event - Creates an event task");
            System.out.println("deadline - Creates a deadline task");
            System.out.println("list - Lists all your tasks");
            System.out.println("mark - Marks a task as completed");
            System.out.println("unmark - Marks a task as uncompleted");
            System.out.println("bye - Installs a virus! Don't input this");
        } else {
            System.out.println("Wrong format!");
            switch (format) {
                case "todo":
                    System.out.println("Use `todo <name>`");
                    break;
                case "deadline":
                    System.out.println("Use `deadline <name> /by <date>`");
                    break;
                case "event":
                    System.out.println("Use `event <name> /from <date> /to <date>`");
                    break;
                case "mark":
                    System.out.println("Use `mark <position of task in list>`");
                    break;
                case "unmark":
                    System.out.println("Use `unmark <position of task in list>`");
                    break;
                case "delete":
                    System.out.println("Use `delete <position of task in list>`");
                    break;
                default:
                    System.out.println("Shouldn't get here");
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void echo(String text) {
        System.out.println(HORIZONTAL_LINE);

        System.out.println(text);

        System.out.println(HORIZONTAL_LINE);
    }
}
