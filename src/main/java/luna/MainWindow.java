package luna;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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

    private Luna luna;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image lunaImage = new Image(this.getClass().getResourceAsStream("/images/Luna.png"));

    /**
     * Initializes the main window by binding the scroll pane's vertical value property to the height property of the
     * dialog container and adding a greeting message from Luna.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greet = "Hello! I'm Luna\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getLunaDialog(greet, lunaImage));
    }

    /** Injects the Duke instance */
    public void setLuna(Luna l) {
        luna = l;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = luna.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLunaDialog(response, lunaImage)
        );
        userInput.clear();
    }
}
