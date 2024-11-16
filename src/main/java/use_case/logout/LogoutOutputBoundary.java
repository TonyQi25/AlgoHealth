package use_case.logout;

public interface LogoutOutputBoundary {

    void prepareSuccessView(LogoutOutputData logoutOutputData);  // do we need a prepareFailView?
                                                                 // that violates that interface rule right?
}
