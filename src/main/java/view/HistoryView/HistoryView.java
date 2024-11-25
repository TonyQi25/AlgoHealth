package view.HistoryView;

import app.HistoryUseCaseFactory;
import data_access.TempHistoryDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;
import view.ViewManager;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class HistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "history";
    private final HistoryViewModel historyViewModel;

    private JPanel headerPanel;
    private JPanel dataPanel;

    private LocalDate viewingDate;
    private JList<String> list;
    private JLabel dateLabel;
    private JLabel errorLabel;

    private JButton prevButton;
    private JButton nextButton;

    private HistoryController historyController;

    public HistoryView(HistoryViewModel viewModel, HistoryController historyController) {
        this.historyViewModel = viewModel;
        this.historyController = historyController;
        this.historyViewModel.addPropertyChangeListener(this);

        resetDate();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        build();
        historyController.execute(viewingDate, 0);
    }


    public void resetDate() {
        this.viewingDate = LocalDate.now();
    }

    public void loadInfo(List<String> info) {

    }

    public void setHeaderPanel() {
        headerPanel = new JPanel();
        dateLabel = new JLabel("Date: " + this.viewingDate.toString());
        errorLabel = new JLabel("");
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.add(getPrevDayButton(viewingDate.minusDays(1)));
        this.add(errorLabel);
        headerPanel.add(dateLabel);
        headerPanel.add(getNextDayButton(viewingDate.plusDays(1)));
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.add(headerPanel);

    }

    public JButton getPrevDayButton(LocalDate date) {
        return createPrevDayButton(date);
    }

    private JButton createPrevDayButton(LocalDate date) {
        // calls to controller
        prevButton = new JButton("Previous Day");
        prevButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyController.execute(viewingDate, -1);
            }
        });
        return prevButton;
    }

    public JButton getNextDayButton(LocalDate date) {
        return createNextDayButton(date);
    }

    private JButton createNextDayButton(LocalDate date) {
        nextButton = new JButton("Next Day");
        nextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        nextButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                historyController.execute(viewingDate, 1);
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
        this.add(dataPanel);
    }

    public JList<String> createDataList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Hello3");

        list = new JList<>(listModel);
        list.setFixedCellWidth(300);
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
        final TempHistoryDAO historyDAO = new TempHistoryDAO();

        final HistoryView historyView = HistoryUseCaseFactory.create(viewManagerModel, historyViewModel, historyDAO);

        views.add(historyView, historyView.getViewName());

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
        if (state.getHistoryError().isEmpty()) {
            setFields(state);
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
