package interface_adapter.food_logging;

import java.util.ArrayList;

public class LogFoodState {
    private String foodName = "";
    private float weightNumber = 0;
    private String weightUnit = "";

    private float totalCalories;
    private float totalProtein;
    private float totalCarbs;
    private float totalFat;

    public String getFoodName() {
        return foodName;
    }

    public double getWeightNumber() {
        return weightNumber;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public float getTotalCalories() {
        return totalCalories;
    }

    public float getTotalProtein() {
        return totalProtein;
    }

    public float getTotalCarbs() {
        return totalCarbs;
    }

    public float getTotalFat() {
        return totalFat;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setWeightNumber(float weightNumber) {
        this.weightNumber = weightNumber;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public void setTotalCalories(float totalCalories) {
        this.totalCalories = totalCalories;
    }

    public void setTotalProtein(float totalProtein) {
        this.totalProtein = totalProtein;
    }

    public void setTotalCarbs(float totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public void setTotalFat(float totalFat) {
        this.totalFat = totalFat;
    }

    public String toString(){
        return "LogFoodState{"
                + "foodName='" + foodName + '\''
                + ", foodWeight='" + weightNumber + '\''
                + ", weightUnit='" + weightUnit + '\''
                + '}';
    }
}
