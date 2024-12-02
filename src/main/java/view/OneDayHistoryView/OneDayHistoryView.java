package view.OneDayHistoryView;

import helpers.ViewHelpers;
import interface_adapter.daily_value_recs.MainViewModel;
import interface_adapter.daily_value_recs.MainViewState;
import interface_adapter.delete_food.DeleteFoodController;
import interface_adapter.delete_food.DeleteFoodViewModel;
import interface_adapter.food_logging.LogFoodState;
import interface_adapter.food_logging.LogFoodViewModel;
import interface_adapter.history.HistoryState;
import interface_adapter.one_day_history.UpdateHistoryTotalsController;
import interface_adapter.one_day_history.UpdateHistoryTotalsState;
import interface_adapter.one_day_history.UpdateHistoryTotalsViewModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import static view.MainView.ViewFormattingUtility.truncateString2Places;

/**
 * view for one day history use case
 */
public class OneDayHistoryView extends JPanel implements PropertyChangeListener {

    private final UpdateHistoryTotalsViewModel updateHistoryTotalsViewModel;
    private final UpdateHistoryTotalsController updateHistoryTotalsController;
    private final String viewName = "one day history";

    private String username;
    private String password;
    private String viewingDate;

    private JProgressBar progressBarCalories = new JProgressBar(0, 100);
    private JProgressBar progressBarProtein = new JProgressBar(0, 100);
    private JProgressBar progressBarCarbs = new JProgressBar(0, 100);
    private JProgressBar progressBarFat = new JProgressBar(0, 100);

    private JLabel totalCalories = new JLabel("0Kcal");
    private JLabel totalProtein = new JLabel("0g");
    private JLabel totalCarbs = new JLabel("0g");
    private JLabel totalFat = new JLabel("0g");

    public OneDayHistoryView(UpdateHistoryTotalsViewModel updateHistoryTotalsViewModel,
                             UpdateHistoryTotalsController updateHistoryTotalsController) {
        this.updateHistoryTotalsViewModel = updateHistoryTotalsViewModel;
        this.updateHistoryTotalsController = updateHistoryTotalsController;

        this.updateHistoryTotalsViewModel.addPropertyChangeListener(this);

        UpdateHistoryTotalsState updateHistoryTotalsState = updateHistoryTotalsViewModel.getState();

        JPanel overviewPanel = new JPanel();
        JLabel overviewLabel = new JLabel("Food Nutrient Details for " + updateHistoryTotalsState.getDate());
        overviewPanel.add(overviewLabel);

        JPanel totalsAndRecPanel = new JPanel();
        totalsAndRecPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        totalsAndRecPanel.setLayout(new GridLayout(5, 3));
        JLabel caloriesLabel = new JLabel("Total calories: ");
        JLabel proteinLabel = new JLabel("Total protein: ");
        JLabel carbsLabel = new JLabel("Total carbohydrates: ");
        JLabel fatLabel = new JLabel("Total fat: ");
        Dimension size = caloriesLabel.getPreferredSize();
        totalsAndRecPanel.setPreferredSize(new Dimension((int) 500, (int) size.getHeight() + 200));

        this.setDisplay(updateHistoryTotalsState);

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

        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Go Back");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHistoryTotalsController.switchToHistory();
            }
        });

        buttonPanel.add(backButton);

        JPanel historyDetailsPanel = new JPanel();
        historyDetailsPanel.setLayout(new BoxLayout(historyDetailsPanel, BoxLayout.Y_AXIS));

        historyDetailsPanel.add(overviewPanel);
        historyDetailsPanel.add(totalsAndRecPanel);
        historyDetailsPanel.add(buttonPanel);

        this.add(historyDetailsPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final UpdateHistoryTotalsState state = (UpdateHistoryTotalsState) evt.getNewValue();
        username = state.getUsername();
        password = state.getPassword();
        viewingDate = state.getDate();

        if(!state.getCompleted()) {
            updateHistoryTotalsController.execute(username, viewingDate, password);
        }   else {
            System.out.println("putting up the values: " + state.getCalories());
            setDisplay(state);
        }
    }


    public String getViewName() {
        return this.viewName;
    }

    private void setDisplay(UpdateHistoryTotalsState updateHistoryTotalsState) {
        totalCalories.setText(truncateString2Places((updateHistoryTotalsState.getCalories()) + "Kcal"));
        totalProtein.setText(truncateString2Places(String.valueOf(updateHistoryTotalsState.getProtein())) + "g");
        totalCarbs.setText(truncateString2Places(String.valueOf(updateHistoryTotalsState.getCarbs())) + "g");
        totalFat.setText(truncateString2Places(String.valueOf(updateHistoryTotalsState.getFat())) + "g");

        progressBarCalories.setValue((int) Math.floor(updateHistoryTotalsState.getPercentCalories()));
        String valueString = truncateString2Places(String.valueOf(updateHistoryTotalsState.getPercentCalories()));
        progressBarCalories.setString(valueString + "% of DV");
        progressBarCalories.setStringPainted(true);

        progressBarProtein.setValue((int) Math.floor(updateHistoryTotalsState.getPercentProtein()));
        valueString = truncateString2Places(String.valueOf(updateHistoryTotalsState.getPercentProtein()));
        progressBarProtein.setString(valueString + "% of DV");
        progressBarProtein.setStringPainted(true);

        progressBarCarbs.setValue((int) Math.floor(updateHistoryTotalsState.getPercentCarbs()));
        valueString = truncateString2Places(String.valueOf(updateHistoryTotalsState.getPercentCarbs()));
        progressBarCarbs.setString(valueString + "% of DV");
        progressBarCarbs.setStringPainted(true);

        progressBarFat.setValue((int) Math.floor(updateHistoryTotalsState.getPercentFat()));
        valueString = truncateString2Places(String.valueOf(updateHistoryTotalsState.getPercentFat()));
        progressBarFat.setString(valueString + "% of DV");
        progressBarFat.setStringPainted(true);
    }
}
