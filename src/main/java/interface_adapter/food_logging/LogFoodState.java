package interface_adapter.food_logging;

import java.util.HashMap;

public class LogFoodState {
    private String foodName = "";
    private float weightNumber = 0;
    private String weightUnit = "";

    private HashMap<String, Integer> foodOptionsMap;
    private int fdcIDofSelection;
    private String foodSearchInput;

    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;

    public String getFoodName() {
        return foodName;
    }

    public double getWeightNumber() {
        return weightNumber;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public double getTotalFat() {
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

    public void setTotalCalories(double totalCalories) {
        this.totalCalories = totalCalories;
    }

    public void setTotalProtein(double totalProtein) {
        this.totalProtein = totalProtein;
    }

    public void setTotalCarbs(double totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public void setTotalFat(double totalFat) {
        this.totalFat = totalFat;
    }

    public String toString(){
        return "LogFoodState{"
                + "foodName='" + foodName + '\''
                + ", foodWeight='" + weightNumber + '\''
                + ", weightUnit='" + weightUnit + '\''
                + '}';
    }

    public HashMap<String, Integer> getFoodOptionsMap() {
        return foodOptionsMap;
    }

    public void setFoodOptionsMap(HashMap<String, Integer> foodOptionsMap) {
        this.foodOptionsMap = foodOptionsMap;
    }

    public int getFdcIDofSelection() {
        return fdcIDofSelection;
    }

    public void setFdcIDofSelection(int fdcIDofSelection) {
        this.fdcIDofSelection = fdcIDofSelection;
    }

    public String getFoodSearchInput() {
        return foodSearchInput;
    }

    public void setFoodSearchInput(String foodSearchInput) {
        this.foodSearchInput = foodSearchInput;
    }
}
