package interface_adapter.history;

import use_case.history.HistoryInputBoundary;
import use_case.history.HistoryInputData;
import use_case.one_day_history.UpdateHistoryTotalsInputData;
import use_case.removeFood.RemoveFoodInputData;

import java.time.LocalDate;

/**
 * history use case controller
 */
public class HistoryController {

    private final HistoryInputBoundary historyUseCaseInteractor;

    public HistoryController(HistoryInputBoundary historyUseCaseInteractor) {
        this.historyUseCaseInteractor = historyUseCaseInteractor;
    }

    /**
     * executing use case.
     * @param viewingDate date to be viewed
     * @param offset offset of the day to be viewed
     * @param username user's username
     * @param password user's password
     */
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

    /**
     * executing remove food use case
     * @param line the input line selected from the JList
     * @param username username
     * @param viewingDate date to remove from
     * @param password password
     */
    public void removeHighlightedFood(String line, String username, String viewingDate, String password) {

        String name;
        double weight;

        if (line != null) {
            String[] split = line.split("(:)|(\\()");
            name = split[0];
            weight = Double.parseDouble(split[1]);
        } else {
            name = "";
            weight = 0.0;
        }

        final RemoveFoodInputData input = new RemoveFoodInputData(name, weight, username, viewingDate, password);

        historyUseCaseInteractor.removeHighlightedFood(input);
    }

    /**
     * go back to main view
     */
    public void goBack(){
        historyUseCaseInteractor.goBack();
    }

    /**
     * go to one day history use case
     * @param username username
     * @param viewingDate the date to view
     * @param password password
     */
    public void viewOneDay(String username, String viewingDate, String password) {

        final UpdateHistoryTotalsInputData input = new UpdateHistoryTotalsInputData(username, viewingDate, password);

        historyUseCaseInteractor.viewOneDay(input);
    }
}
