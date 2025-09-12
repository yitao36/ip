package tsundere;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import tsundere.command.AbstractCommand;
import tsundere.log.Log;
import tsundere.parser.CommandParser;
import tsundere.storage.TextStorage;
import tsundere.task.TaskList;
import tsundere.ui.GraphicsUi;
import tsundere.ui.UiMessages;

/**
 * The entry point to the tsundere.Tsundere chatbot.
 */
public class Tsundere {
    private final GraphicsUi ui;
    private final TextStorage storage;
    private final TaskList tasks;
    private final Log log;

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
        this.log = new Log(tasks, ui, storage);
    }
    /**
     * Starts the main command loop for tsundere.Tsundere.
     */
    public void run() {
        ui.displayMessage(UiMessages.WELCOME);
        try {
            tasks.addAll(storage.retrieveAll());
        } catch (TsundereException | IOException e) {
            ui.displayMessage(e.getMessage());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(br);

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = sc.nextLine();
            try {
                AbstractCommand command = CommandParser.parse(fullCommand);
                command.execute(tasks, ui, storage, log);
                isExit = command.isExit();
            } catch (TsundereException e) {
                ui.displayMessage(e.getMessage());
            }
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
        } catch (TsundereException | IOException e) {
            ui.displayMessage(e.getMessage());
        }
        ui.setResources(vBox, image);
        ui.displayMessage(UiMessages.WELCOME);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public void displayResponse(String input) {
        try {
            AbstractCommand command = CommandParser.parse(input);
            command.execute(tasks, ui, storage, log);
        } catch (TsundereException e) {
            ui.displayMessage(e.getMessage());
        }
    }
}
