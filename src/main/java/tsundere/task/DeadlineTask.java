package tsundere.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static tsundere.Config.TASK_DEADLINE;

/**
 * A task that contains a date specifying the task's deadline.
 *
 * @see tsundere.task.Task
 */
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeadlineTask deadlineTask) {
            return this.deadline.equals(deadlineTask.deadline)
                    && super.equals(deadlineTask);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.deadline);
    }
}
