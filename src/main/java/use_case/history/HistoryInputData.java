package use_case.history;

import java.time.LocalDate;

/**
 * The input data for the Calendar
 */
public class HistoryInputData {

    private final LocalDate date;
    private final String username;

    public HistoryInputData(LocalDate date, String username) {
        this.date = date;
        this.username = username;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }
}
