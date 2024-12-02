package use_case.removeFood;

import use_case.history.HistoryInputData;

/**
 * input boundary for remove food use case
 */
public interface RemoveFoodInputBoundary {

    /**
     * executes the use case
     * @param input input
     */
    void execute(RemoveFoodInputData input);

    /**
     * returns to history view
     * @param input input
     */
    void returnToHistory(HistoryInputData input);
}
