package tsundere.task;

import java.util.Objects;

public abstract class Task {
    private final String name;
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    public abstract String toStorageString();

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "[" + (done ? "X" : "") + "] " + this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task task) {
            return this.name.equals(task.name) && this.done == task.done;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.done);
    }
}

