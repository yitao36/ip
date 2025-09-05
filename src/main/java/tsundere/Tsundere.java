package tsundere;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import tsundere.command.AbstractCommand;
import tsundere.parser.CommandParser;
import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.GraphicsUi;

/**
 * The entry point to the tsundere.Tsundere chatbot.
 */
public class Tsundere {
    private final GraphicsUi ui;
    private final TextStorage storage;
    private final TaskList tasks;

    /**
     * Initializes a new tsundere.Tsundere chatbot with a text file storage.
     *
     * @throws RuntimeException If none of the storage location can be used for text storage.
     */
    public Tsundere() {
        this.tasks = new TaskList();
        this.ui = new GraphicsUi();
        try {
            this.storage = TextStorage.of();
        } catch (IOException e) {
            System.out.println("Failed to initialize text storage.");
            throw new RuntimeException();
        }
    }
    /**
     * Starts the main command loop for tsundere.Tsundere.
     */
    public void run() {
        ui.showWelcome();
        try {
            tasks.addAll(storage.retrieveAll());
        } catch (TsundereException e) {
            ui.echo(e.getMessage());
        }

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

    /**
     * Initializes GraphicsUi with a welcome message.
     * @param vBox DialogContainer to display output
     * @param image Profile picture of Tsundere
     */
    public void setGraphicsUi(VBox vBox, Image image) {
        try {
            tasks.addAll(storage.retrieveAll());
        } catch (TsundereException e) {
            ui.echo(e.getMessage());
        }
        ui.setResources(vBox, image);
        ui.showWelcome();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public void displayResponse(String input) {
        AbstractCommand command = CommandParser.parse(input);
        command.execute(tasks, ui, storage);
        boolean isExit = command.isExit();
    }
}
