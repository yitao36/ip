package tsundere.command;

import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

public class EchoCommand extends AbstractCommand {
    private final String text;
    public EchoCommand(String text) {
        super(false);
        this.text = text;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, TextStorage storage) {
        ui.echo(text);
    }
}
