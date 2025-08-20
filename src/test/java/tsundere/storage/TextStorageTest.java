package tsundere.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import tsundere.task.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TextStorageTest {
    private final TextStorage storage;

    /**
     * Initializes a new text storage in the specified test folder.
     * Clears its contents between each test.
     */
    public TextStorageTest() {
        this.storage = new TextStorage("./src/test/java/tsundere/storage/tsundereStorage.txt");
        storage.clear();
    }

    @Test
    public void storeAndRetrieveAll_multipleTasks_correctFormat() {
        TaskList tasks = new TaskList();
        TodoTask todo1 = new TodoTask("task 1");
        TodoTask todo2 = new TodoTask("task 2");
        DeadlineTask deadline1 = new DeadlineTask("task 3", LocalDateTime.parse("2025-05-25T23:59"));
        DeadlineTask deadline2 = new DeadlineTask("task 4", LocalDateTime.parse("2025-05-26T23:59"));
        EventTask event1 = new EventTask("task 5",
                LocalDateTime.parse("2025-05-25T23:59"), LocalDateTime.parse("2025-05-26T23:59"));
        EventTask event2 = new EventTask("task 6",
                LocalDateTime.parse("2025-05-25T23:59"), LocalDateTime.parse("2025-05-26T23:59"));

        todo2.markDone();
        deadline2.markDone();
        event2.markDone();

        List<Task> list = Arrays.stream(new Task[] {todo1, todo2, deadline1, deadline2, event1, event2}).toList();
        list.forEach(storage::store);
        tasks.addAll(list);

        TaskList outputTasks = storage.retrieveAll();
        assertEquals(tasks, outputTasks, "able to store and retrieve multiple items correctly");
    }

    @Test
    public void mark_multipleTasks_correctFormat() {
        TaskList tasks = new TaskList();
        TodoTask todo1 = new TodoTask("task 1");
        TodoTask todo2 = new TodoTask("task 2");
        DeadlineTask deadline1 = new DeadlineTask("task 3", LocalDateTime.parse("2025-05-25T23:59"));
        DeadlineTask deadline2 = new DeadlineTask("task 4", LocalDateTime.parse("2025-05-26T23:59"));
        EventTask event1 = new EventTask("task 5",
                LocalDateTime.parse("2025-05-25T23:59"), LocalDateTime.parse("2025-05-26T23:59"));
        EventTask event2 = new EventTask("task 6",
                LocalDateTime.parse("2025-05-25T23:59"), LocalDateTime.parse("2025-05-26T23:59"));

        List<Task> list = Arrays.stream(new Task[] {todo1, todo2, deadline1, deadline2, event1, event2}).toList();
        list.forEach(storage::store);
        tasks.addAll(list);

        try {
            todo2.markDone();
            deadline2.markDone();
            event2.markDone();
            storage.mark(1);
            storage.mark(3);
            storage.mark(5);
        } catch (AlreadyMarkedException | StorageFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        TaskList outputTasks = storage.retrieveAll();
        assertEquals(tasks, outputTasks);
    }
}
