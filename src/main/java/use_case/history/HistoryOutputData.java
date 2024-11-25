package use_case.history;

import data.DayInfo;

import java.util.List;

public class HistoryOutputData {

    List<String> foodInfo;
    String date;
    private final boolean useCaseFailed;

    public HistoryOutputData(List<String> data, String date, boolean useCaseFailed) {
        this.foodInfo = data;
        this.date = date;
        this.useCaseFailed = useCaseFailed;
    }

    public List<String> getFoodList() {
        return foodInfo;
    }

    public String getDate() {
        return date;
    }
}
