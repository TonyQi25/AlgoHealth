package use_case.signup;

import data.DayInfo;

import java.util.List;

public class SignupOutputData {

    private final String username;
    private final String password;

    public SignupOutputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
