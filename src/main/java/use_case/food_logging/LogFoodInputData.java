package use_case.food_logging;
import data.Food;

public class LogFoodInputData {

    private final String foodName;
    private final float foodWeight;
    private final String weightUnit;

    public LogFoodInputData(String foodName, float foodWeight, String weightUnit) {
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.weightUnit = weightUnit;
    }

    public String getFoodName() {
        return foodName;
    }

    public float getFoodWeight() {
        return foodWeight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }
}
