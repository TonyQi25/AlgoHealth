package interface_adapter.food_logging;
import interface_adapter.ViewManagerModel;
import interface_adapter.daily_value_recs.MainViewModel;
import interface_adapter.daily_value_recs.MainViewState;
import use_case.food_logging.LogFoodOutputData;
import use_case.food_logging.LogFoodOutputBoundary;

public class LogFoodPresenter implements LogFoodOutputBoundary {
    private final LogFoodViewModel foodLogViewModel;
    private final MainViewModel mainViewModel;
    private final ViewManagerModel viewManagerModel;

    public LogFoodPresenter(LogFoodViewModel foodLogView, MainViewModel mainViewModel, ViewManagerModel viewManagerModel) {
        this.foodLogViewModel = foodLogView;
        this.mainViewModel = mainViewModel;
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

        final MainViewState mainViewState = mainViewModel.getState();
        mainViewState.setCalories(logFoodState.getTotalCalories());
        mainViewState.setProtein(logFoodState.getTotalProtein());
        mainViewState.setCarbs(logFoodState.getTotalCarbs());
        mainViewState.setFat(logFoodState.getTotalFat());

        this.foodLogViewModel.setState(logFoodState);
        this.foodLogViewModel.firePropertyChanged();
        this.viewManagerModel.setState(foodLogViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
