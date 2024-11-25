package view.HistoryView;

import app.HistoryUseCaseFactory;
import data_access.TempHistoryDAO;
import interface_adapter.ViewManagerModel;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryViewModel;
import use_case.history.*;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;

public class HistoryView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "history";
    private final HistoryViewModel historyViewModel;

    private JPanel headerPanel;
    private JPanel dataPanel;
    private LocalDate viewingDate;

    private HistoryController historyController;

    public HistoryView(HistoryViewModel viewModel, HistoryController historyController) {
        this.historyViewModel = viewModel;
        this.historyController = historyController;
        resetDate();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        build();
    }


    public void resetDate() {
        this.viewingDate = LocalDate.now();
    }

    public void loadInfo() {

    }

    public void setHeaderPanel() {
        headerPanel = new JPanel();
        JLabel dateLabel = new JLabel("Date: " + this.viewingDate.toString());
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.add(getPrevDayButton(viewingDate.minusDays(1)));
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
        JButton returning = new JButton("Previous Day");
        returning.setAlignmentX(Component.CENTER_ALIGNMENT);
        returning.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyController.execute(viewingDate, -1);
            }
        });
        return returning;
    }

    public JButton getNextDayButton(LocalDate date) {
        return createNextDayButton(date);
    }

    private JButton createNextDayButton(LocalDate date) {
        JButton returning = new JButton("Next Day");
        returning.setAlignmentX(Component.CENTER_ALIGNMENT);
        returning.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                historyController.execute(viewingDate, 1);
            }
        });
        return returning;
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

        JList<String> list = new JList<>(listModel);
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

//        //main
//        HistoryInputData input = new HistoryInputData(LocalDate.now());
//        HistoryOutputData output = new HistoryOutputData();
//        HistoryViewModel viewModel = new HistoryViewModel("history view");
//        HistoryPresenter presenter = new HistoryPresenter(viewModel, output);
//        HistoryDataAccessInterface historyDAO = new TempHistoryDAO();
//        HistoryInteractor useCaseInteractor = new HistoryInteractor(input, presenter, output, historyDAO);
//        HistoryController controller = new HistoryController(useCaseInteractor, input);
//        HistoryView hv = new HistoryView(viewModel, controller);

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

    }
}
