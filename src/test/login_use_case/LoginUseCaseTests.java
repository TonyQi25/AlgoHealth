package login_use_case;

import interface_adapter.login.LoginPresenter;
import org.junit.jupiter.api.Test;
import use_case.login.*;

import static org.junit.Assert.assertEquals;

public class LoginUseCaseTests {

    @Test
    void loginInteractorSuccessTest() {
        LoginInputData loginInputData = new LoginInputData("test001", "password");

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData outputData) {
                assertEquals(outputData.getUsername(), "test001");
                assertEquals(outputData.getPassword(), "password");
                assertEquals(outputData.getCalories(), 0.0, 0);
                assertEquals(outputData.getProtein(), 0.0, 0);
                assertEquals(outputData.getCarbs(), 0.0, 0);
                assertEquals(outputData.getFat(), 0.0, 0);
            }

            @Override
            public void prepareFailView(String errorMessage) {

            }

            @Override
            public void switchToSignup() {

            }
        };

        LoginInputBoundary loginInteractor = new LoginInteractor(loginPresenter);
        loginInteractor.execute(loginInputData);
    }

    @Test
    void loginInteractorAccountNotExistFailTest() {
        LoginInputData loginInputData = new LoginInputData("GVFGjfKHABFHSDHGhj`BDGJns~", "password");

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData outputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("GVFGjfKHABFHSDHGhj`BDGJns~: Account does not exist.", errorMessage);
            }

            @Override
            public void switchToSignup() {

            }
        };

        LoginInputBoundary loginInteractor = new LoginInteractor(loginPresenter);
        loginInteractor.execute(loginInputData);
    }

    @Test
    void loginInteractorWrongPasswordFailTest() {
        LoginInputData loginInputData = new LoginInputData("test001", "pass");

        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData outputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Incorrect password for \"test001\".", errorMessage);
            }

            @Override
            public void switchToSignup() {

            }
        };

        LoginInputBoundary loginInteractor = new LoginInteractor(loginPresenter);
        loginInteractor.execute(loginInputData);
    }

    @Test
    void loginInteractorSwitchToSignupTest() {
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData outputData) {

            }

            @Override
            public void prepareFailView(String errorMessage) {

            }

            @Override
            public void switchToSignup() {

            }
        };

        LoginInputBoundary loginInteractor = new LoginInteractor(loginPresenter);
        loginInteractor.switchToSignup();
    }
}
