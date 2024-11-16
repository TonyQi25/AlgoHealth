/*package interface_adapter.food_logging;
import view.MainView.mainView;
import use_case.food_logging.LogFoodOutputData;
import use_case.food_logging.LogFoodOutputBoundary;

public class LogFoodPresenter implements LogFoodOutputBoundary {
    private final mainView foodLogView;

    public LogFoodPresenter(mainView foodLogView) {
        this.foodLogView = foodLogView;
    }

    public void prepareLogFoodView(LogFoodOutputData foodOutputData){
        foodLogView.setCalories(foodOutputData.getCalories());
        foodLogView.setFat(foodOutputData.getFat());
        foodLogView.setProtein(foodOutputData.getProtein());
        foodLogView.setCarbs(foodOutputData.getCarbs());
    }
}*/
