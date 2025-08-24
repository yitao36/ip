import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import tsundere.command.AbstractCommand;
import tsundere.parser.CommandParser;
import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

public class Tsundere {
    private final Ui ui;
    private final TextStorage storage;
    private final TaskList tasks;

<<<<<<< HEAD
    /**
     * Initializes a new Tsundere chatbot with a text file storage.
     *
     * @throws RuntimeException If none of the storage location can be used for text storage.
     */
    public Tsundere() {
        this.tasks = new TaskList();
=======
    public Tsundere() {
>>>>>>> branch-Level-9
        this.ui = new Ui();
        try {
            this.storage = TextStorage.of();
        } catch (IOException e) {
            System.out.println("Failed to initialize text storage.");
            throw new RuntimeException();
        }
    }

    public void run() {
        ui.showWelcome();
        tasks.addAll(storage.retrieveAll());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(br);

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = sc.nextLine();
            AbstractCommand command = CommandParser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }

    public static void main(String[] args) {
        Tsundere tsundere = new Tsundere();
        tsundere.run();
    }
}
