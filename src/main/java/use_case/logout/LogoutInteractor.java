package use_case.logout;

import data.AccountInfo;

public class LogoutInteractor implements LogoutInputBoundary {

    private final LogoutDataAccessInterface logoutDataAccessInterface;
    private final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutDataAccessInterface logoutDataAccessInterface,
                            LogoutOutputBoundary logoutPresenter) {
        this.logoutDataAccessInterface = logoutDataAccessInterface;
        this.logoutPresenter = logoutPresenter;
    }

    @Override
    public void execute(LogoutInputData logoutInputData) {
        String currentUsername = logoutInputData.getCurrentUsername();
        AccountInfo currentAccount = logoutDataAccessInterface.get(currentUsername);
        logoutDataAccessInterface.save(currentAccount);

        logoutDataAccessInterface.setCurrentUsername(null);
        LogoutOutputData logoutOutputData = new LogoutOutputData("", "");
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}
