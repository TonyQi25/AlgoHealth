package use_case.food_logging;

import java.util.ArrayList;

public class LogFoodOutputData {
    private final String foodName;

    private final ArrayList<Object> calories;
    private final ArrayList<Object> protein;
    private final ArrayList<Object> carbs;
    private final ArrayList<Object> fat;

    //output food name and calories calculated based on amount consumed.
    public LogFoodOutputData(String foodName, ArrayList<Object> calories, ArrayList<Object> protein,
                             ArrayList<Object> carbs, ArrayList<Object> fat) {
        this.foodName = foodName;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }
    public String getFoodName() {
        return foodName;
    }

    public ArrayList<Object> getCalories() {
        return calories;
    }

    public ArrayList<Object> getProtein() {
        return protein;
    }

    public ArrayList<Object> getCarbs() {
        return carbs;
    }

    public ArrayList<Object> getFat() {
        return fat;
    }
}
