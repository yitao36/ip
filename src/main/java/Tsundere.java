import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

class Task {
    private final String name;
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    public String toString() {
        return "[" + (done ? "X" : "") + "] " + this.name;
    }
}

public class Tsundere {
    public static final int WIDTH = 40;
    public static final String HORIZONTAL_LINE = "_".repeat(WIDTH);
    public static final Task[] tasks = new Task[100];
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

        tasks[numTasks] = new Task(message);
        numTasks++;
        System.out.println("I've added " + message + " to the list.");

        System.out.println(HORIZONTAL_LINE);
    }

    public static void list() {
        System.out.println(HORIZONTAL_LINE);

        if (numTasks == 0) {
            System.out.println("There's no tasks, dummy!");
        }

        for (int i = 0; i < numTasks; i++) {
            System.out.print((i+1) + ". ");
            System.out.println(tasks[i]);
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public static void mark(String idString) {
        System.out.println(HORIZONTAL_LINE);

        int id;
        try {
            id = Integer.parseInt(idString) - 1;
            if (id >= numTasks || id < 0) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (tasks[id].isDone()) {
                System.out.println("Ehh? It's already marked! You probably input the wrong number, silly.");
                System.out.println(tasks[id]);
            }
            tasks[id].markDone();
            System.out.println("Here, it's marked.");
            System.out.println(tasks[id]);

        } catch (NumberFormatException e) {
            System.out.println("That's not a valid number, baka!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ugh! That task does not exist. You know I'm busy, right?");
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public static void unmark(String idString) {
        int id;
        try {
            id = Integer.parseInt(idString) - 1;
            if (id >= numTasks || id < 0) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (!tasks[id].isDone()) {
                System.out.println("Dude!! That task isn't even done yet!");
                System.out.println(tasks[id]);
            }
            tasks[id].markUndone();
            System.out.println("Why'd you even mark it done?");
            System.out.println(tasks[id]);

        } catch (NumberFormatException e) {
            System.out.println("That's not a valid number, baka!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ugh! That task does not exist. You know I'm busy, right?");
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        init();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(br);

        outer:
        while (true) {
            String line = sc.nextLine();
            String[] words = line.split(" ");

            String command = words.length > 0 ? words[0] : "noInput";

            switch (command) {
                case "todo":
                    String message = words.length > 1
                            ? Arrays.stream(words).skip(2).reduce(words[1],
                                (prev, next) -> prev + " " + next)
                            : "";
                    if (message.isEmpty()) {
                        System.out.println("Use the format `todo <Name>`");
                    } else {
                        addTodo(message);
                    }
                    break;
                case "list":
                    list();
                    break;
                case "mark":
                    String id = words.length > 1
                            ? words[1]
                            : null;
                    mark(id);
                    break;
                case "unmark":
                    String id2 = words.length > 1
                            ? words[1]
                            : null;
                    unmark(id2);
                    break;
                case "bye":
                    break outer;
                default:
                    System.out.println(line);
                    continue;
            }
        }

        exit();
    }
}
