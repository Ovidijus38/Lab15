import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

class SerialisationDemoHandlingExceptions {

    public static void main(String[] args) {

        File outFile = new File("books_computers.data");

        try {
            FileOutputStream outStream = new FileOutputStream(outFile);

            Book book1 = new Book("Jaws", 23.33, "8978979854", 121);
            Book book2 = new Book("One Flew Over the Cuckoo's Nest", 43.21, "3453453435", 328);

            Computer comp1 = new Computer("Dell", "Laptop", 3.25, 16, 550.99);
            Computer comp2 = new Computer("Apple", "Desktop", 3.85, 8, 854.54);

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);

            objectOutStream.writeObject(book1);
            objectOutStream.writeObject(book2);
            objectOutStream.writeObject(comp1);
            objectOutStream.writeObject(comp2);

            ArrayList<Object> mixtureOfObjects = new ArrayList<>();

            mixtureOfObjects.add("Student");
            mixtureOfObjects.add(comp1);
            mixtureOfObjects.add(3546);
            mixtureOfObjects.add(102.56);
            mixtureOfObjects.add(book2);
            mixtureOfObjects.add('k');

            objectOutStream.writeObject(mixtureOfObjects);

            outStream.close();
        }
        catch(FileNotFoundException fnfe){
            System.out.println(fnfe.getStackTrace());
            JOptionPane.showMessageDialog(null,"File could not be found!",
                    "Problem Finding File!",JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            System.out.println(ioe.getStackTrace());
            JOptionPane.showMessageDialog(null,"File could not be written!",
                    "Problem Writing to File!",JOptionPane.ERROR_MESSAGE);
        }


        File inFile	= new File("books_computers.data");

        try {
            FileInputStream inStream = new FileInputStream(inFile);

            ObjectInputStream objectInStream = new ObjectInputStream(inStream);


            Book book1 = (Book) objectInStream.readObject();
            Book book2 = (Book) objectInStream.readObject();
            Computer comp1 = (Computer) objectInStream.readObject();
            Computer comp2 = (Computer) objectInStream.readObject();
            ArrayList<Object> mixtureOfObjects = (ArrayList<Object>) objectInStream.readObject();

            String objectMixture = "";

            for (Object o : mixtureOfObjects)
                objectMixture += o + "\n";

            JOptionPane.showMessageDialog(null, "State of standalone objects read from the file are:\n\n" + book1 + "\n" +
                            book2 + "\n" + comp1 + "\n" + comp2 + "\n\n\nThe ones from the array-list are:\n\n" + objectMixture,
                    "Output from File", JOptionPane.INFORMATION_MESSAGE);

            inStream.close();
        }
        catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
            JOptionPane.showMessageDialog(null,"File could not be found!",
                    "Problem Finding File!",JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
            JOptionPane.showMessageDialog(null,"File could not be read!",
                    "Problem Reading From File!",JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
            JOptionPane.showMessageDialog(null,"Could not find the appropriate class!",
                    "Problem Finding the Class!",JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassCastException cce) {
            cce.printStackTrace();
            JOptionPane.showMessageDialog(null,"Could not convert the object to the appropriate class!",
                    "Problem Converting Object!",JOptionPane.ERROR_MESSAGE);
        }

    }
}