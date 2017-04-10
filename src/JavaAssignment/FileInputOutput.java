package JavaAssignment;


import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Allows to save the result of a successful computation to persistent storage
 * and allow the user to load a previously saved computation.
 */
public class FileInputOutput {

    /**
     * Load results to results TextArea from external file.
     *
     * @param stage Main application container.
     * @return Read content of external file which is stored to {@link String} object.
     */
    public static String loadResultFromFile(final Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open results file");
        //Opened file
        File file = fileChooser.showOpenDialog(stage);
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader(file));
            String text = "";
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text).append("\n");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileInputOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileInputOutput.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(FileInputOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return stringBuffer.toString();
    }

    /**
     * Save results from results TextArea to external file with any extension.
     *
     * @param dataToSave Data displayed in results TextArea.
     * @param stage      Main application container.
     */
    public static void saveResults(String dataToSave, final Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        //Opened file
        File file = fileChooser.showSaveDialog(stage);
        {
            try {
                FileWriter fileWriter = null;
                fileWriter = new FileWriter(file);
                fileWriter.write(dataToSave);
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(FileInputOutput.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}











