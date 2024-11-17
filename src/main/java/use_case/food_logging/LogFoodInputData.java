package use_case.food_logging;

public class LogFoodInputData {

    private final Integer fdcId;
    private final float foodWeight;
    private final String weightUnit;

    public LogFoodInputData(Integer fdcId, float foodWeight, String weightUnit) {
        this.fdcId = fdcId;
        this.foodWeight = foodWeight;
        this.weightUnit = weightUnit;
    }


    public float getFoodWeight() {
        return foodWeight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public Integer getFdcId() {
        return fdcId;
    }
}
