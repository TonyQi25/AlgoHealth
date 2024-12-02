package use_case.one_day_history;

import java.util.HashMap;

/**
 * input data for view one day use case
 */
public class UpdateHistoryTotalsInputData {

    String username;
    String password;
    String date;

    public UpdateHistoryTotalsInputData(String username, String date, String password) {
        this.username = username;
        this.date = date;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDate() {
        return date;
    }
}
