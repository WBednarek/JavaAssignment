package JavaAssignment;

import javax.swing.*;
import java.io.*;

/**
 * Created by a on 09.04.2017.
 */
public class SaveLoad {
    // String to hold the filename input on command line (argv[0])
    private static String FILE;

    /**
     * Method to write the results in a given file
     *
     * @param File    stores the file in which to write
     * @param content stores the content to write in file
     */
    public void writeObjects(String File, String[] content) {
        FILE = File;
        try {
            FileOutputStream fos = new FileOutputStream(FILE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(content);
            os.close();
            JOptionPane.showMessageDialog(null, "Your file :" + FILE + " has been succesfully saved.\n Files are saved in the same location as the .jar executable.", "Save Succesfull !", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            System.out.println("Exception while writing");
            System.exit(1);
        }
    }

    /**
     * Method to read a file in which the results are stored
     *
     * @param File stores the file to read
     * @return the content of the file
     */
    public String[] readObjects(String File) {
        FILE = File;
        String[] content = new String[3];
        System.out.println("Test: read objects from FILE");
        try {
            FileInputStream fis = new FileInputStream(FILE);
            ObjectInputStream is = new ObjectInputStream(fis);
            content = (String[]) is.readObject();
            is.close();
        } catch (FileNotFoundException e) {
            System.out.println("File to read objects not found");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Exception while reading data");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException while reading");
            System.exit(1);
        }
        return content;
    }
}
