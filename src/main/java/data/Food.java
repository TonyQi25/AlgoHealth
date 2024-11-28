package data;

import helpers.HashMapFromString;
import org.json.JSONObject;

import java.util.HashMap;

public class Food {
    private String description;
    private String standardUnit;
    private float weight;
    private HashMap<String, Object> calories;
    private HashMap<String, HashMap<String, Object>> microNutrients;
    private HashMap<String, Object> protein;
    private HashMap<String, Object> carbs;
    private HashMap<String, Object> fat;

    public double getTotalFat() {
        return totalFat;
    }

    private double totalCalories;
    private double totalProtein;

    private double totalCarb;
    private double totalFat;

    public Food() {
        this.description = "";
        this.weight = 0;
        this.calories = new HashMap<>();
        this.microNutrients = new HashMap<>();
        this.protein = new HashMap<>();
        this.carbs = new HashMap<>();
        this.fat = new HashMap<>();
    }

    public Food(String name, float weight, HashMap<String, Object> calories) {
        this.description = name;
        this.weight = weight;
        this.calories = calories;
        this.microNutrients = new HashMap<>();  // could also just put in the nutrients right from the constructor
        this.protein = new HashMap<>();  // depends on how API calls work! Check back after API calls do smth
        this.carbs = new HashMap<>();
        this.fat = new HashMap<>();
    }

    public String getDescription() {
        return this.description;
    }

    public float getWeight() {
        return this.weight;
    }

    public HashMap<String, Object> getCalories() {
        return this.calories;
    }

    public HashMap<String, HashMap<String, Object>> getMicroNutrients() {
        return this.microNutrients;
    }

    public HashMap<String, Object> getProtein() {
        return this.protein;
    }

    public HashMap<String, Object> getCarbs() {
        return carbs;
    }

    public HashMap<String, Object> getFat() {
        return fat;
    }

    public void setCalories(HashMap<String, Object> calories) {
        this.calories = calories;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMicroNutrients(HashMap<String, HashMap<String, Object>> microNutrients) {
        this.microNutrients = microNutrients;
    }

    public void setProtein(HashMap<String, Object> protein) {
        this.protein = protein;
    }

    public void setCarbs(HashMap<String, Object> carbs) {
        this.carbs = carbs;
    }

    public void setFat(HashMap<String, Object> fat) {
        this.fat = fat;
    }

    public void setStandardUnit(String standardUnit) {
        this.standardUnit = standardUnit;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getStandardUnit() {
        return standardUnit;
    }

    public void setTotalCalories() {
        this.totalCalories = (double) calories.get("amount per 100")/100*this.weight;
    }

    public void setTotalProtein() {
        this.totalProtein = (double) this.getProtein().get("amount per 100")/100*this.weight;
    }

    public void setTotalCarb() {
        if (this.getMacroNutrients().containsKey("Carbohydrate")) {
            this.totalCarb = (double) this.getMacroNutrients().get("Carbohydrate").get(
                    "amount per 100") / 100 * this.weight;
        }
        else if (this.getMacroNutrients().containsKey("Starch")) {
            this.totalCarb = (double) this.getMacroNutrients().get("Starch").get(
                    "amount per 100") / 100 * this.weight;
        }
    }

    public void setTotalFat() {
        this.totalFat = (double) this.getFat().get("amount per 100")/100*this.weight;
    }

    public double getTotalCalories() {
        return totalCalories;
    }

    public double getTotalProtein() {
        return totalProtein;
    }

    public double getTotalCarb() {
        return totalCarb;
    }

    public String toString() {
        JSONObject foodStringObject = new JSONObject();

        foodStringObject.put("description", this.getDescription());
        foodStringObject.put("standard unit", this.getStandardUnit());
        foodStringObject.put("weight", this.getWeight());
        foodStringObject.put("calories", this.getCalories());
        foodStringObject.put("micronutrients", this.getMicroNutrients());
        foodStringObject.put("protein", this.getProtein());
        foodStringObject.put("carbs", this.getCarbs());
        foodStringObject.put("fat", this.getFat());

        return foodStringObject.toString();
    }

    public static Food fromString(String foodString) {
        Food newFood = new Food();
        JSONObject foodObject = new JSONObject(foodString);

        newFood.setDescription((String) foodObject.get("description"));
        newFood.setStandardUnit((String) foodObject.get("standard unit"));
        newFood.setWeight((float) foodObject.get("weight"));

        HashMap<String, Object> calories = HashMapFromString.hashMapFromString((String) foodObject.get("calories"));
        newFood.setCalories(calories);

        newFood.setMicroNutrients(new HashMap<>());

        HashMap<String, Object> protein = HashMapFromString.hashMapFromString((String) foodObject.get("protein"));
        newFood.setProtein(protein);

        HashMap<String, Object> carbs = HashMapFromString.hashMapFromString((String) foodObject.get("carbs"));
        newFood.setProtein(carbs);

        HashMap<String, Object> fat = HashMapFromString.hashMapFromString((String) foodObject.get("fat"));
        newFood.setProtein(fat);

        return newFood;
    }
}