package use_case.logout;

public class LogoutInputData {

    private final String currentUsername;

    public LogoutInputData(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
