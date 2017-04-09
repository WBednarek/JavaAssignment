package JavaAssignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import javax.swing.*;

/**
 * Created by a on 09.04.2017.
 */
public class InputOutputControl {

    /**
     *  This class handles action events for Loading a file.  The event handler
     *  prompts a window in which the user selects a file. Then the information
     *  contained in the file is outputed in the content tabs.
     */

    /**
     * Object used to make possible operations in matrix input text field.
     * It binds Graphical JavaFX TextArea with that object.
     */
    @FXML
    protected TextArea matrixArea;
    /**
     * Object used to make possible operations in vector input text field.
     * It binds Graphical JavaFX TextArea with that object.
     */
    @FXML
    protected TextArea vectorArea;
    /**
     * Object used to display results in results TextArea.
     * It binds Graphical JavaFX TextArea with that object.
     */
    @FXML
    protected TextArea resultsArea;
/*
    class LoadActionListener  {
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(new );
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                if(chooser.getSelectedFile().getName().indexOf(".") == -1){
                    String[] content = new String[3];
                    //Creating SaveLoad object
                    SaveLoad Save = new SaveLoad();
                    //Getting values from selected file
                    content = Save.readObjects(chooser.getSelectedFile().getName());
                    //Adding content to input and output tabs
                    matrixArea.setText(content[0]);
                    vectorArea.setText(content[1]);
                    resultsArea.setText(content[2]);
                }
                else
                    JOptionPane.showMessageDialog(null, "Please select a file saved through this java application !!! \n Files are saved in the same location as the .jar executable.","Not a correct file", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     *  This class handles action events for saving a file.  The event handler
     *  prompts a window in which the user enters the name of the file to save.
     *  The information contained in the main window tabs are then saved in a
     *  file.
     */


    class SaveActionListener {
        public void actionPerformed(ActionEvent arg0) {
            // Prompt the user to enter their name
            String name = JOptionPane.showInputDialog("Input wanted file name");
            // Object selectedValue = pane.getValue();
            if (name != null) {
                //Alert message if no name entered
                if ("".equals(name)) {
                    JOptionPane.showMessageDialog(null, "Please enter the name of the file", "No name", JOptionPane.ERROR_MESSAGE);
                } else {
                    //Create SaveLoad object
                    SaveLoad Save = new SaveLoad();
                    //Getting values
                    String[] content = new String[3];
                    content[0] = matrixArea.getText();
                    content[1] = vectorArea.getText();
                    content[2] = resultsArea.getText();
                    //Storing values in file
                    Save.writeObjects(name, content);
                }
            }
        }
    }

}

