package use_case.daily_value_recs;

public class DailyValueRecsIntakeData {

    private double calories;
    private double protein;
    private double carbs;
    private double fat;

    public DailyValueRecsIntakeData(double cals, double protein, double carbs, double fat) {
        this.calories = cals;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }


    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFat() {
        return fat;
    }
}
