package data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayInfo {
    private LocalDate date;
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTotalCalories(float newTotalCalories) {
        this.totalCalories = newTotalCalories;
    }

    public void setFoodLog(List<Food> foodLog) {
        this.foodLog = foodLog;
    }

    public String toString() {
        JSONObject dayStringObject = new JSONObject();

        dayStringObject.put("date", this.getDate());
        dayStringObject.put("total calories", this.getTotalCalories());
        dayStringObject.put("food log", this.getFoodLog());   // [{...}, {...}, {...}]

        return dayStringObject.toString();
    }

    public static DayInfo fromString(String dayString) {
        DayInfo newDay = new DayInfo(LocalDate.now());
        JSONObject dayObject = new JSONObject(dayString);

        newDay.setDate((LocalDate) dayObject.get("date"));
        newDay.setTotalCalories((float) dayObject.get("total calories"));

        JSONArray arrayFoodLog = new JSONArray((String) dayObject.get("food log"));
        for (Object food : arrayFoodLog) {
            Food newFood = Food.fromString(food.toString());
            newDay.addToFoodLog(newFood);
        }

        return newDay;
    }
}