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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;

public class HistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "history";
    private String username;
    private final HistoryViewModel historyViewModel;
    private String highlightedFood;

    private JPanel headerPanel;
    private JPanel dataPanel;

    private LocalDate viewingDate;
    private JList<String> list;
    private JLabel dateLabel;
    private JLabel errorLabel;

    private JButton prevButton;
    private JButton nextButton;

    private JButton removeFoodButton;

    //private RemoveFoodController removeFoodController;
    private HistoryController historyController;

    public HistoryView(HistoryViewModel viewModel, HistoryController historyController) {
        this.historyViewModel = viewModel;
        this.historyController = historyController;
        //this.removeFoodController = removeFoodController;
        this.historyViewModel.addPropertyChangeListener(this);
        this.viewingDate = LocalDate.now();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        build();
        historyController.execute(viewingDate, 0, username);
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
                // create Remove state and send it in Viewmodel manager (but there is no manager here)
                // or use Remove controller? to have exectue?

                // e.g. "Apple: ###.##(g/ml), ...
                String name;
                double weight;

                String input = list.getSelectedValue();

                //tester
                input = "Apple: 323.43(g/ml)";
                if (input != null) {
                    String[] split = input.split("(:)|(,)|(\\()");
                    name = split[0];
                    weight = Double.parseDouble(split[1]);
                } else {
                    name = "";
                    weight = 0.0;
                }

                historyController.removeHighlightedFood(name, weight, username, viewingDate.toString());
            }
        });

        return removeFoodButton;
    }

    private JButton createPrevDayButton() {
        // calls to controller
        prevButton = new JButton("Previous Day");
        prevButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyController.execute(viewingDate, -1, username);
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
                historyController.execute(viewingDate, 1, username);
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
        listModel.addElement("Hello3");

        list = new JList<>(listModel);
        list.setFixedCellWidth(300);

        list.addListSelectionListener(new ListSelectionListener() {

            // dont even need to always check
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String input = list.getSelectedValue();
                if (input != null) {
                    String[] split = input.split(":");
                    highlightedFood = split[0];
                    System.out.println(highlightedFood);
                } else {
                    highlightedFood = "";
                }

            }
        });
        return list;
    }

    public void build() {
        setHeaderPanel();
        setDataPanel();
    }

    public String getViewName() {
        return viewName;
    }

    public static void main(String[] args) {
        final JFrame application = new JFrame("Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        final CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);


        final HistoryViewModel historyViewModel = new HistoryViewModel();
        final RemoveFoodViewModel removeFoodViewModel = new RemoveFoodViewModel();

        final TempHistoryDAO historyDAO = new TempHistoryDAO();
        final TempHistoryDAO removeFoodDAO = new TempHistoryDAO(); // have its own

        //final HistoryView historyView = HistoryUseCaseFactory.create(viewManagerModel, historyViewModel, historyDAO);
        //final RemoveFoodView removeFoodView = RemoveFoodUseCaseFactory.create(viewManagerModel, removeFoodViewModel, removeFoodDAO);

        final HistoryOutputBoundary historyOutputBoundary = new HistoryPresenter(historyViewModel, removeFoodViewModel, viewManagerModel);
        final HistoryInputBoundary historyInteractor = new HistoryInteractor(historyOutputBoundary, historyDAO);
        final HistoryController historyController = new HistoryController(historyInteractor);
        HistoryView historyView = new HistoryView(historyViewModel, historyController);

        final RemoveFoodOutputBoundary removeFoodOutputBoundary = new RemoveFoodPresenter(removeFoodViewModel, viewManagerModel, historyViewModel);
        final RemoveFoodInputBoundary removeFoodInteractor = new RemoveFoodInteractor(removeFoodOutputBoundary, historyDAO);
        final RemoveFoodController removeFoodController = new RemoveFoodController(removeFoodInteractor);
        RemoveFoodView removeFoodView = new RemoveFoodView(removeFoodViewModel, removeFoodController);

        //========================================================================================

        views.add(historyView, historyView.getViewName());
        views.add(removeFoodView, removeFoodView.getViewName());

        viewManagerModel.setState(historyView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final HistoryState state = (HistoryState) evt.getNewValue();
        username = state.getUsername();
        if (state.getHistoryError().isEmpty()) {
            if (!state.getCompleted()) {
                historyController.execute(state.getViewingDate(), 0, state.getUsername());
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
