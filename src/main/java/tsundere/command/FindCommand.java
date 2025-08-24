package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

/**
 * Handles the execution of displaying a list of tasks to the user with the specified search parameter.
 */
public class FindCommand extends AbstractCommand {
    private final String name;

    public FindCommand(String name) {
        super(false);
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        TaskList filteredList = tasks.find(name);
        ui.listTasks(filteredList);
    }
}
