package use_case.food_logging;

/**
 * The output boundary for the Log Food Use Case.
 */

public interface LogFoodOutputBoundary {

    /**
     * Prepares the success view for the Log Food Use Case.
     * @param outputData the output data
     */
    void prepareLogFoodView(LogFoodOutputData outputData);

}
