package use_case.history;

import java.time.LocalDate;

/**
 * The input data for the Calendar
 */
public class HistoryInputData {

    private final LocalDate date;

    public HistoryInputData(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
