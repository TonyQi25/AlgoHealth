package interface_adapter.food_logging;
import data.Food;
import use_case.food_logging.LogFoodInputBoundary;
import use_case.food_logging.LogFoodInputData;

public class LogFoodController {
    private final LogFoodInputBoundary logFoodUseCaseInteractor;

    public LogFoodController(LogFoodInputBoundary logFoodUseCaseInteractor) {
        this.logFoodUseCaseInteractor = logFoodUseCaseInteractor;
    }

    public void execute(Integer fdcId, float foodWeight, String weightUnit){
        final LogFoodInputData foodInputData = new LogFoodInputData(fdcId, foodWeight, weightUnit);
        logFoodUseCaseInteractor.execute(foodInputData);
    }
}
