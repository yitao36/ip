package tsundere.parser;

import tsundere.command.*;
import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.TodoTask;

import java.util.Arrays;

public class Parser {
    public static AbstractCommand parse(String fullCommand) {
        String[] args = fullCommand.split(" ");

        if (args.length == 0 || args[0].isBlank() || args[0].equals("help")) {
            return new InvalidFormatCommand("help");
        }

        String command = args[0];

        switch (command) {
            case "todo":
                return parseTodo(args);
            case "deadline":
                return parseDeadline(args);
            case "event":
                return parseEvent(args);
            case "list":
                return new ListCommand();
            case "mark":
                try {
                    if (args.length < 2) {
                        return new InvalidFormatCommand("mark");
                    }
                    return new MarkCommand_(Integer.parseInt(args[1]));
                } catch (NumberFormatException e) {
                    return new InvalidFormatCommand("mark");
                }
            case "unmark":
                try {
                    if (args.length < 2) {
                        return new InvalidFormatCommand("unmark");
                    }
                    return new UnmarkCommand(Integer.parseInt(args[1]) - 1);
                } catch (NumberFormatException e) {
                    return new InvalidFormatCommand("unmark");
                }
            case "delete":
                try {
                    if (args.length < 2) {
                        return new InvalidFormatCommand("delete");
                    }
                    return new DeleteCommand(Integer.parseInt(args[1]) - 1);
                } catch (NumberFormatException e) {
                    return new InvalidFormatCommand("delete");
                }
            case "bye":
                return new ByeCommand();
            case "noInput":
            default:
                return new EchoCommand(fullCommand);
        }
    }

    public static AbstractCommand parseTodo(String[] args) {
        if (args.length < 2) {
            return new InvalidFormatCommand("todo");
        }

        String name = Arrays.stream(args).skip(2).reduce(args[1],
                (prev, next) -> prev + " " + next);

        return new AddTaskCommand(new TodoTask(name));
    }

    public static AbstractCommand parseEvent(String[] words) {
        if (words.length < 6) {
            return new InvalidFormatCommand("event");
        }
        StringBuilder name = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();

        enum Params {NAME, FROM, TO};
        Params type = Params.NAME;

        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/from")) {
                switch (type) {
                    case NAME -> type = Params.FROM;
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
                    case NAME, FROM -> type = Params.TO;
                    case TO -> {
                        to.append(words[i]);
                        to.append(' ');
                    }
                }
            } else {
                switch (type) {
                    case NAME -> {
                        name.append(words[i]);
                        name.append(' ');
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
        if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
            return new InvalidFormatCommand("event");
        }
        name.deleteCharAt(name.length()-1);
        from.deleteCharAt(from.length()-1);
        to.deleteCharAt(to.length()-1);

        return new AddTaskCommand(new EventTask(name.toString(), from.toString(), to.toString()));
    }

    public static AbstractCommand parseDeadline(String[] words) {
        if (words.length < 4) {
            return new InvalidFormatCommand("deadline");
        }
        StringBuilder name = new StringBuilder();
        StringBuilder by = new StringBuilder();

        enum Param {NAME, BY};
        Param type = Param.NAME;

        for (int i = 1; i < words.length; i++) {
            if (words[i].equals("/by")) {
                switch (type) {
                    case NAME -> type = Param.BY;
                    case BY -> {
                        by.append(words[i]);
                        by.append(' ');
                    }
                }
            } else {
                switch (type) {
                    case NAME -> {
                        name.append(words[i]);
                        name.append(' ');
                    }
                    case BY -> {
                        by.append(words[i]);
                        by.append(' ');
                    }
                }
            }
        }

        if (name.isEmpty() || by.isEmpty()) {
            return new InvalidFormatCommand("deadline");
        }
        name.deleteCharAt(name.length() - 1);
        by.deleteCharAt(by.length() - 1);

        return new AddTaskCommand(new DeadlineTask(name.toString(), by.toString()));
    }
}
