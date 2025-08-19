

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

import tsundere.TaskFormatException;

import static tsundere.Commands.*;
import static tsundere.Config.*;


public class Tsundere {
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

        return new String[] {message.toString(), from.toString(), to.toString()};
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

        return new String[] {message.toString(), by.toString()};
    }

    public static String readFromStorage() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("tsundere/storage/tsundereStorage.txt"));
        StringBuilder sb = new StringBuilder();

        while (sc.hasNext()) {
            sb.append(sc.nextLine());
        }

        return sb.toString();
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
                case "noInput":
                default:
                    System.out.println(line);
            }
        }

        exit();
    }
}
