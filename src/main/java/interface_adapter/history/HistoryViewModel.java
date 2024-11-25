package interface_adapter.history;

import interface_adapter.ViewModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryViewModel extends ViewModel<HistoryState> {

    private List<String> dayDetails = new ArrayList<>();
    private String date;

    public HistoryViewModel(String viewName) {
        super("history");
        setState(new HistoryState());
    }

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
}
