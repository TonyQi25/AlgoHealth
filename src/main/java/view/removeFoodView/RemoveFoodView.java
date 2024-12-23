package view.removeFoodView;

import interface_adapter.history.HistoryState;
import interface_adapter.remove_food.RemoveFoodController;
import interface_adapter.remove_food.RemoveFoodState;
import interface_adapter.remove_food.RemoveFoodViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * view for remove food use case
 */
public class RemoveFoodView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "remove food";

    private String username;
    private String password;
    private String date;

    private final JLabel responseLabel;

    private final JButton returnToHistoryButton;

    private RemoveFoodViewModel viewModel;
    private RemoveFoodController controller;

    public RemoveFoodView(RemoveFoodViewModel viewModel, RemoveFoodController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.responseLabel = new JLabel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        responseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(responseLabel);
        this.returnToHistoryButton = returnButton();
        returnToHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(returnToHistoryButton);

        this.viewModel.addPropertyChangeListener(this);
    }

    private JButton returnButton() {
        JButton returning = new JButton("Return");
        returning.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                controller.returnToHistory(date, username, password);

            }

        });

        return returning;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final RemoveFoodState state = (RemoveFoodState) evt.getNewValue();

        username = state.getUsername();
        password = state.getPassword();
        date = state.getViewingDate();

        if (state.getRemoveFoodError().isEmpty()) {
            if (!state.getCompleted()) {
                controller.execute(state.getFoodName(), state.getWeight(), state.getUsername(), state.getViewingDate(), password);
            } else {
                responseLabel.setText(state.getOutputMessage());
            }
        }   else {
            responseLabel.setText(state.getRemoveFoodError());
        }
    }

    public String getViewName() {
        return viewName;
    }
}
