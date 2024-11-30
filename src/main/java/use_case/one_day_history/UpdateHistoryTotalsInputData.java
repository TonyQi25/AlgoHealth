package use_case.one_day_history;

import java.util.HashMap;

public class UpdateHistoryTotalsInputData {

    HashMap<String, Double> foodList;
    String username;
    String date;

    public UpdateHistoryTotalsInputData(HashMap<String, Double> foodList, String username, String date) {
        this.foodList = foodList;
        this.username = username;
        this.date = date;
    }

    public HashMap<String, Double> getFoodList() {
        return foodList;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }
}
