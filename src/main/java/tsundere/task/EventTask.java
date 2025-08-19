package tsundere.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static tsundere.Config.*;

public class EventTask extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventTask(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toStorageString() {
        return TASK_EVENT + "," + (super.isDone() ? "T" : "F") + "," + super.getName()
                + "," + from + "," + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (From "
                + from.format(DateTimeFormatter.ofPattern("dd-MMM-yy' 'HH:mm")) + " To "
                + to.format(DateTimeFormatter.ofPattern("dd-MMM-yy' 'HH:mm")) + ")";
    }
}
