import javax.swing.*;
import java.io.*;

public class TextFileDemo {

    public static void main(String args[]) {

        JFileChooser fileChooser = new JFileChooser("C:/users/documents");
        int status = fileChooser.showOpenDialog(null);

        File selectedFile = null;

        if(status == JFileChooser.APPROVE_OPTION)
            selectedFile = fileChooser.getSelectedFile();

        try{
            if(selectedFile !=null) {
                FileReader in = new FileReader(selectedFile);

                FileWriter out = new FileWriter("output.txt");

                int c;

                while ((c = in.read()) != -1)
                    out.write(c);

                JOptionPane.showMessageDialog(null, "Data now copied from " + selectedFile.getName() + " to " +
                        "output.txt", "File Copied!", JOptionPane.INFORMATION_MESSAGE);

                in.close();
                out.close();
            }
            else
                JOptionPane.showMessageDialog(null, "No file was selected!",
                        "No File Selected!", JOptionPane.ERROR_MESSAGE);

        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getStackTrace());
            JOptionPane.showMessageDialog(null, "File could not be found!",
                    "Problem Finding File!", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ioe) {
            System.out.println(ioe.getStackTrace());
            JOptionPane.showMessageDialog(null, "File could not be read/written!",
                    "Problem Reading/Writing to File!", JOptionPane.ERROR_MESSAGE);
        }
    }
}