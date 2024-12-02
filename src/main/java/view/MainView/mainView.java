package view.MainView;

import interface_adapter.ViewManagerModel;
import interface_adapter.daily_value_recs.DailyValueRecsController;
import interface_adapter.daily_value_recs.MainViewModel;
import interface_adapter.daily_value_recs.MainViewState;
import interface_adapter.food_logging.LogFoodController;
import interface_adapter.food_logging.LogFoodState;
import interface_adapter.food_logging.LogFoodViewModel;
import interface_adapter.display_food_options.DisplayFoodOptionsController;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.logout.LogoutController;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;

import static view.MainView.ViewFormattingUtility.main;
import static view.MainView.ViewFormattingUtility.truncateString2Places;

public class mainView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "main view";
    private final MainViewModel mainViewModel;
    private final LogFoodViewModel logFoodViewModel;
    private final HistoryViewModel historyViewModel;

    private JProgressBar progressBarCalories = new JProgressBar(0, 100);
    private JProgressBar progressBarProtein = new JProgressBar(0, 100);
    private JProgressBar progressBarCarbs = new JProgressBar(0, 100);
    private JProgressBar progressBarFat = new JProgressBar(0, 100);

    private JLabel totalCalories = new JLabel("0Kcal");
    private JLabel totalProtein = new JLabel("0g");
    private JLabel totalCarbs = new JLabel("0g");
    private JLabel totalFat = new JLabel("0g");

    private JTextField foodInputField = new JTextField(15);
    private JTextField foodAmountField = new JTextField(5);
    private JTextField unitInputField = new JTextField(5);

    private DailyValueRecsController dailyValueRecsController;
    private DisplayFoodOptionsController displayFoodOptionsController;

    private LogFoodController logFoodController;
    private LogoutController logoutController;
    private HistoryController historyController;

    public mainView(MainViewModel mainViewModel,
                    LogFoodViewModel logFoodViewModel,
                    HistoryViewModel historyViewModel) {

        this.mainViewModel = mainViewModel;
        this.logFoodViewModel = logFoodViewModel;
        this.mainViewModel.addPropertyChangeListener(this);
        this.logFoodViewModel.addPropertyChangeListener(this);
        this.historyViewModel = historyViewModel;
        this.historyViewModel.addPropertyChangeListener(this);

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

                final MainViewState mainViewState = mainViewModel.getState();
                mainViewState.setFoodSearchInput(foodInputField.getText());
                mainViewModel.setState(mainViewState);
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
                        // Change reading direct from field to mediated through a view model.
                        if (evt.getSource().equals(searchFoodButton)) {
                            displayFoodOptionsController.execute(mainViewModel.getState().getFoodSearchInput());
                        }
                    }
                });


        //Listeners for inputting food information

        submitButton.addActionListener(
                evt -> {
                    if (evt.getSource().equals(submitButton)) {
                        final LogFoodState currentState = logFoodViewModel.getState();
                        currentState.setPassword(mainViewModel.getState().getPassword());
                        currentState.setUsername(mainViewModel.getState().getUsername());
                        logFoodController.execute(currentState.getFdcIDofSelection(),
                                (float) currentState.getWeightNumber(),
                                currentState.getWeightUnit(), currentState.getUsername(), currentState.getPassword());
                    }
                }
        );

        addFoodAmountListener();
        addUnitInputFieldListener();

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
        JPanel searchPanel = new JPanel();
        searchPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        searchPanel.add(enterFood);
        searchPanel.add(foodInputField);
        searchPanel.add(searchFoodButton);

        JPanel foodWeightUnitsSubmitPanel = new JPanel();
        foodWeightUnitsSubmitPanel.setBorder(BorderFactory.createRaisedBevelBorder());

        foodWeightUnitsSubmitPanel.add(enterAmountNumber);
        foodWeightUnitsSubmitPanel.add(foodAmountField);
        foodWeightUnitsSubmitPanel.add(enterAmountUnits);
        foodWeightUnitsSubmitPanel.add(unitInputField);
        foodWeightUnitsSubmitPanel.add(submitButton);

        JPanel searchAndWeightSBSPanel = new JPanel();
        searchAndWeightSBSPanel.add(searchPanel);
        searchAndWeightSBSPanel.add(foodWeightUnitsSubmitPanel);

        // Button panel.
        JPanel logoutPanel = getLogoutPanel(this.mainViewModel);

        // Add to main panel.
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(searchAndWeightSBSPanel);

        JPanel totalsAndRecPanel = new JPanel();
        totalsAndRecPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        totalsAndRecPanel.setLayout(new GridLayout(5, 3));
        JLabel caloriesLabel = new JLabel("Total calories: ");
        JLabel proteinLabel = new JLabel("Total protein: ");
        JLabel carbsLabel = new JLabel("Total carbohydrates: ");
        JLabel fatLabel = new JLabel("Total fat: ");
        Dimension size = caloriesLabel.getPreferredSize();
        totalsAndRecPanel.setPreferredSize(new Dimension((int) 500, (int) size.getHeight() + 200));

        totalsAndRecPanel.add(caloriesLabel);
        totalsAndRecPanel.add(totalCalories);
        totalsAndRecPanel.add(progressBarCalories);

        totalsAndRecPanel.add(proteinLabel);
        totalsAndRecPanel.add(totalProtein);
        totalsAndRecPanel.add(progressBarProtein);

        totalsAndRecPanel.add(carbsLabel);
        totalsAndRecPanel.add(totalCarbs);
        totalsAndRecPanel.add(progressBarCarbs);

        totalsAndRecPanel.add(fatLabel);
        totalsAndRecPanel.add(totalFat);
        totalsAndRecPanel.add(progressBarFat);

        totalsAndRecPanel.add(new JLabel(""));
        totalsAndRecPanel.add(new JLabel(""));
        totalsAndRecPanel.add(getDVrecs);

        // totalsAndRecPanel complete so add it and logout to mainPanel and wrap everything up.
        mainPanel.add(totalsAndRecPanel);
        // mainPanel.add(logoutPanel);

        JButton history = new JButton("History");

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainViewState mainState = mainViewModel.getState();
                HistoryState historyState = historyViewModel.getState();

                historyState.setCompleted(false);
                historyState.setUsername(mainState.getUsername());
                historyState.setPassword(mainState.getPassword());
                historyState.setViewingDate(LocalDate.now());
                historyState.setHistoryError("");
                historyState.setDate("");
                historyState.setDayDetails(null);

                HistoryState testingState = historyViewModel.getState();

                historyViewModel.firePropertyChanged();



                historyController.execute(LocalDate.now(), 0, mainState.getUsername(), mainState.getPassword());
            }
        });

        JPanel logoutAndHistory = new JPanel();
        logoutAndHistory.add(logoutPanel);
        logoutAndHistory.add(history);

        mainPanel.add(logoutAndHistory);

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
            totalCalories.setText(truncateString2Places(String.valueOf(logFoodState.getTotalCalories())) + "Kcal");
            totalProtein.setText(truncateString2Places(String.valueOf(logFoodState.getTotalProtein())) + "g");
            totalCarbs.setText(truncateString2Places(String.valueOf(logFoodState.getTotalCarbs())) + "g");
            totalFat.setText(truncateString2Places(String.valueOf(logFoodState.getTotalFat())) + "g");

            progressBarCalories.setValue(0);
            progressBarCalories.setStringPainted(false);
            progressBarProtein.setValue(0);
            progressBarProtein.setStringPainted(false);
            progressBarCarbs.setValue(0);
            progressBarCarbs.setStringPainted(false);
            progressBarFat.setValue(0);
            progressBarFat.setStringPainted(false);
        }
        else if (evt.getSource() instanceof MainViewModel) {
            final MainViewState state = (MainViewState) evt.getNewValue();
            progressBarCalories.setValue((int) Math.floor(state.getPercentCals()));
            String valueString = truncateString2Places(String.valueOf(state.getPercentCals()));
            progressBarCalories.setString(valueString + "% of DV");
            progressBarCalories.setStringPainted(true);

            progressBarProtein.setValue((int) Math.floor(state.getPercentProt()));
            valueString = truncateString2Places(String.valueOf(state.getPercentProt()));
            progressBarProtein.setString(valueString + "% of DV");
            progressBarProtein.setStringPainted(true);

            progressBarCarbs.setValue((int) Math.floor(state.getPercentCarbs()));
            valueString = truncateString2Places(String.valueOf(state.getPercentCarbs()));
            progressBarCarbs.setString(valueString + "% of DV");
            progressBarCarbs.setStringPainted(true);

            progressBarFat.setValue((int) Math.floor(state.getPercentFat()));
            valueString = truncateString2Places(String.valueOf(state.getPercentFat()));
            progressBarFat.setString(valueString + "% of DV");
            progressBarFat.setStringPainted(true);
        }
    }


    public void setDailyValueRecsController(DailyValueRecsController dailyValueRecsController) {
        this.dailyValueRecsController = dailyValueRecsController;
    }

    public void setLogFoodController(LogFoodController logFoodController) {
        this.logFoodController = logFoodController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setHistoryController(HistoryController historyController) {
        this.historyController = historyController;
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

    private void addUnitInputFieldListener() {
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

    @NotNull
    private JPanel getLogoutPanel(MainViewModel mainViewModel) {
        JPanel logoutPanel = new JPanel();
        JButton logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logoutController.execute(mainViewModel.getState().getUsername());
            }
        });
        logoutPanel.add(logoutButton);
        return logoutPanel;
    }

    private void setFields() {
        this.foodInputField.setText("");
        this.foodAmountField.setText("");
        this.unitInputField.setText("");
    }
}