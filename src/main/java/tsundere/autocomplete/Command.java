package tsundere.autocomplete;

import java.util.Arrays;

/**
 * Displays the format of the autocompleted command for the current user input.
 */
public class Command {
    private static final String[] AUTOCOMPLETE_TEXT = new String[] {
        "todo", "event", "deadline", "list", "find", "mark", "unmark", "undo", "bye"
    };

    /**
     * Autocompletes the given user input and returns the first command that it matches.
     * @param text User input to be autocompleted
     * @return String of the completed command
     */
    public static String autocompleteCommand(String text) {
        try {
            return Arrays.stream(AUTOCOMPLETE_TEXT).filter(c -> c.startsWith(text)).toList().get(0);
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }
}
