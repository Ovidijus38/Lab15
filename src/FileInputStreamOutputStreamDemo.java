import javax.swing.*;
import java.io.*;

public class FileInputStreamOutputStreamDemo {

    public static void main(String[] args) throws Exception{

        File outFile  = new File("demofile.data");

        FileOutputStream outStream = new FileOutputStream(outFile);

        byte[] byteArray = {10, 20, 30, 40, 50, 60, 70, 80};

        outStream.write(byteArray);

        outStream.close();

        File inFile	= new File("demofile.data");
        FileInputStream inStream = new FileInputStream(inFile);

        int fileSize  = (int)inFile.length();
        byteArray = new byte[fileSize];

        inStream.read(byteArray);

        String valuesRead="";

        for(int i = 0; i < fileSize; i++) {
            valuesRead+=byteArray[i] + "  ";
        }

        JOptionPane.showMessageDialog(null,"Values read from the file are: " + valuesRead,
                "Output from File",JOptionPane.INFORMATION_MESSAGE);

        inStream.close();
    }
}
