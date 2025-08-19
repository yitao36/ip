package tsundere.task;

import static tsundere.Config.TASK_TODO;

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
}
