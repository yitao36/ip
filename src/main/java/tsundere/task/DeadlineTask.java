package tsundere.task;

import static tsundere.Config.TASK_DEADLINE;

public class DeadlineTask extends Task {
    private final String deadline;
    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toStorageString() {
        return TASK_DEADLINE + "," + (super.isDone() ? "T" : "F") + "," + super.getName() + "," + deadline;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + deadline + ")";
    }
}
