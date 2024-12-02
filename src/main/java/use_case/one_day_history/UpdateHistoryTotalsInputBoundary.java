package use_case.one_day_history;

/**
 * input boundary for one day view use case
 */
public interface UpdateHistoryTotalsInputBoundary {

    /**
     * executes the use case
     * @param input input
     */
    void execute(UpdateHistoryTotalsInputData input);

    /**
     * go back to history view
     */
    void switchToHistory();
}
