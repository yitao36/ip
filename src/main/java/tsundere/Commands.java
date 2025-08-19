package tsundere;

import tsundere.storage.AlreadyMarkedException;
import tsundere.storage.StorageFormatException;
import tsundere.storage.TextStorage;
import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.Task;
import tsundere.task.TodoTask;

import java.io.IOException;

import static tsundere.Config.*;

public class Commands {
    private static final TextStorage storage = new TextStorage("./src/main/java/tsundere/storage/tsundereStorage.txt");

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

        Task todo = new TodoTask(message);
        storage.store(todo);
        System.out.println("New todo task added!");
        System.out.println(todo);

        System.out.println(HORIZONTAL_LINE);
    }

    public static void addDeadline(String[] params) {
        String message = params[0];
        String by = params[1];

        System.out.println(HORIZONTAL_LINE);

        Task deadline = new DeadlineTask(message, by);
        storage.store(deadline);
        System.out.println("New deadline task added!");
        System.out.println(deadline);

        System.out.println(HORIZONTAL_LINE);
    }

    public static void addEvent(String[] params) {
        String message = params[0];
        String from = params[1];
        String to = params[2];

        System.out.println(HORIZONTAL_LINE);

        Task event = new EventTask(message, from, to);
        storage.store(event);
        System.out.println("New event task added!");
        System.out.println(event);

        System.out.println(HORIZONTAL_LINE);
    }

    public static void list() {
        System.out.println(HORIZONTAL_LINE);

        Task[] tasks = storage.retrieveAll();
        if (tasks == null || tasks.length == 0) {
            System.out.println("There's no tasks, dummy!");
            tasks = new Task[] {};
        }

        for (int i = 0; i < tasks.length; i++) {
            System.out.print((i+1) + ". ");
            System.out.println(tasks[i]);
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

            Task task = storage.mark(id);
            if (task == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
            System.out.println("Here, it's marked.");
            System.out.println(task);

        } catch (NumberFormatException e) {
            System.out.println("That's not a valid number, baka!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ugh! That task does not exist. You know I'm busy, right?");
        } catch (MissingArgumentException e) {
            System.out.println("Don't leave me hanging!");
            System.out.println("Use `mark <task id>");
        } catch (AlreadyMarkedException e) {
            System.out.println("Ehh? It's already marked! You probably input the wrong number, silly.");
            System.out.println(e.getTask());
        } catch (IOException e) {
            System.out.println("It seems I'm unable to open the storage file...");
        } catch (StorageFormatException e) {
            System.out.println("Storage text seems to be corrupted!");
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

            Task task = storage.unmark(id);
            if (task == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
            System.out.println("Why'd you even mark it done?");
            System.out.println(task);

        } catch (NumberFormatException e) {
            System.out.println("That's not a valid number, baka!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ugh! That task does not exist. You know I'm busy, right?");
        } catch (MissingArgumentException e) {
            System.out.println("Don't leave me hanging!");
            System.out.println("Use `unmark <task id>");
        } catch (AlreadyMarkedException e) {
            System.out.println("Dude!! That task isn't even done yet!");
            System.out.println(e.getTask());
        } catch (IOException e) {
            System.out.println("It seems I'm unable to open the storage file...");
        } catch (StorageFormatException e) {
            System.out.println("Storage text seems to be corrupted!");
        }
    }

    public static void delete(String idString) {
        System.out.println(HORIZONTAL_LINE);

        try {
            if (idString == null) {
                throw new MissingArgumentException();
            }

            int id = Integer.parseInt(idString) - 1;
            Task t = storage.delete(id);
            if (t == null) {
                throw new ArrayIndexOutOfBoundsException();
            }
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
