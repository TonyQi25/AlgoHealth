package use_case.signup;

import data.DayInfo;

import java.util.List;

public class SignupOutputData {

    private final String username;
    private final List<DayInfo> days;

    public SignupOutputData(String username, List<DayInfo> days) {
        this.username = username;
        this.days = days;
    }

    public String getUsername() {
        return username;
    }

    public List<DayInfo> getDays() {
        return days;
    }
}
