package use_case.food_logging;

import java.util.ArrayList;

public class LogFoodOutputData {
    private final String foodName;

    private final float foodWeight;
    private final String weightUnit;

    private final ArrayList<Object> calories;
    private final ArrayList<Object> protein;
    private final ArrayList<Object> carbs;
    private final ArrayList<Object> fat;

    //output food name and calories calculated based on amount consumed.
    public LogFoodOutputData(String foodName, float foodWeight, String weightUnit, ArrayList<Object> calories,
                             ArrayList<Object> protein, ArrayList<Object> carbs, ArrayList<Object> fat) {
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.weightUnit = weightUnit;
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

    public float getFoodWeight() {
        return foodWeight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }
}
