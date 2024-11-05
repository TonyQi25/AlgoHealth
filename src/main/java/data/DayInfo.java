package data;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

class DayInfo {
    private final LocalDateTime date;
    private float totalCalories;
    private List<Food> foodLog;

    public DayInfo(LocalDateTime date) {
        this.date = date;
        totalCalories = 0;
        foodLog = new ArrayList<>();
    }

    public LocalDateTime getDate() {
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

    public void setTotalCalories(float newTotalCalories) {
        this.totalCalories = newTotalCalories;
    }
}