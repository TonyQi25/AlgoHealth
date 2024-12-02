package use_case.removeFood;

import use_case.history.HistoryInputData;

/**
 * output boundary for remove food use case
 */
public interface RemoveFoodOutputBoundary {

    /**
     * prepares success view for use case
     * @param data output data
     */
    void prepareSuccessView(RemoveFoodOutputData data);

    /**
     * prepares fail view for use case
     * @param errorMessage error message
     */
    void prepareFailView(String errorMessage);

    /**
     * prepares history view to return to
     * @param data input data for history use case
     */
    void prepareHistoryView(HistoryInputData data);
}
