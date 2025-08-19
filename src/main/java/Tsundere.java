

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import tsundere.command.AbstractCommand;
import tsundere.parser.Parser;
import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.Ui;

public class Tsundere {
    private final Ui ui;
    private final TextStorage storage;
    private final TaskList tasks;

    public Tsundere(String path) {
        this.ui = new Ui();
        this.storage = new TextStorage("./src/main/java/tsundere/storage/tsundereStorage.txt");
        this.tasks = new TaskList();
    }

    public void run() {
        ui.showWelcome();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(br);

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = sc.nextLine();
            AbstractCommand command = Parser.parse(fullCommand);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
        }
    }

    public static void main(String[] args) {
        Tsundere tsundere = new Tsundere("./src/main/java/tsundere/storage/tsundereStorage.txt");
        tsundere.run();


    }
}
