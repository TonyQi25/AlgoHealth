package interface_adapter.history;

import data.DayInfo;
import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInputData;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class HistoryController {

    private final HistoryInputBoundary historyUseCaseInteractor;

    public HistoryController(HistoryInputBoundary historyUseCaseInteractor) {
        this.historyUseCaseInteractor = historyUseCaseInteractor;
    }

    public void execute(LocalDate viewingDate, int offset) {
        final HistoryInputData input;
        if (offset == -1) {
            input = new HistoryInputData(viewingDate.minusDays(1));
        }   else if (offset == 1) {
            input = new HistoryInputData(viewingDate.plusDays(1));
        }   else {
            input = new HistoryInputData(viewingDate);
        }

        historyUseCaseInteractor.execute(input);
    }
}
