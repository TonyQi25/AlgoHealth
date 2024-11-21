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
    private final LocalDate date;

    public HistoryController(HistoryInputBoundary historyUseCaseInteractor, LocalDate date) {
        this.historyUseCaseInteractor = historyUseCaseInteractor;
        this.date = date;
    }

    public void execute(JFrame historyFrame, LocalDate date) {
        final HistoryInputData historyInputData = new HistoryInputData(date);



    }
}
