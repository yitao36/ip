package tsundere.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import tsundere.DialogBox;
import tsundere.command.InvalidFormatCommand;
import tsundere.task.Task;
import tsundere.task.TaskList;

/**
 * Ui class handling displaying of responses to the user's GUI.
 */
public class GraphicsUi extends AbstractUi {
    private VBox dialogContainer;
    private Image tsundereImage;

    /**
     * Sets the DialogContainer where responses should be added to.
     */
    public GraphicsUi() {
    }
    public void setResources(VBox vBox, Image image) {
        dialogContainer = vBox;
        tsundereImage = image;
    }
    @Override
    public void showWelcome() {
        String response = UiMessages.getMessage(UiMessages.WELCOME);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, tsundereImage));
    }

    @Override
    public void showExit() {
        String response = UiMessages.getMessage(UiMessages.EXIT);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, tsundereImage));
    }

    @Override
    public void listTasks(TaskList tasks) {
        String response = UiMessages.getMessage(UiMessages.LIST_TASKS, tasks);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, tsundereImage));
    }

    @Override
    public void addTaskSuccess(Task task) {
        String response = UiMessages.getMessage(UiMessages.ADD_TASK, task);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, tsundereImage));
    }

    @Override
    public void markSuccess(Task task) {
        String response = UiMessages.getMessage(UiMessages.MARK_TASK_SUCCESS, task);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, tsundereImage));
    }

    @Override
    public void markRedundant(Task task) {
        String response = UiMessages.getMessage(UiMessages.MARK_TASK_REDUNDANT, task);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, tsundereImage));
    }

    @Override
    public void unmarkSuccess(Task task) {
        String response = UiMessages.getMessage(UiMessages.UNMARK_TASK_SUCCESS, task);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, tsundereImage));
    }

    @Override
    public void unmarkRedundant(Task task) {
        String response = UiMessages.getMessage(UiMessages.UNMARK_TASK_REDUNDANT, task);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, tsundereImage));
    }

    @Override
    public void deleteSuccess(Task task) {
        String response = UiMessages.getMessage(UiMessages.DELETE_SUCCESS, task);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, tsundereImage));
    }

    @Override
    public void showHelp(InvalidFormatCommand.Format format) {
        String response = UiMessages.getMessage(UiMessages.HELP, format);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, tsundereImage));
    }

    @Override
    public void echo(String s) {
        String response = UiMessages.getMessage(UiMessages.ECHO, s);
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(response, tsundereImage));
    }
}
