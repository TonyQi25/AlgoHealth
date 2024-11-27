package view.removeFoodView;

import interface_adapter.remove_food.RemoveFoodController;
import interface_adapter.remove_food.RemoveFoodViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RemoveFoodView extends JPanel implements ActionListener, PropertyChangeListener {

    private JLabel responseLabel;

    private JButton returnToHistoryButton;

    private RemoveFoodViewModel viewModel;
    private RemoveFoodController controller;

    public RemoveFoodView(RemoveFoodViewModel viewModel, RemoveFoodController controller) {
        this.viewModel = viewModel;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
