package use_case.history;

import java.time.LocalDate;

/**
 * The input data for the history
 */
public class HistoryInputData {

    private final LocalDate date;
    private final String username;
    private final String password;

    public HistoryInputData(LocalDate date, String username, String password) {
        this.date = date;
        this.username = username;
        this.password = password;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
