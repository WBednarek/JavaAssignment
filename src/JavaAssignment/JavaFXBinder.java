package JavaAssignment;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Created by Wiktor Bednarek on 2017-04-05.
 */
public class JavaFXBinder {


    /**
     * Object used to make possible operations in matrix input text field.
     * It binds Graphical JavaFX TextArea with that object.
     */
    @FXML
    private TextArea matrixArea;

    /**
     * Object used to make possible operations in vector input text field.
     * It binds Graphical JavaFX TextArea with that object.
     */
    @FXML
    private TextArea vectorArea;


    /**
     * Object used to display results in results TextArea.
     * It binds Graphical JavaFX TextArea with that object.
     */
    @FXML
    private TextArea resultsArea;


}
