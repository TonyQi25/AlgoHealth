package use_case.food_logging;

/**
 * The Input Data for the Log Food Use Case.
 */

public class LogFoodInputData {

    private final Integer fdcId;
    private final double foodWeight;
    private final String weightUnit;

    private final String username;
    private final String password;

    public LogFoodInputData(Integer fdcId, double foodWeight, String weightUnit, String username, String password) {
        this.fdcId = fdcId;
        this.foodWeight = foodWeight;
        this.weightUnit = weightUnit;
        this.username = username;
        this.password = password;
    }

    public double getFoodWeight() {
        return foodWeight;
    }

    public String getUsername() { return username; }

    public String getPassword(){ return password; }
}
