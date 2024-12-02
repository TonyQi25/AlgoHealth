package view.HistoryView;

import data_access.TempHistoryDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;
import interface_adapter.remove_food.RemoveFoodController;
import interface_adapter.remove_food.RemoveFoodPresenter;
import interface_adapter.remove_food.RemoveFoodViewModel;
import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInteractor;
import use_case.history.HistoryOutputBoundary;
import use_case.removeFood.RemoveFoodInputBoundary;
import use_case.removeFood.RemoveFoodInteractor;
import use_case.removeFood.RemoveFoodOutputBoundary;
import view.ViewManager;
import view.removeFoodView.RemoveFoodView;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;

/**
 * view for history use case
 */
public class HistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "history";
    private String username;
    private String password;
    private final HistoryViewModel historyViewModel;

    private JPanel headerPanel;
    private JPanel dataPanel;

    private LocalDate viewingDate;
    private JList<String> list;
    private JLabel dateLabel;
    private JLabel errorLabel;

    private JButton prevButton;
    private JButton nextButton;

    private JButton backButton;

    private JButton removeFoodButton;
    private JButton oneDayButton;

    private HistoryController historyController;

    public HistoryView(HistoryViewModel viewModel, HistoryController historyController) {
        this.historyViewModel = viewModel;
        this.historyController = historyController;
        this.historyViewModel.addPropertyChangeListener(this);
        this.viewingDate = LocalDate.now();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        build();
    }

    public void setHeaderPanel() {
        headerPanel = new JPanel();
        dateLabel = new JLabel("Date: " + this.viewingDate.toString());
        errorLabel = new JLabel("");
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.add(getPrevDayButton());
        this.add(errorLabel);
        headerPanel.add(dateLabel);
        headerPanel.add(getNextDayButton());
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.add(headerPanel);

    }

    public JButton getPrevDayButton() {
        return createPrevDayButton();
    }

    private JButton createRemoveFoodButton() {
        removeFoodButton = new JButton("Remove Food");
        removeFoodButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeFoodButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                historyController.removeHighlightedFood(list.getSelectedValue(), username, viewingDate.toString(), password);
            }
        });

        return removeFoodButton;
    }

    private JButton createBackButton() {
        backButton = new JButton("return");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                historyController.goBack();

            }
        });

        return backButton;
    }

    private JButton createOneDayStatsButton() {
        oneDayButton = new JButton("Day Details");
        oneDayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        oneDayButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                historyController.viewOneDay(username, viewingDate.toString(), password);

            }
        });

        return oneDayButton;
    }

    private JButton createPrevDayButton() {
        // calls to controller
        prevButton = new JButton("Previous Day");
        prevButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyController.execute(viewingDate, -1, username, password);
            }
        });
        return prevButton;
    }

    public JButton getNextDayButton() {
        return createNextDayButton();
    }

    private JButton createNextDayButton() {
        nextButton = new JButton("Next Day");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                historyController.execute(viewingDate, 1, username, password);
            }
        });
        return nextButton;
    }

    public void setDataPanel() {
        dataPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane(createDataList());
        scrollPane.setMaximumSize(new Dimension(600, 400));
        scrollPane.setMinimumSize(new Dimension(600, 400));
        dataPanel.add(scrollPane);
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.add(createRemoveFoodButton());
        this.add(dataPanel);
    }

    public JList<String> createDataList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();

        list = new JList<>(listModel);
        list.setFixedCellWidth(300);

        return list;
    }

    public void build() {
        setHeaderPanel();
        setDataPanel();
        this.add(createBackButton());
        this.add(createOneDayStatsButton());
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final HistoryState state = (HistoryState) evt.getNewValue();
        username = state.getUsername();
        System.out.println("In HistoryView: " + username);
        password = state.getPassword();
        System.out.println("In HistoryView Password: " + password);
        if (state.getHistoryError().isEmpty()) {
            if (!state.getCompleted()) {
                historyController.execute(state.getViewingDate(), 0, state.getUsername(), password);
            } else {
                setFields(state);
            }
        } else {
            errorLabel.setText(state.getHistoryError());
        }

    }

    private void setFields(HistoryState state) {
        System.out.println("Hit set property change");
        List<String> info = state.getDayDetails();
        DefaultListModel<String> tempList = new DefaultListModel<>();

        for (String food: info) {
            tempList.addElement(food);
        }

        list.setModel(tempList);

        dateLabel.setText("Date: " + state.getDate());
        viewingDate = state.getViewingDate();
        errorLabel.setText("");


    }
}
