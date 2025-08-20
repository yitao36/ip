package tsundere.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventTask eventTask) {
            return this.from.equals(eventTask.from)
                    && this.to.equals(eventTask.to)
                    && super.equals(eventTask);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.from, this.to);
    }
}
