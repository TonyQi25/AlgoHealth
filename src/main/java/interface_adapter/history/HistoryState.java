package interface_adapter.history;

import data.DayInfo;

import java.time.LocalDate;

public class HistoryState {
    private LocalDate date;
    private DayInfo dayInfo;

    public LocalDate getDate() {
        return date;
    }

    public DayInfo getDayInfo() {
        return dayInfo;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDayInfo(DayInfo dayInfo) {
        this.dayInfo = dayInfo;
    }
}
