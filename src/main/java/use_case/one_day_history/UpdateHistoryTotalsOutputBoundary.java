package use_case.one_day_history;

/**
 * output boundary for view one day use case
 */
public interface UpdateHistoryTotalsOutputBoundary {
    /**
     * prepares success view for view one day use case
     * @param output output data
     */
    void prepareSuccessView(UpdateHistoryTotalsOutputData output);

    /**
     * returns to history view
     */
    void switchToHistory();
}
