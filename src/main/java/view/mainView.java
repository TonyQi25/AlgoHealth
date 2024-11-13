package view;


import api.callUsdaApi;
import data.DayInfo;
import data.Food;
import interface_adapter.get_recommendations.getRecsController;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import static api.callUsdaApi.genMyApiKey;
import static api.populateFromUsda.foodFromFirstResultUsda;
import static api.populateFromUsda.foodFromResultUsda;

public class mainView {

    // for all instance attributes the item at 0 is the amount as a string, and the item at 1 is the unit.
    private ArrayList<Object> calories;
    private ArrayList<Object> protein;
    private ArrayList<Object> carbs;
    private ArrayList<Object> fat;
    private Food food;

    public mainView(){
        calories = new ArrayList<>();
        calories.add("0");
        calories.add("Kcal");

        protein = new ArrayList<>();
        protein.add("0");
        protein.add("g");

        carbs = new ArrayList<>();
        carbs.add("0");
        carbs.add("g");

        fat = new ArrayList<>();
        fat.add("0");
        fat.add("g");


    }

    public void genMvGUI(DayInfo day) {

        // Input fields and labels part.
        JLabel enterFood = new JLabel("Enter food:");
        JTextField foodInputField = new JTextField(15);
        JLabel enterAmountNumber = new JLabel("Enter weight number:");
        JTextField foodAmountField = new JTextField(15);
        JLabel enterAmountUnits = new JLabel("Enter weight units:");
        JTextField unitInputField = new JTextField(15);
        JButton submitButton = new JButton("Submit");

        // Macronutrient and calories values display.
        JLabel totalCalories = new JLabel("Total calories: " + calories.get(0) + calories.get(1));
        JLabel totalProtein = new JLabel("Total protein: " + protein.get(0) + protein.get(1));
        JLabel totalCarbs = new JLabel("Total carbohydrates: " + carbs.get(0) + protein.get(1));
        JLabel totalFat = new JLabel("Total fat: " + fat.get(0) + fat.get(1));
        JButton getDVrecs = new JButton("Daily Value Assessment");

        getDVrecs.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getRecsController.execute(calories, protein, carbs, fat);
                    }
                }
        );

        // Input and submit panel.
        JPanel panel1 = new JPanel();
        panel1.add(enterFood);
        panel1.add(enterAmountNumber);
        panel1.add(foodInputField);
        panel1.add(enterAmountNumber);
        panel1.add(foodAmountField);
        panel1.add(enterAmountUnits);
        panel1.add(unitInputField);
        panel1.add(submitButton);

        // Macros panel.
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(totalCalories);
        panel2.add(totalProtein);
        panel2.add(totalCarbs);
        panel2.add(totalFat);
        panel2.add(getDVrecs);

        //Food history panel.
        JPanel fhPanel = new JPanel();
        fhPanel.setLayout(new BoxLayout(fhPanel, BoxLayout.Y_AXIS));
        // loop through foods in Day.foodLog() and generate an entry.
        fhPanel.add(new JLabel("Day's Food History"));
        for (Food food: day.getFoodLog()) {
            JLabel food1 = new JLabel(food.getDescription() + " " + food.getWeight() + food.getStandardUnit());
            fhPanel.add(food1);
        }

        // Panel with history and macros side by side.
        JPanel sbsPanel = new JPanel();
        sbsPanel.add(fhPanel);
        sbsPanel.add(panel2);

        // Add to main panel.
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panel1);
        mainPanel.add(sbsPanel);


        JFrame mainFrame = new JFrame("AlgoHealth");
        mainFrame.setSize(1000,500);
        mainFrame.setContentPane(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);



    }

    public void setCalories(ArrayList<Object> calories) {
        this.calories = calories;
    }

    public void setCarbs(ArrayList<Object> carbs) {
        this.carbs = carbs;
    }

    public void setFat(ArrayList<Object> fat) {
        this.fat = fat;
    }

    public void setProtein(ArrayList<Object> protein) {
        this.protein = protein;
    }

    public static void main(String[] args) {
        // Informal test for the view.
        mainView mV = new mainView();
        DayInfo mockDay = new DayInfo(LocalDate.now());
        ArrayList<Food> foodList = new ArrayList<>();
        String apiKey = genMyApiKey("myFDCApiKey.txt");
        callUsdaApi usdaObj = new callUsdaApi(apiKey);
        Food result = foodFromFirstResultUsda("whole milk", usdaObj);
        foodList.add(result);
        foodList.add(result);
        foodList.add(result);
        foodList.add(result);
        foodList.add(result);
        mockDay.setFoodLog(foodList);
        mV.genMvGUI(mockDay);
        int i = 1;
    }

}

// Citation: lab-5 code https://github.com/CSC207-2024F-UofT/lab-5