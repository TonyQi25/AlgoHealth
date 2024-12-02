package interface_adapter.food_logging;

import use_case.food_logging.LogFoodInputBoundary;
import use_case.food_logging.LogFoodInputData;
/**
 * The controller for the Log Food Use Case.
 */

public class LogFoodController {
    private final LogFoodInputBoundary logFoodUseCaseInteractor;

    public LogFoodController(LogFoodInputBoundary logFoodUseCaseInteractor) {
        this.logFoodUseCaseInteractor = logFoodUseCaseInteractor;
    }

    /**
     * Executes the Log Food Use Case.
     * @param fdcId the fdcID of the food that's being logged
     * @param foodWeight the weight of the food that's being logged
     * @param weightUnit the weight of the food that's being logged
     * @param username the username of the user currently logged in
     * @param password the password of the user currently logged in
     */

    public void execute(Integer fdcId, double foodWeight, String weightUnit, String username, String password) {
        final LogFoodInputData foodInputData = new LogFoodInputData(fdcId, foodWeight, weightUnit, username, password);
        logFoodUseCaseInteractor.execute(foodInputData);
    }
}
