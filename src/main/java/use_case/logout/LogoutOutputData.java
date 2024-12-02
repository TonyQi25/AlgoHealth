package use_case.logout;

public class LogoutOutputData {

    private final String username;
    private final String password;

    public LogoutOutputData(String username, String password) {
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
