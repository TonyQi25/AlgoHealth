package use_case.login;

import data.DayInfo;

import java.util.List;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String username;
    private final List<DayInfo> days;

    public LoginOutputData(String username, List<DayInfo> days) {
        this.username = username;
        this.days = days;
    }

    public String getUsername() {
        return this.username;
    }

    public List<DayInfo> getDays() {
        return this.days;
    }
}
