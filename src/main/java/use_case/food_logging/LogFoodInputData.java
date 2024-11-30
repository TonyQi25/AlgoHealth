package use_case.food_logging;

public class LogFoodInputData {

    private final Integer fdcId;
    private final float foodWeight;
    private final String weightUnit;

    private final String username;
    private final String password;

    public LogFoodInputData(Integer fdcId, float foodWeight, String weightUnit, String username, String password) {
        this.fdcId = fdcId;
        this.foodWeight = foodWeight;
        this.weightUnit = weightUnit;
        this.username = username;
        this.password = password;
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

    public String getUsername(){return username;}
    public String getPassword(){return password;}
}
