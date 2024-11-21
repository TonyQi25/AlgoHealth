package interface_adapter.history;

import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInputData;
import java.time.LocalDate;

import javax.swing.*;

public class HistoryController {

    private final HistoryInputBoundary historyUseCaseInteractor;
    private final LocalDate date;

    public HistoryController(HistoryInputBoundary historyUseCaseInteractor, LocalDate date) {
        this.historyUseCaseInteractor = historyUseCaseInteractor;
        this.date = date;
    }

    public void execute(JFrame historyFrame, LocalDate date) {
        final HistoryInputData historyInputData = new HistoryInputData(historyFrame, date);
        //historyUseCaseInteractor.execute(historyInputData);
    }
}
