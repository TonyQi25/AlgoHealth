package use_case.one_day_history;

import java.util.HashMap;

public class UpdateHistoryTotalsInputData {

    HashMap<Integer, Double> idToWeight;
    String username;
    String date;

    public UpdateHistoryTotalsInputData(HashMap<Integer, Double> foodList, String username, String date) {
        this.idToWeight = foodList;
        this.username = username;
        this.date = date;
    }

    public HashMap<Integer, Double> getIdToWeight() {
        return idToWeight;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }
}
