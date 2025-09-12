package tsundere;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Tsundere tsundere;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Sticker1.png"));
    private Image tsundereImage = new Image(this.getClass().getResourceAsStream("/images/tohsaka.jpg"));

    /**
     * Binds scrollbar to the dialog container, and sets up Tsundere Graphics UI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        this.setBackground(new Background(new BackgroundImage(new Image(
                this.getClass().getResourceAsStream("/images/tsundere.jpg")),
                null, null, null, null)));
    }
    /** Injects the Duke instance */
    public void setTsundere(Tsundere t) {
        tsundere = t;
        tsundere.setGraphicsUi(dialogContainer, tsundereImage);
    }
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage));
        tsundere.displayResponse(input);
        userInput.clear();
    }
}
