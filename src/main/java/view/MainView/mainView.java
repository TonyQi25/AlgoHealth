package view.MainView;


import interface_adapter.daily_value_recs.DailyValueRecsController;
import interface_adapter.daily_value_recs.MainViewModel;
import interface_adapter.daily_value_recs.MainViewState;
import interface_adapter.food_logging.LogFoodState;
import interface_adapter.food_logging.LogFoodViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class mainView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "main view";
    private final MainViewModel mainViewModel;
    private final LogFoodViewModel logFoodViewModel;

    private JLabel dailyValueCaloriesText = new JLabel();
    private JLabel dailyValueProteinText = new JLabel();
    private JLabel dailyValueCarbsText = new JLabel();
    private JLabel dailyValueFatText = new JLabel();

    private JLabel totalCalories = new JLabel("Total calories: 0Kcal");
    private JLabel totalProtein = new JLabel("Total protein: 0g");
    private JLabel totalCarbs = new JLabel("Total carbohydrates: 0g");
    private JLabel totalFat = new JLabel("Total fat: 0g");

    private DailyValueRecsController dailyValueRecsController;

    public mainView(MainViewModel mainViewModel, LogFoodViewModel logFoodViewModel) {

        this.mainViewModel = mainViewModel;
        this.logFoodViewModel = logFoodViewModel;
        this.mainViewModel.addPropertyChangeListener(this);

        // Input fields and labels part.
        JLabel enterFood = new JLabel("Enter food:");
        JTextField foodInputField = new JTextField(15);
        JLabel enterAmountNumber = new JLabel("Enter weight number:");
        JTextField foodAmountField = new JTextField(15);
        JLabel enterAmountUnits = new JLabel("Enter weight units:");
        JTextField unitInputField = new JTextField(15);
        JButton submitButton = new JButton("Submit");

        // Macronutrient and calories values display.
        JPanel jp1 = new JPanel();
        jp1.add(totalCalories);
        jp1.add(dailyValueCaloriesText);

        JPanel jp2 = new JPanel();
        jp2.add(totalProtein);
        jp2.add(dailyValueProteinText);

        JPanel jp3 = new JPanel();
        jp3.add(totalCarbs);
        jp3.add(dailyValueCarbsText);

        JPanel jp4 = new JPanel();
        jp4.add(totalFat);
        jp4.add(dailyValueFatText);

        JButton getDVrecs = new JButton("Daily Value Assessment");

        getDVrecs.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getDVrecs)) {
                            final MainViewState currentState = mainViewModel.getState();

                            dailyValueRecsController.execute(
                                    currentState.getCalories(),
                                    currentState.getProtein(),
                                    currentState.getCarbs(),
                                    currentState.getFat()
                            );

                        }
                    }
                });

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
        panel2.add(jp1);
        panel2.add(jp2);
        panel2.add(jp3);
        panel2.add(jp4);
        panel2.add(getDVrecs);

        //Food history panel.
        JPanel fhPanel = new JPanel();
        fhPanel.setLayout(new BoxLayout(fhPanel, BoxLayout.Y_AXIS));
        fhPanel.add(new JLabel("Day's Food History"));

        // Panel with history and macros side by side.
        JPanel sbsPanel = new JPanel();
        sbsPanel.add(fhPanel);
        sbsPanel.add(panel2);

        // Add to main panel.
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panel1);
        mainPanel.add(sbsPanel);

        this.add(mainPanel);

    }


    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MainViewState state = (MainViewState) evt.getNewValue();
        final LogFoodState logFoodState = (LogFoodState) evt.getNewValue();
        dailyValueCaloriesText.setText("is " + String.valueOf(state.getCalories()) +
                "% of the recommended Daily Value.");
        dailyValueProteinText.setText("is " + String.valueOf(state.getProtein()) +
                "% of the recommended Daily Value.");
        dailyValueCarbsText.setText("is " + String.valueOf(state.getCarbs()) +
                "% of the recommended Daily Value.");
        dailyValueFatText.setText("is " + String.valueOf(state.getFat()) +
                "% of the recommended Daily Value.");
        totalCalories.setText("Total calories" + String.valueOf(logFoodState.getTotalCalories()) +"Kcal");
        totalProtein.setText("Total proteing" + String.valueOf(logFoodState.getTotalProtein()) + "g");
        totalCarbs.setText("Total carbohydrates" + String.valueOf(logFoodState.getTotalCarbs()) + "g");
        totalFat.setText("Total caybohydrates" + String.valueOf(logFoodState.getTotalFat()) + "g");
    }

    public void setDailyValueRecsController(DailyValueRecsController dailyValueRecsController) {
        this.dailyValueRecsController = dailyValueRecsController;
    }
}
// Citation: lab-5 code https://github.com/CSC207-2024F-UofT/lab-5