package interface_adapter.food_logging;

import interface_adapter.ViewModel;

public class LogFoodViewModel extends ViewModel<LogFoodState> {
    public LogFoodViewModel(){
        super("log food");
        setState(new LogFoodState());
    }
}
