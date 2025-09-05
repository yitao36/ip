package tsundere.task;

import tsundere.storage.TsundereOutOfBoundsException;

import java.util.ArrayList;

/**
 * A list of tasks utilizing the ArrayList implementation.
 *
 * @see java.util.ArrayList
 */
public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    /**
     * Filters the task list for tasks which contains the specified name as a substring in the task's name.
     *
     * @return A new {@link TaskList} of the filtered tasks.
     */
    public TaskList find(String name) {
        TaskList filteredList = new TaskList();
        filteredList.addAll(super.stream().filter(task -> task.getName().contains(name)).toList());
        return filteredList;
    }

    /**
     * Checks if the user input index is within the correct range.
     * @param i The index of the task in the task list.
     */
    public void validateIndex(int i) throws TsundereOutOfBoundsException {
        if (i < 0 || i >= super.size()) {
            throw new TsundereOutOfBoundsException();
        }
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
