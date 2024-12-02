package login_use_case;

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
}
