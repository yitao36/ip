package tsundere.task;

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

    public String toString() {
        return "[" + (done ? "X" : "") + "] " + this.name;
    }
}

