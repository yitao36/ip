package tsundere.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static tsundere.Config.TASK_DEADLINE;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;
    public DeadlineTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toStorageString() {
        return TASK_DEADLINE + "," + (super.isDone() ? "T" : "F") + "," + super.getName() + "," + deadline;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (By "
                + deadline.format(DateTimeFormatter.ofPattern("dd-MMM-yy' 'HH:mm")) + ")";
    }
}
