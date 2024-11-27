package view.removeFoodView;

import interface_adapter.remove_food.RemoveFoodController;
import interface_adapter.remove_food.RemoveFoodViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RemoveFoodView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "remove food";
    private JLabel responseLabel;

    private JButton returnToHistoryButton;

    private RemoveFoodViewModel viewModel;
    private RemoveFoodController controller;

    public RemoveFoodView(RemoveFoodViewModel viewModel, RemoveFoodController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
        this.responseLabel = new JLabel();

    }

    private JButton returnButton() {
        JButton returning = new JButton("Return");
        returning.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }

        });

        return returning;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }
}
