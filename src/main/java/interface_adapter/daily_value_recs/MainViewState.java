package interface_adapter.daily_value_recs;

import java.util.HashMap;

public class MainViewState {

    private double percent_cals = 0;
    private double percent_prot = 0;
    private double percent_carbs = 0;
    private double percent_fat = 0;

    private double calories = 0;
    private double protein = 0;
    private double carbs = 0;
    private double fat = 0;

    private HashMap<String, Integer> foodOptionsMap;
    private int fdcIDofSelection;

    private String foodSearchInput;

    public double getPercent_cals() {
        return percent_cals;
    }

    public void setPercent_cals(double percent_cals) {
        this.percent_cals = percent_cals;
    }

    public double getPercent_prot() {
        return percent_prot;
    }

    public void setPercent_prot(double percent_prot) {
        this.percent_prot = percent_prot;
    }

    public double getPercent_carbs() {
        return percent_carbs;
    }

    public void setPercent_carbs(double percent_carbs) {
        this.percent_carbs = percent_carbs;
    }

    public double getPercent_fat() {
        return percent_fat;
    }

    public void setPercent_fat(double percent_fat) {
        this.percent_fat = percent_fat;
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

    public int getFdcIDofSelection() {
        return fdcIDofSelection;
    }

    public void setFdcIDofSelection(int fdcIDofSelection) {
        this.fdcIDofSelection = fdcIDofSelection;
    }

}
