package interface_adapter.one_day_history;

import java.util.HashMap;

public class UpdateHistoryTotalsState {

    private String username;
    private String password;
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


    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
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

    public double getPercentCalories() {
        return this.getCalories() / this.getRecCalories();
    }

    public double getPercentProtein() {
        return this.getProtein() / this.getRecProtein();
    }

    public double getPercentCarbs() {
        return this.getCarbs() / this.getRecCarbs();
    }

    public double getPercentFat() {
        return this.getFat() / this.getRecFat();
    }
}

