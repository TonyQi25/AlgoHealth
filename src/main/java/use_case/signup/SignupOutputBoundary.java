package use_case.signup;

public interface SignupOutputBoundary {

    void prepareSuccessView(SignupOutputData signupOutputData);

    void prepareFailView(String error);

    void switchToLogin();
}
