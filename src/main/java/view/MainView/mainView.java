package view.MainView;


import data_access.UserFoodSearchInMemoryDAO;
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

public class mainView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "main view";
    private final MainViewModel mainViewModel;
    private final LogFoodViewModel logFoodViewModel;

    // private JLabel dailyValueCaloriesText = new JLabel();
    private JProgressBar progressBarCalories = new JProgressBar(0, 100);
    private JProgressBar progressBarProtein = new JProgressBar(0, 100);
    private JProgressBar progressBarCarbs = new JProgressBar(0, 100);
    private JProgressBar progressBarFat = new JProgressBar(0, 100);

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

    private UserFoodSearchInMemoryDAO userFoodSearchInMemoryDAO = new UserFoodSearchInMemoryDAO();

    public mainView(MainViewModel mainViewModel, LogFoodViewModel logFoodViewModel) {

        this.mainViewModel = mainViewModel;
        this.logFoodViewModel = logFoodViewModel;
        this.mainViewModel.addPropertyChangeListener(this);
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
                //final UserFoodSearchInMemoryDAO foodSearchDAO = userFoodSearchInMemoryDAO;
                currentMVState.setFoodSearchInput(foodInputField.getText());
                // foodSearchDAO.setFoodSearchInput(foodInputField.getText());
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
/*            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(searchFoodButton)) {
                        //final LogFoodState currentState = logFoodViewModel.getState();
                        final LogFoodState currentState = logFoodViewModel.getState();
                        final UserFoodSearchInMemoryDAO foodSearchDAO = userFoodSearchInMemoryDAO;
                        FoodDataCentralSearchDAO usdaObj = new FoodDataCentralSearchDAO(genMyApiKey(
                                "myFDCApiKey.txt"));
                        HashMap<String, Integer> foodMap = usdaObj.first10FoundationFoods(currentState
                                .getFoodSearchInput());
                        *//*HashMap<String, Integer> foodMap = usdaObj.first10FoundationFoods(foodSearchDAO
                                .getFoodSearchInput());*//*
                        Collection<String> foodMapKeys = foodMap.keySet();
                        String[] foodList = new String[foodMap.values().size()];
                        int i = 0;
                        for (String item : foodMapKeys) {
                            foodList[i] = item;
                            i += 1;
                        }
                        currentState.setFoodOptionsMap(foodMap);
                        // foodSearchDAO.setFoodOptionsMap(foodMap);
                        selectFromListPopup popUp = new selectFromListPopup(currentState,foodList);
                        // String stringy = JOptionPane.showInputDialog(new JComboBox(foodList));*/
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(searchFoodButton)) {
                            displayFoodOptionsController.execute(searchFoodButton.getText());
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
        //jp1.add(dailyValueCaloriesText);
        jp1.add(progressBarCalories);

        JPanel jp2 = new JPanel();
        jp2.add(totalProtein);
        // jp2.add(dailyValueProteinText);
        jp2.add(progressBarProtein);

        JPanel jp3 = new JPanel();
        jp3.add(totalCarbs);
        // jp3.add(dailyValueCarbsText);
        jp3.add(progressBarCarbs);

        JPanel jp4 = new JPanel();
        jp4.add(totalFat);
        // jp4.add(dailyValueFatText);
        jp4.add(progressBarFat);

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

        if (evt.getSource() instanceof LogFoodViewModel) {
            final LogFoodState logFoodState = (LogFoodState) evt.getNewValue();
            totalCalories.setText("Total calories: " + String.valueOf(logFoodState.getTotalCalories()) + "Kcal");
            totalProtein.setText("Total protein: " + String.valueOf(logFoodState.getTotalProtein()) + "g");
            totalCarbs.setText("Total carbohydrates" + String.valueOf(logFoodState.getTotalCarbs()) + "g");
            totalFat.setText("Total fat: " + String.valueOf(logFoodState.getTotalFat()) + "g");

            //dailyValueCaloriesText.setText("");
            dailyValueProteinText.setText("");
            dailyValueCarbsText.setText("");
            dailyValueFatText.setText("");
        }
        else if (evt.getSource() instanceof MainViewModel) {
            final MainViewState state = (MainViewState) evt.getNewValue();
            // dailyValueCaloriesText.setText("is " + String.valueOf(state.getPercent_cals()) +
                    //"% of the recommended Daily Value.");
            //progressBarCalories.setString(String.valueOf(state.getPercent_cals()));
            progressBarCalories.setValue((int) Math.floor(state.getPercent_cals()));
            progressBarCalories.setString(String.valueOf(Math.floor(state.getPercent_cals()) + "% of DV"));
            progressBarCalories.setStringPainted(true);

            progressBarProtein.setValue((int) Math.floor(state.getPercent_prot()));
            progressBarProtein.setString(String.valueOf(Math.floor(state.getPercent_prot()) + "% of DV"));
            progressBarProtein.setStringPainted(true);

            progressBarCarbs.setValue((int) Math.floor(state.getPercent_carbs()));
            progressBarCarbs.setString(String.valueOf(Math.floor(state.getPercent_carbs()) + "% of DV"));
            progressBarCarbs.setStringPainted(true);

            progressBarFat.setValue((int) Math.floor(state.getPercent_fat()));
            progressBarFat.setString(String.valueOf(Math.floor(state.getPercent_fat()) + "% of DV"));
            progressBarFat.setStringPainted(true);


            /*dailyValueProteinText.setText("is " + String.valueOf(state.getPercent_prot()) +
                    "% of the recommended Daily Value.");
            dailyValueCarbsText.setText("is " + String.valueOf(state.getPercent_carbs()) +
                    "% of the recommended Daily Value.");
            dailyValueFatText.setText("is " + String.valueOf(state.getPercent_fat()) +
                    "% of the recommended Daily Value.");*/
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

    public DisplayFoodOptionsController getDisplayFoodOptionsController() {
        return displayFoodOptionsController;
    }

    public void setDisplayFoodOptionsController(DisplayFoodOptionsController displayFoodOptionsController) {
        this.displayFoodOptionsController = displayFoodOptionsController;
    }
}