package view;

import interface_adapter.display_food_options.DisplayFoodOptionsController;
import interface_adapter.display_food_options.DisplayOptionsViewModel;
import interface_adapter.display_food_options.DisplayOptionsViewState;
import interface_adapter.select_from_food_options.SelectFromFoodOptionsController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DisplayOptionsView extends JPanel implements ActionListener, PropertyChangeListener {

    private JLabel dailyValueProteinText = new JLabel();
    private String viewName = "display options view";
    private DisplayFoodOptionsController displayFoodOptionsController;
    private SelectFromFoodOptionsController selectFromFoodOptionsController;
    private JComboBox dropdown = new JComboBox();
    private JLabel errorMessage = new JLabel("");

    private DisplayOptionsViewModel displayFoodOptionsViewModel;

    public DisplayOptionsView(DisplayOptionsViewModel displayOptionsViewModel) {
        this.displayFoodOptionsViewModel = displayOptionsViewModel;
        displayFoodOptionsViewModel.addPropertyChangeListener(this);
        DisplayOptionsViewState currState = displayOptionsViewModel.getState();
        JPanel mainPanel = new JPanel();
        JButton selectAndReturn = new JButton("Select and Return");
        mainPanel.add(dropdown);
        mainPanel.add(selectAndReturn);

        JPanel actualMainPanel = new JPanel();
        actualMainPanel.setLayout(new BoxLayout(actualMainPanel, BoxLayout.Y_AXIS));
        actualMainPanel.add(mainPanel);
        actualMainPanel.add(errorMessage);

        this.add(actualMainPanel);

        dropdown.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(dropdown)) {
                            JComboBox comboBox = (JComboBox) evt.getSource();
                            String selected = (String) comboBox.getSelectedItem();
                            DisplayOptionsViewState displayOptionsViewState = displayOptionsViewModel.getState();
                            displayOptionsViewState.setCurrSelection(selected);
                            /*int i = 0;
                            currentState.setFdcIDofSelection(currentState.getFoodOptionsMap().get(selected));*/
                        }

                    }
                });
        selectAndReturn.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // selectFromFoodOptionsController.execute((String) dropdown.getSelectedItem());
                        selectFromFoodOptionsController.execute(displayOptionsViewModel.getState().getCurrSelection(),
                                displayOptionsViewModel.getState().getErrorMessage());
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() instanceof DisplayOptionsViewModel) {
            DisplayOptionsViewState currState = (DisplayOptionsViewState) evt.getNewValue();
            dropdown.removeAllItems();
            for (String foodDesc: currState.getSelectionList()) {
                dropdown.addItem(foodDesc);
            }
            this.errorMessage.setText(currState.getErrorMessage());
        }

    }

    public String getViewName() {
        return viewName;
    }

    public DisplayFoodOptionsController getDisplayFoodOptionsController() {
        return displayFoodOptionsController;
    }

    public void setDisplayFoodOptionsController(DisplayFoodOptionsController displayFoodOptionsController) {
        this.displayFoodOptionsController = displayFoodOptionsController;
    }

    public void setSelectFromFoodOptionsController(SelectFromFoodOptionsController selectFromFoodOptionsController) {
        this.selectFromFoodOptionsController = selectFromFoodOptionsController;
    }
}
