package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.AbstractUi;
import tsundere.ui.TerminalUi;

/**
 *  The abstract command class that handles the execution of code for {@link TaskList},
 *  {@link AbstractUi}, and {@link TextStorage}
 */
public abstract class AbstractCommand {
    private final boolean isExit;

    AbstractCommand(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Determines if the command should cause the program to close.
     *
     * @return true if program will exit after execution.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Runs the command with the corresponding implementation for handling {@link TaskList},
     * {@link AbstractUi}, and {@link TextStorage}
     *
     * @param tasks   The current list of tasks.
     * @param ui      Displays messages to the user.
     * @param storage Storage for the tasks.
     */
    public abstract void execute(TaskList tasks, AbstractUi ui, TextStorage storage);
}

