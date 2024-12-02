package interface_adapter.history;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * State for history use case
 */
public class HistoryState {
    private List<String> dayDetails = new ArrayList<>();
    private String date;
    private LocalDate viewingDate;
    private String historyError;
    private String username;
    private String password;
    private boolean completed;

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getCompleted() {
        return completed;
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

    public void setViewingDate(LocalDate viewingDate) {
        this.viewingDate = viewingDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getViewingDate() {
        return viewingDate;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
