package interface_adapter.food_logging;

import java.util.HashMap;

/**
 * The state for the Log Food View Model.
 */
public class LogFoodState {
    private String foodName = "";
    private double weightNumber = 0;
    private String weightUnit = "";

    private HashMap<String, Integer> foodOptionsMap;
    private int fdcIDofSelection;
    private String foodSearchInput;

    private double totalCalories;
    private double totalProtein;
    private double totalCarbs;
    private double totalFat;

    private String username;
    private String password;

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

    public void setWeightNumber(double weightNumber) {
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

    @Override
    public String toString() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
