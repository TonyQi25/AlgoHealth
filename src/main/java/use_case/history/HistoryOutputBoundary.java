package use_case.history;

import use_case.one_day_history.UpdateHistoryTotalsInputData;
import use_case.removeFood.RemoveFoodInputData;
import use_case.removeFood.RemoveFoodInteractor;

/**
 * output boundary for history use case
 */
public interface HistoryOutputBoundary {
    /**
     * prepare success view for history use case
     * @param data output data
     */
    void prepareSuccessView(HistoryOutputData data);

    /**
     * prepares fail view for history use case
     * @param errorMessage error message
     */
    void prepareFailView(String errorMessage);

    /**
     * prepares remove food use case
     * @param input input data
     */
    void prepareRemoveFoodView(RemoveFoodInputData input);

    /**
     * returns to main view
     */
    void prepareMainView();

    /**
     * runs view one day use case
     * @param input input data
     */
    void viewOneDay(UpdateHistoryTotalsInputData input);
}
