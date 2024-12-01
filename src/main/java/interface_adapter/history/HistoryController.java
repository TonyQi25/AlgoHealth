package interface_adapter.history;

import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInputData;
import use_case.removeFood.RemoveFoodInputData;

import java.time.LocalDate;

public class HistoryController {

    private final HistoryInputBoundary historyUseCaseInteractor;

    public HistoryController(HistoryInputBoundary historyUseCaseInteractor) {
        this.historyUseCaseInteractor = historyUseCaseInteractor;
    }

    public void execute(LocalDate viewingDate, int offset, String username, String password) {
        final HistoryInputData input;
        if (offset == -1) {
            input = new HistoryInputData(viewingDate.minusDays(1), username, password);
        }   else if (offset == 1) {
            input = new HistoryInputData(viewingDate.plusDays(1), username, password);
        }   else {
            input = new HistoryInputData(viewingDate, username, password);
        }

        historyUseCaseInteractor.execute(input);
    }

    public void removeHighlightedFood(String line, String username, String viewingDate, String password) {

        String name;
        double weight;

        if (line != null) {
            String[] split = line.split("(:)|(,)|(\\()|(\\))");
            name = split[0];
            weight = Double.parseDouble(split[1]);
        } else {
            name = "";
            weight = 0.0;
        }

        final RemoveFoodInputData input = new RemoveFoodInputData(name, weight, username, viewingDate, password);

        historyUseCaseInteractor.removeHighlightedFood(input);
    }
}
