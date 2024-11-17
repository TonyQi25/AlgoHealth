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
        logFoodState.setTotalCarbs(Double.valueOf((String) logFoodOutputData.getCarbs().get(0)));
        logFoodState.setTotalCalories(Double.valueOf((String) logFoodOutputData.getCalories().get(0)));
        logFoodState.setTotalFat(Double.valueOf((String)logFoodOutputData.getFat().get(0)));
        logFoodState.setTotalProtein(Double.valueOf((String) logFoodOutputData.getProtein().get(0)));
        this.foodLogViewModel.setState(logFoodState);
        this.foodLogViewModel.firePropertyChanged();
        this.viewManagerModel.setState(foodLogViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
