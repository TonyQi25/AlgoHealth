package use_case.food_logging;
import data.Food;

public class LogFoodInputData {
    private final String food;

    public LogFoodInputData(String food) {
        this.food = food;
    }

    String getFood() {return food;}
}
