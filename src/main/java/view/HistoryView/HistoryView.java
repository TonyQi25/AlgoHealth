package view.HistoryView;

import interface_adapter.history.HistoryController;
import use_case.history.HistoryInputData;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;

import javax.swing.*;
import java.time.LocalDate;

public class HistoryView {

    private HistoryViewBuilder historyViewBuilder;
    private HistoryController historyController;

    public HistoryView(HistoryViewBuilder historyViewBuilder) {

        this.historyViewBuilder = historyViewBuilder;

    }

    public void generateHistoryView() {
        JFrame historyframe = historyViewBuilder.build();
        historyframe.pack();
        historyframe.setVisible(true);
    }

    public static void main(String[] args) {
        //main
        HistoryInputData input = new HistoryInputData(LocalDate.now());
        HistoryOutputData output = new HistoryOutputData();
        HistoryView hv = new HistoryView(new HistoryViewBuilder(new JPanel(), new JFrame()));
        hv.generateHistoryView();


    }
}
