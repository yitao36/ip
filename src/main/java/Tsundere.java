import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

abstract class Task {
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

class TodoTask extends Task {
    public TodoTask (String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class DeadlineTask extends Task {
    private final String deadline;
    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + deadline + ")";
    }
}

class EventTask extends Task {
    private final String from;
    private final String to;

    public EventTask(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + from + " to " + to + ")";
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

        tasks[numTasks] = new TodoTask(message);
        numTasks++;
        System.out.println("I've added " + message + " to the list.");

        System.out.println(HORIZONTAL_LINE);
    }

    public static void addDeadline(String[] params) {
        if (params == null) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Incorrect format!");
            System.out.println("Use 'deadline <message> /by <date>'");
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        String message = params[0];
        String by = params[1];

        System.out.println(HORIZONTAL_LINE);

        tasks[numTasks] = new DeadlineTask(message, by);
        System.out.println("New deadline task added!");
        System.out.println(tasks[numTasks]);
        numTasks++;

        System.out.println(HORIZONTAL_LINE);
    }

    public static void addEvent(String[] params) {
        if (params == null) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Incorrect format!");
            System.out.println("Use 'event <message> /from <time> /to <time>'");
            System.out.println(HORIZONTAL_LINE);
            return;
        }

        String message = params[0];
        String from = params[1];
        String to = params[2];

        System.out.println(HORIZONTAL_LINE);

        tasks[numTasks] = new EventTask(message, from, to);
        System.out.println("New event task added!");
        System.out.println(tasks[numTasks]);
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

    public static String[] parseEvent(String[] words) {
        StringBuilder message = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();

        StringBuilder[] sb = new StringBuilder[] {message, from, to};
        int type = 0;

        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/from")) {
                if (type == 1 || !from.isEmpty()) {
                    sb[type].append(words[i]);
                    sb[type].append(' ');
                }
                type = Math.max(type, 1);
            } else if (words[i].equals("/to")) {
                if (type == 2 || !to.isEmpty()) {
                    sb[type].append(words[i]);
                    sb[type].append(' ');
                }
                type = 2;
            } else {
                sb[type].append(words[i]);
                sb[type].append(' ');
            }
        }
        for (StringBuilder stringBuilder : sb) {
            if (stringBuilder.isEmpty()) {
                return null;
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

        String[] str = new String[3];
        return Arrays.stream(sb).map(StringBuilder::toString).toList().toArray(str);
    }

    public static String[] parseDeadline(String[] words) {
        StringBuilder message = new StringBuilder();
        StringBuilder by = new StringBuilder();

        StringBuilder[] sb = new StringBuilder[] {message, by};
        int type = 0;

        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/by")) {
                if (type == 1 || !by.isEmpty()) {
                    sb[type].append(words[i]);
                    sb[type].append(' ');
                }
                type = 1;
            } else {
                sb[type].append(words[i]);
                sb[type].append(' ');
            }
        }
        for (StringBuilder stringBuilder : sb) {
            if (stringBuilder.isEmpty()) {
                return null;
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
        }

        String[] str = new String[3];
        return Arrays.stream(sb).map(StringBuilder::toString).toList().toArray(str);
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
                case "deadline":
                    String[] deadlineMessage = parseDeadline(words);
                    addDeadline(deadlineMessage);
                    break;
                case "event":
                    String[] eventMessage = parseEvent(words);
                    addEvent(eventMessage);
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
