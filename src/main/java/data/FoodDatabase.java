package data;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.HashMap;

public class FoodDatabase {
    private HashMap<String, Food> foods;

    public FoodDatabase() {
        try {

            //preps reader
            File foodData = new File("FoodData.txt");
            Scanner myReader = new Scanner(foodData);

            //reads the header line to know which label each value is under
            String headers = myReader.nextLine();
            String[] headerSplit = headers.split("\\|");

            //loop to go through file
            while (myReader.hasNextLine()) {

                //reads and splits with '|' as splitter
                String data = myReader.nextLine();
                String[] dataSplit = data.split("\\|");

                //creates a temp food to add to the dictionary
                Food temp = new Food(dataSplit[0], Float.parseFloat(dataSplit[1]), Float.parseFloat(dataSplit[2]));

                //index after name, weight, and calories
                int tempIndex = 3;

                //for other labels which are for macros first (e.g. protein, fat)
                //  leave loop if it reads micro which is splitter
                while (dataSplit.length > tempIndex && !dataSplit[tempIndex].equals("micro")) {

                    //add macros to dict in Food instance
                    temp.addMacro(headerSplit[tempIndex], Float.parseFloat(dataSplit[tempIndex]));
                    tempIndex++;
                }

                //rest is micronutrients so end when string is empty
                while (dataSplit.length > tempIndex) {

                    //add micros to dict in Food instance
                    temp.addMicro(headerSplit[tempIndex], Float.parseFloat(dataSplit[tempIndex]));
                    tempIndex++;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public getInfo // keep going
}
