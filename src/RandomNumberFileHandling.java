import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class RandomNumberFileHandling {

    public static void main(String args[]) {

        int randomNum,total=0,highest=0,lowest=500, i=0;
        int[] allRandomNums = new int[100];
        String listOfNumbers="";
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("monospaced", Font.PLAIN,12));

        try{
            FileWriter out = new FileWriter("randomnumbers.txt");

            for(i=0;i<100;i++) {
                randomNum = (int)(Math.random()*500+1);

                out.write(Integer.toString(randomNum) + "\n");
            }

            out.close();

            Scanner input = new Scanner(new File("randomnumbers.txt"));

            i=0;

            while(input.hasNext()){
                randomNum = Integer.parseInt(input.nextLine());

                allRandomNums[i] = randomNum;

                i++;
            }

            i=0;

            for(int num: allRandomNums){
                if(num>highest)
                    highest = num;
                else if(num<lowest)
                    lowest = num;

                listOfNumbers+=String.format("%-5d",num);

                if((i+1)%10==0)
                    listOfNumbers+="\n";

                total+=num;

                i++;
            }

            textArea.append(listOfNumbers + "\n\nThe average of the numbers generated was: " + String.format("%.2f",(float)total/100) +
                    "\nThe highest number generated was: " + highest + "\nThe lowest number generated was: " + lowest);

            JOptionPane.showMessageDialog(null, textArea, "Random Number Stats", JOptionPane.INFORMATION_MESSAGE);

            input.close();

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