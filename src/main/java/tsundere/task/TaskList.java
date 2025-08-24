package tsundere.task;

import java.util.ArrayList;

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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
