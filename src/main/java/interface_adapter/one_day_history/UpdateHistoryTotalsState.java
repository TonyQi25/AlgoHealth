package interface_adapter.one_day_history;

import java.util.HashMap;

public class UpdateHistoryTotalsState {

    private HashMap<String, Double> foodList;
    private String username;
    private String date;
    private boolean completed;

    private double calories;
    private double protein;
    private double carbs;
    private double fat;

    private double recCalories;
    private double recProtein;
    private double recCarbs;
    private double recFat;

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFoodList(HashMap<String, Double> foodList) {
        this.foodList = foodList;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public HashMap<String, Double> getFoodList() {
        return foodList;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setRecCalories(double recCalories) {
        this.recCalories = recCalories;
    }

    public void setRecCarbs(double recCarbs) {
        this.recCarbs = recCarbs;
    }

    public void setRecFat(double recFat) {
        this.recFat = recFat;
    }

    public void setRecProtein(double recProtein) {
        this.recProtein = recProtein;
    }

    public double getRecFat() {
        return recFat;
    }

    public double getRecCarbs() {
        return recCarbs;
    }

    public double getRecProtein() {
        return recProtein;
    }

    public double getCalories() {
        return calories;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getProtein() {
        return protein;
    }

    public double getRecCalories() {
        return recCalories;
    }
}

