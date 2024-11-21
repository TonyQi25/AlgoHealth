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
    private final HistoryInputData historyInputData;

    public HistoryController(HistoryInputBoundary historyUseCaseInteractor, HistoryInputData inputData) {
        this.historyUseCaseInteractor = historyUseCaseInteractor;
        this.historyInputData = inputData;
    }

    public void execute(JFrame historyFrame) {

        //historyUseCaseInteractor.execute

    }
}
