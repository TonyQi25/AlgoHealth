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

public class RemoveFoodView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "remove food";
    private final JLabel responseLabel;

    private JButton returnToHistoryButton;

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
                System.out.println("return clicked");

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
        if (state.getRemoveFoodError().isEmpty()) {
            if (!state.getCompleted()) {
                controller.execute(state.getFoodName(), state.getWeight(), state.getUsername(), state.getViewingDate());
            } else {
                System.out.println("State completed and property change called");
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
