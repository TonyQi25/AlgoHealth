package view.MainView;


import api.FoodDataCentralSearchDAO;
import interface_adapter.daily_value_recs.DailyValueRecsController;
import interface_adapter.daily_value_recs.MainViewModel;
import interface_adapter.daily_value_recs.MainViewState;
import interface_adapter.food_logging.LogFoodController;
import interface_adapter.food_logging.LogFoodState;
import interface_adapter.food_logging.LogFoodViewModel;
import interface_adapter.display_food_options.DisplayFoodOptionsController;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collection;
import java.util.HashMap;

import static api.FoodDataCentralSearchDAO.genMyApiKey;

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

    private JTextField foodInputField = new JTextField(15);
    private JTextField foodAmountField = new JTextField(15);
    private JTextField unitInputField = new JTextField(15);

    private DailyValueRecsController dailyValueRecsController;
    private DisplayFoodOptionsController displayFoodOptionsController;

    private LogFoodController logFoodController;

    public mainView(MainViewModel mainViewModel, LogFoodViewModel logFoodViewModel) {

        this.mainViewModel = mainViewModel;
        this.logFoodViewModel = logFoodViewModel;
        // this.mainViewModel.addPropertyChangeListener(this);
        this.logFoodViewModel.addPropertyChangeListener(this);

        // Input fields and labels part.
        JLabel enterFood = new JLabel("Enter food:");
        JTextField foodInputField = new JTextField(15);

        JLabel enterAmountNumber = new JLabel("Enter weight number:");
        JLabel enterAmountUnits = new JLabel("Enter weight units:");
        JButton submitButton = new JButton("Submit");
        JButton searchFoodButton = new JButton("Search");

        foodInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LogFoodState currentMVState = logFoodViewModel.getState();
                currentMVState.setFoodSearchInput(foodInputField.getText());
                logFoodViewModel.setState(currentMVState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        searchFoodButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(searchFoodButton)) {
                        final LogFoodState currentState = logFoodViewModel.getState();
                        // displayFoodOptionsController.execute(currentState.getFoodSearchInput());
                        ;
                        FoodDataCentralSearchDAO usdaObj = new FoodDataCentralSearchDAO(genMyApiKey(
                                "myFDCApiKey.txt"));
                        HashMap<String, Integer> foodMap = usdaObj.first10FoundationFoods(currentState
                                .getFoodSearchInput());
                        Collection<String> foodMapKeys = foodMap.keySet();
                        String[] foodList = new String[foodMap.values().size()];
                        int i = 0;
                        for (String item : foodMapKeys) {
                            foodList[i] = item;
                            i += 1;
                        }
                        currentState.setFoodOptionsMap(foodMap);
                        selectFromListPopup popUp = new selectFromListPopup(currentState,foodList);
                        }
                    }
                });


        //Listeners for inputting food information

        submitButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(submitButton)) {
                        final LogFoodState currentState = logFoodViewModel.getState();

                        logFoodController.execute(currentState.getFdcIDofSelection(),
                                (float) currentState.getWeightNumber(),
                                currentState.getWeightUnit());
                    }
                }
        );

        //addFoodListener();
        addFoodAmountListener();
        addUnitInputFieldListener();

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
        panel1.add(searchFoodButton);
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
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // final MainViewState state = (MainViewState) evt.getNewValue();
        if (evt.getSource() instanceof LogFoodViewModel) {
            final LogFoodState logFoodState = (LogFoodState) evt.getNewValue();
       /* dailyValueCaloriesText.setText("is " + String.valueOf(state.getCalories()) +
                "% of the recommended Daily Value.");
        dailyValueProteinText.setText("is " + String.valueOf(state.getProtein()) +
                "% of the recommended Daily Value.");
        dailyValueCarbsText.setText("is " + String.valueOf(state.getCarbs()) +
                "% of the recommended Daily Value.");
        dailyValueFatText.setText("is " + String.valueOf(state.getFat()) +
                "% of the recommended Daily Value.");*/
            totalCalories.setText("Total calories: " + String.valueOf(logFoodState.getTotalCalories()) + "Kcal");
            totalProtein.setText("Total protein: " + String.valueOf(logFoodState.getTotalProtein()) + "g");
            totalCarbs.setText("Total carbohydrates" + String.valueOf(logFoodState.getTotalCarbs()) + "g");
            totalFat.setText("Total fat: " + String.valueOf(logFoodState.getTotalFat()) + "g");
        }
    }


    public void setDailyValueRecsController(DailyValueRecsController dailyValueRecsController) {
        this.dailyValueRecsController = dailyValueRecsController;
    }

    public void setLogFoodController(LogFoodController logFoodController) {
        this.logFoodController = logFoodController;
    }
/*    private void addFoodListener (){
        foodInputField.getDocument().addDocumentListener(new DocumentListener() {private void documentListenerHelper() {
            final LogFoodState currentState = logFoodViewModel.getState();
            logFoodViewModel.setState(currentState);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            documentListenerHelper();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            documentListenerHelper();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            documentListenerHelper();
        }
    });}*/

    private void addFoodAmountListener(){
        foodAmountField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LogFoodState currentState = logFoodViewModel.getState();
                currentState.setWeightNumber(Float.valueOf(foodAmountField.getText()));
                logFoodViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addUnitInputFieldListener(){
        unitInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final LogFoodState currentState = logFoodViewModel.getState();
                logFoodViewModel.setState(currentState);
                System.out.println(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }
}