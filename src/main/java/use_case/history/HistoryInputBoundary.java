package use_case.history;

import use_case.one_day_history.UpdateHistoryTotalsInputData;
import use_case.removeFood.RemoveFoodInputData;

/**
 * input boundary for history use case
 */
public interface HistoryInputBoundary {
    /**
     * runs history use case
     * @param input input data
     */
    void execute(HistoryInputData input);

    /**
     * runs remove food use case
     * @param input input data
     */
    void removeHighlightedFood(RemoveFoodInputData input);

    /**
     * returns to main view
     */
    void goBack();

    /**
     * runs view one day use case
     * @param input input data
     */
    void viewOneDay(UpdateHistoryTotalsInputData input);
}
