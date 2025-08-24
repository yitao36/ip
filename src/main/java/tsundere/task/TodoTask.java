package tsundere.task;

import java.util.Objects;

import static tsundere.Config.TASK_TODO;

/**
 * A task that can be marked as completed.
 *
 * @see tsundere.task.Task
 */
public class TodoTask extends Task {
    public TodoTask (String name) {
        super(name);
    }

    @Override
    public String toStorageString() {
        return TASK_TODO + "," + (super.isDone() ? "T" : "F") + "," + super.getName();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TodoTask todoTask) {
            return super.equals(todoTask);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
