package interface_adapter.food_logging;
import use_case.food_logging.LogFoodOutputData;
import use_case.food_logging.LogFoodOutputBoundary;

public class LogFoodPresenter implements LogFoodOutputBoundary {
    private final LogFoodViewModel foodLogViewModel;

    public LogFoodPresenter(LogFoodViewModel foodLogView) {
        this.foodLogViewModel = foodLogView;
    }

    public void prepareLogFoodView(LogFoodOutputData logFoodOutputData){
        final LogFoodState logFoodState = foodLogViewModel.getState();
        logFoodState.setFoodName(logFoodOutputData.getFoodName());
        logFoodState.setWeightNumber(logFoodOutputData.getFoodWeight());
        logFoodState.setWeightUnit(logFoodOutputData.getWeightUnit());
    }
}
