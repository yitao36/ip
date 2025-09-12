package tsundere;

import tsundere.storage.AbstractStorage;
import tsundere.task.TaskList;

import java.io.IOException;

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
}
