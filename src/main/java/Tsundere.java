

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
    private final static String DEFAULT_STORAGE_PATH = "./src/main/java/tsundere/storage/tsundereStorage.txt";
    private final static String ALT_STORAGE_PATH = "./tsundereStorage.txt";

    private final Ui ui;
    private final TextStorage storage;
    private final TaskList tasks;

    public Tsundere(String path) {
        this.ui = new Ui();
        try {
            this.storage = TextStorage.of();
        } catch (IOException e) {
            System.out.println("Failed to initialize text storage.");
            throw new RuntimeException();
        }
        this.tasks = new TaskList();
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
        Tsundere tsundere = new Tsundere("./src/main/java/tsundere/storage/tsundereStorage.txt");
        tsundere.run();
    }
}
