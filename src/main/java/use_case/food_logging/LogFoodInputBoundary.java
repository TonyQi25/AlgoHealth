package use_case.food_logging;

/**
 * Input Boundary for food logging.
 */
public interface LogFoodInputBoundary {

    /**
     * Executes the Log Food use case.
     * @param logFoodInputData the input data
     */
    void execute(LogFoodInputData logFoodInputData);
}
