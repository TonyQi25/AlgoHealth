package data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayInfo {
    private final LocalDate date;
    private float totalCalories;
    private List<Food> foodLog;

    public DayInfo(LocalDate date) {
        this.date = date;
        totalCalories = 0;
        foodLog = new ArrayList<>();
    }

    public LocalDate getDate() {
        return this.date;
    }

    public float getTotalCalories() {
        return this.totalCalories;
    }

    public List<Food> getFoodLog() {
        return foodLog;
    }

    public void addToFoodLog(Food newFood) {  // do we wanna use a setter for this? maybe that would be better
        this.foodLog.add(newFood);
    }

    public void setTotalCalories() {
        float totalCalories = 0;
        for (Food foodItem: this.foodLog){
            totalCalories += foodItem.getTotalCalories();
        }
        this.totalCalories = totalCalories;
    }

    public void setFoodLog(List<Food> foodLog) {
        this.foodLog = foodLog;
    }
}