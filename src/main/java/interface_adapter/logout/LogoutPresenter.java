package interface_adapter.logout;

import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

public class LogoutPresenter implements LogoutOutputBoundary {

    public LogoutPresenter() {

    }

    @Override
    public void prepareSuccessView(LogoutOutputData logoutOutputData) {
        // takes you to the login screen
    }
}
