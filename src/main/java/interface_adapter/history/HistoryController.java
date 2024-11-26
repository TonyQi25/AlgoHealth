package interface_adapter.history;

import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInputData;

import java.time.LocalDate;

public class HistoryController {

    private final HistoryInputBoundary historyUseCaseInteractor;

    public HistoryController(HistoryInputBoundary historyUseCaseInteractor) {
        this.historyUseCaseInteractor = historyUseCaseInteractor;
    }

    public void execute(LocalDate viewingDate, int offset, String username) {
        final HistoryInputData input;
        if (offset == -1) {
            input = new HistoryInputData(viewingDate.minusDays(1), username);
        }   else if (offset == 1) {
            input = new HistoryInputData(viewingDate.plusDays(1), username);
        }   else {
            input = new HistoryInputData(viewingDate, username);
        }

        historyUseCaseInteractor.execute(input);
    }
}
