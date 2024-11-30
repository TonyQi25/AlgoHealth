package view.DeleteFoodView;

import helpers.ViewHelpers;
import interface_adapter.delete_food.DeleteFoodController;
import interface_adapter.delete_food.DeleteFoodPresenter;
import interface_adapter.delete_food.DeleteFoodViewModel;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeleteFoodView extends JPanel implements PropertyChangeListener {

    private final DeleteFoodViewModel deleteFoodViewModel;
    private final DeleteFoodController deleteFoodController;
    private final String viewName = "delete food";

    private final JPanel deleteFoodPanel = new JPanel();
    private JPanel introPanel;
    private JPanel foodListPanel;
    private JPanel buttonPanel;

    public DeleteFoodView(DeleteFoodViewModel deleteFoodViewModel, DeleteFoodController deleteFoodController) {
        this.deleteFoodViewModel = deleteFoodViewModel;
        this.deleteFoodController = deleteFoodController;

        this.deleteFoodViewModel.addPropertyChangeListener(this);

        // Introduction panel
        this.introPanel = new JPanel();
        JLabel introLabel = new JLabel("Select a food and click the Delete button to remove it!");

        this.introPanel.add(introLabel);

        // Food list panel
        this.foodListPanel = new JPanel();
        JList<String> loggedFood = new JList<>(this.deleteFoodViewModel.getState().getFoods().toArray(new String[0]));
        loggedFood.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane loggedFoodPane = new JScrollPane(loggedFood);

        this.foodListPanel.add(loggedFoodPane);

        // Buttons panel
        this.buttonPanel = new JPanel();
        JButton deleteButton = ViewHelpers.getDeleteButton(this.deleteFoodViewModel, this.deleteFoodController);
        JButton goToHistoryButton = ViewHelpers.getDeleteToHistoryButton(this.deleteFoodController);

        this.buttonPanel.add(deleteButton);
        this.buttonPanel.add(goToHistoryButton);

        // Putting it all together
        this.deleteFoodPanel.setLayout(new BoxLayout(this.deleteFoodPanel, BoxLayout.Y_AXIS));
        this.deleteFoodPanel.add(this.introPanel);
        this.deleteFoodPanel.add(this.foodListPanel);
        this.deleteFoodPanel.add(this.buttonPanel);

        this.add(this.deleteFoodPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return this.viewName;
    }
}
