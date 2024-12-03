package use_case.logout;

public class LogoutInteractor implements LogoutInputBoundary {

    private final LogoutDataAccessInterface logoutDataAccessInterface;
    private final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutDataAccessInterface logoutDataAccessInterface,
                            LogoutOutputBoundary logoutPresenter) {
        this.logoutDataAccessInterface = logoutDataAccessInterface;
        this.logoutPresenter = logoutPresenter;
    }

    @Override
    public void execute() {
        final LogoutOutputData logoutOutputData = new LogoutOutputData("", "");
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}
