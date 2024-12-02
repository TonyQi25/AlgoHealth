package use_case.one_day_history;

import java.util.HashMap;

public class UpdateHistoryTotalsInputData {

    String username;
    String date;

    public UpdateHistoryTotalsInputData(String username, String date) {
        this.username = username;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }
}
