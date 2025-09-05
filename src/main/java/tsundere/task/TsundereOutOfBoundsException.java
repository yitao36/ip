package tsundere.task;

import tsundere.TsundereException;

/**
 * Thrown if an invalid index is input as an argument to a command.
 */
public class TsundereOutOfBoundsException extends TsundereException {
    public TsundereOutOfBoundsException() {
        super("That task index doesn't exist, dummy!!\n");
    }
}
