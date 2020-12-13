import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class BicycleShopAppWithExceptionHandling extends JFrame implements ActionListener {

    private JMenu fileMenu, bikeMenu;
    private JMenuItem item=null;
    private final File file = new File("D:\\OOP_Java\\Sem3Lab15\\Lab15\\src\\bicycles.data");

    public BicycleShopAppWithExceptionHandling( ) {
        super("Bike Shop System");

        Container pane = getContentPane();
        pane.setBackground(new Color(200,200,240));

        createFileMenu();
        createBikeMenu();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(bikeMenu);

        ImageIcon icon = new ImageIcon("D:\\OOP_Java\\Sem3Lab15\\Lab15\\src\\bike.png");
        setIconImage(icon.getImage());

        setSize(400,400 );
        setVisible(true);
    }

    public static void main( String[] args ) {
        BicycleShopAppWithExceptionHandling frame = new BicycleShopAppWithExceptionHandling();
    }


    private void createFileMenu(){

        fileMenu = new JMenu("File");

        String[] itemNames = {"New","Open","Save","Quit"};

        for(int i=0;i<itemNames.length;i++){
            item = new JMenuItem(itemNames[i]);
            item.addActionListener(this);

            if(i==3)
                fileMenu.addSeparator();

            fileMenu.add(item);
        }
    }


    private void createBikeMenu(){

        bikeMenu = new JMenu("Bicycle");

        String[] itemNames = {"Add","Amend","Remove","View"};

        for (String itemName : itemNames) {

            item = new JMenuItem(itemName);
            item.addActionListener(this);
            bikeMenu.add(item);
        }
    }

    public void actionPerformed(ActionEvent e) {

        int choice;

        if(e.getActionCommand().equals("New")) {
            createFile();
        }
        else if(e.getActionCommand().equals("Open")) {
            openFile();
        }
        else if(e.getActionCommand().equals("Save")) {
            saveFile();

        }
        else if(e.getActionCommand().equals("Quit")) {
            choice = JOptionPane.showConfirmDialog(null, "Are you sure you wish to quit the application?",
                    "Exiting Application", JOptionPane.YES_NO_CANCEL_OPTION);

            if(choice==JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null,"Saving the file that stores bicycle details before terminating",
                        "Saving File",JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }

        }
        else if(e.getActionCommand().equals("Add"))
            JOptionPane.showMessageDialog(null,"Adding new bicycle details",
                    "Adding Bike",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("Amend"))
            JOptionPane.showMessageDialog(null,"Amending bicycle details",
                    "Amending Bike",JOptionPane.INFORMATION_MESSAGE);
        else if(e.getActionCommand().equals("Remove"))
            JOptionPane.showMessageDialog(null,"Removing bicycle details",
                    "Removing Bike",JOptionPane.INFORMATION_MESSAGE);
        else
            JOptionPane.showMessageDialog(null,"Viewing bicycle details",
                    "Viewing Bike",JOptionPane.INFORMATION_MESSAGE);
    }

    public void createFile() {

        if(!file.exists())                         //if the file doesn't already exist, create it
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                JOptionPane.showMessageDialog(null, "Created a new file to store bicycle details!",
                        "New File", JOptionPane.INFORMATION_MESSAGE);
                fileOutputStream.close();
            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
                JOptionPane.showMessageDialog(null, "File could not be found!",
                        "Problem Finding File!", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null, "File could not be opened!",
                        "Problem Opening the File!", JOptionPane.ERROR_MESSAGE);
            }

        else
            JOptionPane.showMessageDialog(null, "File to store bicycle details already exists!",
                    "New File", JOptionPane.WARNING_MESSAGE);
    }

    public void openFile() {

        ObjectInputStream objectInputStream=null;

        if(!file.exists())
            createFile();
        else {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);

                if(file.length()!= 0){
                    objectInputStream = new ObjectInputStream(fileInputStream);
                    JOptionPane.showMessageDialog(null, "Opened the file that stores bicycle details",
                            "Opened File", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null, "Can't read file as it is empty!",
                            "Empty File", JOptionPane.ERROR_MESSAGE);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null, "File could not be opened!",
                        "Problem Opening the File!", JOptionPane.ERROR_MESSAGE);
            }

            if(objectInputStream==null)
                return;

            try{
                ArrayList<Bicycle> allBikes = null;

                allBikes = (ArrayList<Bicycle>) objectInputStream.readObject();

                System.out.println(allBikes);

                String bikeData="";

                if(allBikes!=null)
                    for(Bicycle bike: allBikes)
                        bikeData+=bike + "\n";

                objectInputStream.close();

                JOptionPane.showMessageDialog(null, "Details of bicycles read from file are:\n\n" + bikeData,
                        "Opened File", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null, "File could not be read!",
                        "Problem Reading From File!", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
                JOptionPane.showMessageDialog(null, "Could not find the appropriate class!",
                        "Problem Finding the Class!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public void saveFile() {
        if (!file.exists())
            createFile();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            Bicycle bike1 = new Bicycle("Joe", 2500, "Honda");
            Bicycle bike2 = new Bicycle("Mike", 3500, "Harley");
            ArrayList<Bicycle> allBikes = new ArrayList<>();
            allBikes.add(bike1);
            allBikes.add(bike2);
            objectOutputStream.writeObject(allBikes);
            objectOutputStream.close();
            JOptionPane.showMessageDialog(null, "Saved the file that stores bicycle details",
                    "Saved File", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getStackTrace());
            JOptionPane.showMessageDialog(null, "File could not be found!",
                    "Problem Finding File!", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ioe) {
            System.out.println(ioe.getStackTrace());
            JOptionPane.showMessageDialog(null, "File could not be written!",
                    "Problem Writing to File!", JOptionPane.ERROR_MESSAGE);
        }
    }
}