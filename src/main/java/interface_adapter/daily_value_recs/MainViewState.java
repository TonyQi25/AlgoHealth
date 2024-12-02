package interface_adapter.daily_value_recs;

import java.util.HashMap;

public class MainViewState {

    private String username = "";
    private String password = "";

    private double percentCals;
    private double percentProt;
    private double percentCarbs;
    private double percentFat;

    private double calories;
    private double protein;
    private double carbs;
    private double fat;

    private HashMap<String, Integer> foodOptionsMap;
    private int fdcIdOfSelection;

    private String foodSearchInput;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getPercentCals() {
        return percentCals;
    }

    public void setPercentCals(double percentCals) {
        this.percentCals = percentCals;
    }

    public double getPercentProt() {
        return percentProt;
    }

    public void setPercentProt(double percentProt) {
        this.percentProt = percentProt;
    }

    public double getPercentCarbs() {
        return percentCarbs;
    }

    public void setPercentCarbs(double percentCarbs) {
        this.percentCarbs = percentCarbs;
    }

    public double getPercentFat() {
        return percentFat;
    }

    public void setPercentFat(double percentFat) {
        this.percentFat = percentFat;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setFoodSearchInput(String foodSearchInput) {
        this.foodSearchInput = foodSearchInput;
    }

    public String getFoodSearchInput() {
        return foodSearchInput;
    }

    public HashMap<String, Integer> getFoodOptionsMap() {
        return foodOptionsMap;
    }

    public void setFoodOptionsMap(HashMap<String, Integer> foodOptionsMap) {
        this.foodOptionsMap = foodOptionsMap;
    }

    public int getFdcIdOfSelection() {
        return fdcIdOfSelection;
    }

    public void setFdcIdOfSelection(int fdcIdOfSelection) {
        this.fdcIdOfSelection = fdcIdOfSelection;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
