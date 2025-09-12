package tsundere.task;

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
<<<<<<< Updated upstream
        TaskList filteredList = new TaskList();
        filteredList.addAll(super.stream().filter(task -> task.getName().contains(name)).toList());
        return filteredList;
=======
        ArrayList<Task> filteredList = new ArrayList<>(tasks.stream().filter(
                task -> task.getName().contains(name)).toList());
        return new TaskList(filteredList);
    }

    /**
     * Checks if the user input index is within the correct range.
     *
     * @param i The index of the task in the task list.
     */
    private void validateIndex(int i) throws TsundereOutOfBoundsException {
        if (i < 0 || i >= tasks.size()) {
            throw new TsundereOutOfBoundsException();
        }
    }

    /**
     * Marks the task at the given index.
     *
     * @param id The index of the task in the task list
     */
    public void mark(int id) throws TsundereException {
        validateIndex(id);
        tasks.get(id).markDone();
    }

    /**
     * Unmarks the task at the given index.
     *
     * @param id The index of the task in the task list
     * @throws TsundereException If the given index is invalid
     */
    public void unmark(int id) throws TsundereException {
        validateIndex(id);
        tasks.get(id).markUndone();
    }

    /**
     * Adds a task to the task list.
     * @param task Task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Adds all the tasks to the task list.
     * @param tasks List of tasks to be added
     */
    public void addAll(TaskList tasks) {
        for (Task task : tasks) {
            this.tasks.add(task);
        }
    }

    /**
     * Retrieves the task from the task list at the given index and return it
     *
     * @param id The index of the task to be retrieved from the task list
     * @return The retrieved task
     * @throws TsundereOutOfBoundsException If the given index is invalid
     */
    public Task get(int id) throws TsundereOutOfBoundsException {
        validateIndex(id);
        return tasks.get(id);
    }

    /**
     * Removes the task from the task list at the given index and return it
     *
     * @param id The index of the task to be removed from the task list
     * @return The removed task
     * @throws TsundereOutOfBoundsException If the given index is invalid
     */
    public Task remove(int id) throws TsundereOutOfBoundsException {
        validateIndex(id);
        return tasks.remove(id);
    }

    /**
     * Checks if the task list is empty.
     * @return true if task list is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
>>>>>>> Stashed changes
    }

    /**
     * Returns the number of tasks in the task list.
     * @return Size of task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Undoes an {@link tsundere.command.AddTaskCommand} by deleting the task at the end of the task list.
     */
    public void undoAdd() {
        tasks.remove(tasks.size() - 1);
    }

    /**
     * Undoes an {@link tsundere.command.DeleteCommand} by adding the task back into its original index.
     * @param task Task to be added into the list
     * @param index Index that the task should in after the undo
     */
    public void undoDelete(Task task, int index) {
        List<Task> temp = new ArrayList<>(tasks.subList(0, index));
        temp.add(task);
        temp.addAll(tasks.subList(index, tasks.size()));
        tasks = temp;
    }

    /**
     * Undoes a {@link tsundere.command.MarkCommand} by unmarking the task at the index.
     * @param index Index of the task to be unmarked
     */
    public void undoMark(int index) {
        try {
            tasks.get(index).markUndone();
        } catch (AlreadyUnmarkedException e) {
            assert false : "undoMark error";
        }
    }

    /**
     * Undoes a {@link tsundere.command.UnmarkCommand} by marking the task at the index.
     * @param index Index of the task to be marked
     */
    public void undoUnmark(int index) {
        try {
            tasks.get(index).markDone();
        } catch (AlreadyMarkedException e) {
            assert false : "undoUnmark error";
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
