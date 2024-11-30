package interface_adapter.one_day_history;

import java.util.HashMap;

public class UpdateHistoryTotalsState {

    HashMap<String, Double> foodList;
    String username;
    String date;

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
}

