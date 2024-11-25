package view.HistoryView;

import data_access.TempHistoryDAO;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryPresenter;
import interface_adapter.history.HistoryViewModel;
import use_case.history.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class HistoryView {
    ;
    private HistoryController historyController;

    private final JFrame historyFrame;
    private final JPanel historyPanel;
    private JPanel headerPanel;
    private JPanel dataPanel;
    private LocalDate viewingDate;

    public HistoryView(HistoryViewModel viewModel, HistoryController historyController) {
        this.historyPanel = new JPanel();
        this.historyFrame = new JFrame();
        resetDate();
        this.historyPanel.setLayout(new BoxLayout(this.historyPanel, BoxLayout.Y_AXIS));
        this.historyFrame.setLayout(new BoxLayout(this.historyFrame.getContentPane(), BoxLayout.Y_AXIS));


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
        historyPanel.add(headerPanel);

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
                historyController.execute(historyFrame, viewingDate, -1);
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
                historyController.execute(historyFrame, viewingDate, 1);
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
        historyFrame.add(dataPanel);
    }

    public JList<String> createDataList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Hello3");

        JList<String> list = new JList<>(listModel);
        list.setFixedCellWidth(300);
        return list;
    }

    public JFrame build() {
        historyFrame.setContentPane(historyPanel);
        historyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setHeaderPanel();
        setDataPanel();
        return historyFrame;
    }

    public static void main(String[] args) {
        //main
        HistoryInputData input = new HistoryInputData(LocalDate.now());
        HistoryOutputData output = new HistoryOutputData();
        HistoryViewModel viewModel = new HistoryViewModel("history view");
        HistoryPresenter presenter = new HistoryPresenter(viewModel, output);
        HistoryDataAccessInterface historyDAO = new TempHistoryDAO();
        HistoryInteractor useCaseInteractor = new HistoryInteractor(input, presenter, output, historyDAO);
        HistoryController controller = new HistoryController(useCaseInteractor, input);
        HistoryView hv = new HistoryView(viewModel, controller);

        JFrame history = hv.build();
        history.setVisible(true);
        history.setSize(800, 600);



    }


}
