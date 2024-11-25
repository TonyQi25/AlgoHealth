package use_case.history;

import data.DayInfo;

import java.time.LocalDate;
import java.util.List;

public class HistoryOutputData {

    List<String> foodInfo;
    String date;
    LocalDate viewingDate;
    private final boolean useCaseFailed;

    public HistoryOutputData(List<String> data, String date, LocalDate viewingDate, boolean useCaseFailed) {
        this.foodInfo = data;
        this.date = date;
        this.useCaseFailed = useCaseFailed;
        this.viewingDate = viewingDate;
    }

    public List<String> getFoodList() {
        return foodInfo;
    }

    public String getDate() {
        return date;
    }

    public LocalDate getViewingDate() {
        return viewingDate;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
