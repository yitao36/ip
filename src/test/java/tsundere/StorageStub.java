package tsundere;

import java.io.IOException;

import tsundere.storage.AbstractStorage;
import tsundere.task.TaskList;

/**
 * A stub of Storage, used for testing.
 */
public class StorageStub extends AbstractStorage {
    private TaskList tasks;

    @Override
    public void storeAll(TaskList tasks) throws IOException {
        this.tasks = tasks;
    }

    @Override
    public TaskList retrieveAll() throws TsundereException, IOException {
        return tasks;
    }

    @Override
    public void clear() {
        tasks = new TaskList();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StorageStub stub) {
            return stub.tasks.equals(this.tasks);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
