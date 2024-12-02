package use_case.signup;

import data.DayInfo;

import java.util.List;

public class SignupOutputData {

    private final String username;
    private final String password;
    private final List<DayInfo> days;

    public SignupOutputData(String username, String password, List<DayInfo> days) {
        this.username = username;
        this.password = password;
        this.days = days;
    }

    public String getUsername() {
        return username;
    }

    public List<DayInfo> getDays() {
        return days;
    }

    public String getPassword() {
        return password;
    }
}
