package interface_adapter.food_logging;
import interface_adapter.ViewManagerModel;
import use_case.food_logging.LogFoodOutputData;
import use_case.food_logging.LogFoodOutputBoundary;

public class LogFoodPresenter implements LogFoodOutputBoundary {
    private final LogFoodViewModel foodLogViewModel;
    private final ViewManagerModel viewManagerModel;

    public LogFoodPresenter(LogFoodViewModel foodLogView, ViewManagerModel viewManagerModel) {
        this.foodLogViewModel = foodLogView;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareLogFoodView(LogFoodOutputData logFoodOutputData){
        final LogFoodState logFoodState = foodLogViewModel.getState();
        logFoodState.setFoodName(logFoodOutputData.getFoodName());
        logFoodState.setWeightNumber(logFoodOutputData.getFoodWeight());
        logFoodState.setWeightUnit(logFoodOutputData.getWeightUnit());
        logFoodState.setTotalCarbs((float)logFoodOutputData.getCarbs().get(0));
        logFoodState.setTotalCalories((float) logFoodOutputData.getCalories().get(0));
        logFoodState.setTotalFat((float) logFoodOutputData.getFat().get(0));
        logFoodState.setTotalProtein((float) logFoodOutputData.getProtein().get(0));
        this.foodLogViewModel.setState(logFoodState);
        this.foodLogViewModel.firePropertyChanged();
        this.viewManagerModel.setState(foodLogViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
