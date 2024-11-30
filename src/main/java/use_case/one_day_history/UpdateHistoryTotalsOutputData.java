package use_case.one_day_history;

public class UpdateHistoryTotalsOutputData {

    private double calories;
    private double protein;
    private double carbs;
    private double fat;

    private double recCalories;
    private double recProtein;
    private double recCarbs;
    private double recFat;

    public UpdateHistoryTotalsOutputData(double calories, double protein, double carbs, double fat,
                                         double recCalories, double recCarbs, double recFat, double recProtein ) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.recCalories = recCalories;
        this.recCarbs = recCarbs;
        this.recProtein = recProtein;
        this.recFat = recFat;
    }

    public double getCalories() {
        return calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFat() {
        return fat;
    }

    public double getProtein() {
        return protein;
    }

    public double getRecCalories() {
        return recCalories;
    }

    public double getRecCarbs() {
        return recCarbs;
    }

    public double getRecFat() {
        return recFat;
    }

    public double getRecProtein() {
        return recProtein;
    }
}
