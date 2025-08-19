package tsundere.task;

import static tsundere.Config.*;

public class EventTask extends Task {
    private final String from;
    private final String to;

    public EventTask(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorageString() {
        return TASK_EVENT + "," + (super.isDone() ? "T" : "F") + "," + super.getName() + "," + from + "," + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + from + " to " + to + ")";
    }
}
