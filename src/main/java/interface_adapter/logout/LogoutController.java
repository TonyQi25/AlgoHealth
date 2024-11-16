package interface_adapter.logout;

import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInputData;

public class LogoutController {

    private final LogoutInputBoundary logoutUseCaseInteractor;

    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }

    public void execute(String currentUsername) {
        LogoutInputData logoutInputData = new LogoutInputData(currentUsername);
        logoutUseCaseInteractor.execute(logoutInputData);
    }
}
