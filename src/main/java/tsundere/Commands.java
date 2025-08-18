package tsundere;

import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.Task;
import tsundere.task.TodoTask;

import java.util.ArrayList;
import java.util.List;

import static tsundere.Config.*;

public class Commands {
    public static final List<Task> tasks = new ArrayList<>();
    public static int numTasks = 0;

    public static void init() {
        System.out.println(HORIZONTAL_LINE);

        System.out.println("Oh! I-I- didn't expect you to be here...");
        System.out.println("What do you want...? Hmph, I'm obviously busy!");

        System.out.println(HORIZONTAL_LINE);
    }

    public static void exit() {
        System.out.println(HORIZONTAL_LINE);

        String exitMessage = "Oh... Leaving already? N-not that I care! Bye...";
        System.out.println(exitMessage);

        System.out.println(HORIZONTAL_LINE);
    }

    public static void addTodo(String message) {
        System.out.println(HORIZONTAL_LINE);

        tasks.add(new TodoTask(message));
        System.out.println("New todo task added!");
        System.out.println(tasks.get(numTasks));
        numTasks++;

        System.out.println(HORIZONTAL_LINE);
    }

    public static void addDeadline(String[] params) {
        String message = params[0];
        String by = params[1];

        System.out.println(HORIZONTAL_LINE);

        tasks.add(new DeadlineTask(message, by));
        System.out.println("New deadline task added!");
        System.out.println(tasks.get(numTasks));
        numTasks++;

        System.out.println(HORIZONTAL_LINE);
    }

    public static void addEvent(String[] params) {
        String message = params[0];
        String from = params[1];
        String to = params[2];

        System.out.println(HORIZONTAL_LINE);

        tasks.add(new EventTask(message, from, to));
        System.out.println("New event task added!");
        System.out.println(tasks.get(numTasks));
        numTasks++;

        System.out.println(HORIZONTAL_LINE);
    }

    public static void list() {
        System.out.println(HORIZONTAL_LINE);

        if (numTasks == 0) {
            System.out.println("There's no tasks, dummy!");
        }

        for (int i = 0; i < numTasks; i++) {
            System.out.print((i+1) + ". ");
            System.out.println(tasks.get(i));
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public static void mark(String idString) {
        System.out.println(HORIZONTAL_LINE);

        try {
            if (idString == null) {
                throw new MissingArgumentException();
            }
            int id = Integer.parseInt(idString) - 1;
            if (id >= numTasks || id < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (tasks.get(id).isDone()) {
                System.out.println("Ehh? It's already marked! You probably input the wrong number, silly.");
                System.out.println(tasks.get(id));
            } else {
                tasks.get(id).markDone();
                System.out.println("Here, it's marked.");
                System.out.println(tasks.get(id));
            }
        } catch (NumberFormatException e) {
            System.out.println("That's not a valid number, baka!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ugh! That task does not exist. You know I'm busy, right?");
        } catch (MissingArgumentException e) {
            System.out.println("Don't leave me hanging!");
            System.out.println("Use `mark <task id>");
        } finally {
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void unmark(String idString) {
        System.out.println(HORIZONTAL_LINE);

        try {
            if (idString == null) {
                throw new MissingArgumentException();
            }
            int id = Integer.parseInt(idString) - 1;
            if (id >= numTasks || id < 0) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (!tasks.get(id).isDone()) {
                System.out.println("Dude!! That task isn't even done yet!");
                System.out.println(tasks.get(id));
            } else {
                tasks.get(id).markUndone();
                System.out.println("Why'd you even mark it done?");
                System.out.println(tasks.get(id));
            }
        } catch (NumberFormatException e) {
            System.out.println("That's not a valid number, baka!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ugh! That task does not exist. You know I'm busy, right?");
        } catch (MissingArgumentException e) {
            System.out.println("Don't leave me hanging!");
            System.out.println("Use `unmark <task id>");
        } finally {
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void delete(String idString) {
        System.out.println(HORIZONTAL_LINE);

        try {
            if (idString == null) {
                throw new MissingArgumentException();
            }
            int id = Integer.parseInt(idString) - 1;
            if (id >= numTasks || id < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            Task t = tasks.remove(id);
            numTasks--;
            System.out.println("I've removed the task.");
            System.out.println(t);

        } catch (NumberFormatException e) {
            System.out.println("That's not a valid number, baka!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ugh! That task does not exist. You know I'm busy, right?");
        } catch (MissingArgumentException e) {
            System.out.println("Don't leave me hanging!");
            System.out.println("Use `delete <task id>");
        }

        System.out.println(HORIZONTAL_LINE);
    }
}
