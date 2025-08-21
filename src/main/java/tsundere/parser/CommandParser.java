package tsundere.parser;

import tsundere.command.*;
import static tsundere.command.InvalidFormatCommand.Format;
import tsundere.task.DeadlineTask;
import tsundere.task.EventTask;
import tsundere.task.TodoTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class CommandParser {
    public static AbstractCommand parse(String fullCommand) {
        String[] args = fullCommand.split(" ");

        if (args.length == 0 || args[0].isBlank() || args[0].equals("help")) {
            return new InvalidFormatCommand(Format.HELP);
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
                        return new InvalidFormatCommand(Format.MARK);
                    }
                    return new MarkCommand_(Integer.parseInt(args[1]) - 1);
                } catch (NumberFormatException e) {
                    return new InvalidFormatCommand(Format.MARK);
                }
            case "unmark":
                try {
                    if (args.length < 2) {
                        return new InvalidFormatCommand(Format.UNMARK);
                    }
                    return new UnmarkCommand(Integer.parseInt(args[1]) - 1);
                } catch (NumberFormatException e) {
                    return new InvalidFormatCommand(Format.UNMARK);
                }
            case "delete":
                try {
                    if (args.length < 2) {
                        return new InvalidFormatCommand(Format.DELETE);
                    }
                    return new DeleteCommand(Integer.parseInt(args[1]) - 1);
                } catch (NumberFormatException e) {
                    return new InvalidFormatCommand(Format.DELETE);
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
            return new InvalidFormatCommand(Format.TODO);
        }

        String name = Arrays.stream(args).skip(2).reduce(args[1],
                (prev, next) -> prev + " " + next);

        return new AddTaskCommand(new TodoTask(name));
    }

    public static AbstractCommand parseEvent(String[] words) {
        if (words.length < 6) {
            return new InvalidFormatCommand(Format.EVENT);
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
        if (name.isEmpty() || from.toString().split(" ").length != 1
                || to.toString().split(" ").length != 1) {
            return new InvalidFormatCommand(InvalidFormatCommand.Format.EVENT);
        }

        name.deleteCharAt(name.length()-1);
        from.deleteCharAt(from.length()-1);
        to.deleteCharAt(to.length()-1);

        LocalDateTime fromDate;
        LocalDateTime toDate;
        try {
            fromDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            toDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
            if (!fromDate.isBefore(toDate)) {
                return new InvalidFormatCommand(Format.EVENT_DATE);
            }
        } catch (DateTimeParseException e) {
            return new InvalidFormatCommand(Format.EVENT);
        }

        return new AddTaskCommand(new EventTask(name.toString(), fromDate, toDate));
    }

    public static AbstractCommand parseDeadline(String[] words) {
        if (words.length < 4) {
            return new InvalidFormatCommand(Format.DEADLINE);
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

        if (name.isEmpty() || by.toString().split(" ").length != 1) {
            return new InvalidFormatCommand(Format.DEADLINE);
        }

        name.deleteCharAt(name.length() - 1);
        by.deleteCharAt(by.length() - 1);

        LocalDateTime byDate;
        try {
            byDate = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        } catch (DateTimeParseException e) {
            return new InvalidFormatCommand(Format.DEADLINE);
        }


        return new AddTaskCommand(new DeadlineTask(name.toString(), byDate));
    }
}
