import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class SerialisationDemo {

    public static void main(String[] args) throws Exception {


        File outFile  = new File("books_computers.data");

        FileOutputStream outStream = new FileOutputStream(outFile);

        Book book1 = new Book("Jaws",23.33,"8978979854",121);
        Book book2 = new Book("One Flew Over the Cuckoo's Nest",43.21,"3453453435",328);

        Computer comp1 = new Computer("Dell","Laptop",3.25,16,550.99);
        Computer comp2 = new Computer("Apple","Desktop",3.85,8,854.54);

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

        File inFile	= new File("books_computers.data");
        FileInputStream inStream = new FileInputStream(inFile);

        ObjectInputStream objectInStream = new ObjectInputStream(inStream);

        book1 = (Book) objectInStream.readObject();
        book2 = (Book) objectInStream.readObject();
        comp1 = (Computer) objectInStream.readObject();
        comp2 = (Computer) objectInStream.readObject();
        mixtureOfObjects = (ArrayList<Object>) objectInStream.readObject();

        String objectMixture="";

        for(Object o: mixtureOfObjects)
            objectMixture += o + "\n";

        JOptionPane.showMessageDialog(null,"State of standalone objects read from the file are:\n\n" + book1 + "\n" +
                        book2 + "\n" + comp1 + "\n" + comp2 + "\n\n\nThe ones from the array-list are:\n\n" + objectMixture,
                "Output from File",JOptionPane.INFORMATION_MESSAGE);

        inStream.close();
    }
}