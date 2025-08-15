import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

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

class TaskFormatException extends Exception {
    public TaskFormatException() {
        super();
    }
}

class MissingArgumentException extends Exception {
    public MissingArgumentException() {
        super();
    }
}

public class Tsundere {
    public static final int WIDTH = 40;
    public static final String HORIZONTAL_LINE = "_".repeat(WIDTH);
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

    public static String[] parseEvent(String[] words) throws TaskFormatException {
        StringBuilder message = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();

        enum Params {MESSAGE, FROM, TO};
        Params type = Params.MESSAGE;

        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/from")) {
                switch (type) {
                    case MESSAGE -> type = Params.FROM;
                    case FROM -> {
                        from.append(words[i]);
                        from.append(' ');
                    }
                    case TO -> {
                        to.append(words[i]);
                        to.append(' ');
                    }
                }
            } else if (words[i].equals("/to")) {
                switch (type) {
                    case MESSAGE -> type = Params.TO;
                    case FROM -> type = Params.TO;
                    case TO -> {
                        to.append(words[i]);
                        to.append(' ');
                    }
                }
            } else {
                switch (type) {
                    case MESSAGE -> {
                        message.append(words[i]);
                        message.append(' ');
                    }
                    case FROM -> {
                        from.append(words[i]);
                        from.append(' ');
                    }
                    case TO -> {
                        to.append(words[i]);
                        to.append(' ');
                    }
                }
            }
        }
        if (message.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new TaskFormatException();
        }
        message.deleteCharAt(message.length()-1);
        from.deleteCharAt(from.length()-1);
        to.deleteCharAt(to.length()-1);

        String[] str = new String[] {message.toString(), from.toString(), to.toString()};
        return str;
    }

    public static String[] parseDeadline(String[] words) throws TaskFormatException {
        StringBuilder message = new StringBuilder();
        StringBuilder by = new StringBuilder();

        enum Param {MESSAGE, BY};
        Param type = Param.MESSAGE;

        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/by")) {
                switch (type) {
                    case MESSAGE -> type = Param.BY;
                    case BY -> {
                        by.append(words[i]);
                        by.append(' ');
                    }
                }
            } else {
                switch (type) {
                    case MESSAGE -> {
                        message.append(words[i]);
                        message.append(' ');
                    }
                    case BY -> {
                        by.append(words[i]);
                        by.append(' ');
                    }
                }
            }
        }

        if (message.isEmpty() || by.isEmpty()) {
            throw new TaskFormatException();
        }
        message.deleteCharAt(message.length() - 1);
        by.deleteCharAt(by.length() - 1);

        String[] str = new String[] {message.toString(), by.toString()};
        return str;
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
                    try {
                        String message = Arrays.stream(words).skip(2).reduce(words[1],
                                (prev, next) -> prev + " " + next);
                        addTodo(message);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("Incorrect format!");
                        System.out.println("Use `todo <name>`");
                        System.out.println(HORIZONTAL_LINE);
                    }
                    break;
                case "deadline":
                    try {
                        String[] deadlineMessage = parseDeadline(words);
                        addDeadline(deadlineMessage);
                    } catch (TaskFormatException e) {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("Incorrect format!");
                        System.out.println("Use 'deadline <name> /by <date>'");
                        System.out.println(HORIZONTAL_LINE);
                    }
                    break;
                case "event":
                    try {
                        String[] eventMessage = parseEvent(words);
                        addEvent(eventMessage);
                    } catch (TaskFormatException e) {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println("Incorrect format!");
                        System.out.println("Use 'event <name> /from <time> /to <time>'");
                        System.out.println(HORIZONTAL_LINE);
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
                case "delete":
                    String id3 = words.length > 1
                            ? words[1]
                            : null;
                    delete(id3);
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
