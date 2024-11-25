package interface_adapter.history;

import data.DayInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoryState {
    private List<String> dayDetails = new ArrayList<>();
    private String date;
    private String historyError;

    public void setDayDetails(List<String> dayDetails) {
        this.dayDetails = dayDetails;
    }

    public List<String> getDayDetails() {
        return dayDetails;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setHistoryError(String loginError) {
        this.historyError = loginError;
    }

    public String getHistoryError() {
        return historyError;
    }
}
