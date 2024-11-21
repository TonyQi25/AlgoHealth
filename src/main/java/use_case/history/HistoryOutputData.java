package use_case.history;

import data.DayInfo;

public class HistoryOutputData {

    DayInfo dayInfo;

    public HistoryOutputData() {}

    public void setDayInfo (DayInfo day) {
        this.dayInfo = day;
    }

    public DayInfo getDayInfo() {
        return dayInfo;
    }
}
