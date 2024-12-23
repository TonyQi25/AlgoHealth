package data;

import java.util.HashMap;

public class Food {
    private String description;
    private String standardUnit;
    private double weight;
    private HashMap<String, Object> calories;
    private HashMap<String, HashMap<String, Object>> microNutrients;
    private HashMap<String, HashMap<String, Object>> macroNutrients;
    private double totalCalories;
    private double totalProtein;

    private double totalCarb;
    private double totalFat;

    public Food() {
        description = "";
        weight = 0;
        calories = new HashMap<>();
        microNutrients = new HashMap<>();
        macroNutrients = new HashMap<>();
    }

    public Food(String name, double weight, HashMap<String, Object> calories) {
        this.description = name;
        this.weight = weight;
        this.calories = calories;
        this.microNutrients = new HashMap<>();  // could also just put in the nutrients right from the constructor
        this.macroNutrients = new HashMap<>();  // depends on how API calls work! Check back after API calls do smth
    }

    public String getDescription() {
        return this.description;
    }

    public double getWeight() {
        return this.weight;
    }

    public HashMap<String, Object> getCalories() {
        return this.calories;
    }

    public HashMap<String, HashMap<String, Object>> getMicroNutrients() {
        return this.microNutrients;
    }

    public HashMap<String, HashMap<String, Object>> getMacroNutrients() {
        return this.macroNutrients;
    }

    public void setCalories(HashMap<String, Object> calories) {
        this.calories = calories;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMacroNutrients(HashMap<String, HashMap<String, Object>> macroNutrients) {
        this.macroNutrients = macroNutrients;
    }

    public void setMicroNutrients(HashMap<String, HashMap<String, Object>> microNutrients) {
        this.microNutrients = microNutrients;
    }

    public void setStandardUnit(String standardUnit) {
        this.standardUnit = standardUnit;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStandardUnit() {
        return standardUnit;
    }

    public void setTotalCalories() {
        this.totalCalories = (double) calories.get("amount per 100")/100*this.weight;
    }

    public void setTotalProtein() {
        this.totalProtein = (double) this.getMacroNutrients().get("Protein").get(
                "amount per 100")/100*this.weight;
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
        this.totalFat = (double) this.getMacroNutrients().get("Fat").get(
                "amount per 100")/100*this.weight;
    }

    public double getTotalFat() {
        return totalFat;
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

    public void setNutritionFacts(HashMap<String, HashMap<String, Object>> microNutrients,
                                   HashMap<String, HashMap<String, Object>> macroNutrients){
        this.setMacroNutrients(macroNutrients);
        this.setMicroNutrients(microNutrients);
        this.setTotalProtein();
        this.setTotalFat();
        this.setTotalCalories();
        this.setTotalCarb();
    }
}