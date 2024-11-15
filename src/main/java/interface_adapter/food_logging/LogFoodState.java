package interface_adapter.food_logging;

public class LogFoodState {
    private String foodName = "";
    private double weightNumber = 0;
    private String weightUnit = "";

    public String getFoodName() {
        return foodName;
    }

    public double getWeightNumber() {
        return weightNumber;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setWeightNumber(double weightNumber) {
        this.weightNumber = weightNumber;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String toString(){
        return "LogFoodState{"
                + "foodName='" + foodName + '\''
                + ", foodWeight='" + weightNumber + '\''
                + ", weightUnit='" + weightUnit + '\''
                + '}';
    }
}
