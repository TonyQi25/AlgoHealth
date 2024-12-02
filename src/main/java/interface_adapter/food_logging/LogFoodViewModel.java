package interface_adapter.food_logging;

import interface_adapter.ViewModel;
/**
 * The view model for the Log Food View Model.
 */

public class LogFoodViewModel extends ViewModel<LogFoodState> {
    public LogFoodViewModel() {
        super("log food");
        setState(new LogFoodState());
    }
}
