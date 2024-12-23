package sign_up_use_case;

import org.junit.jupiter.api.Test;
import use_case.signup.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SignupUseCaseTests {

    @Test
    void signupInteractorSuccessTest() {
        String randomUsername = generateRandomUsername();
        SignupInputData signupInputData = new SignupInputData(LocalDate.now(), 1.0F, 1.0F,
                new String[0], "None", randomUsername, "password", new ArrayList<>());

        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {
                assertEquals(randomUsername, signupOutputData.getUsername());
                assertEquals("password", signupOutputData.getPassword());
            }

            @Override
            public void prepareFailView(String error) {

            }

            @Override
            public void switchToLogin() {

            }
        };

        SignupInputBoundary signupInteractor = new SignupInteractor(signupPresenter);
        signupInteractor.execute(signupInputData);
    }

    @Test
    void signupInteractorUsernameTakenFailTest() {
        SignupInputData signupInputData = new SignupInputData(LocalDate.now(), 1.0F, 1.0F,
                new String[0], "None", "test001", "password", new ArrayList<>());

        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {

            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username is taken.", error);
            }

            @Override
            public void switchToLogin() {

            }
        };

        SignupInputBoundary signupInteractor = new SignupInteractor(signupPresenter);
        signupInteractor.execute(signupInputData);
    }

    @Test
    void signupInteractorUsernameBlankFailTest() {
        SignupInputData signupInputData = new SignupInputData(LocalDate.now(), 1.0F, 1.0F,
                new String[0], "None", "", "password", new ArrayList<>());

        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {

            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username cannot be empty.", error);
            }

            @Override
            public void switchToLogin() {

            }
        };

        SignupInputBoundary signupInteractor = new SignupInteractor(signupPresenter);
        signupInteractor.execute(signupInputData);
    }

    @Test
    void signupInteractorPasswordBlankFailTest() {
        String randomUsername = generateRandomUsername();
        SignupInputData signupInputData = new SignupInputData(LocalDate.now(), 1.0F, 1.0F,
                new String[0], "None", randomUsername, "", new ArrayList<>());

        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {

            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password cannot be empty.", error);
            }

            @Override
            public void switchToLogin() {

            }
        };

        SignupInputBoundary signupInteractor = new SignupInteractor(signupPresenter);
        signupInteractor.execute(signupInputData);
    }

    @Test
    void signupInteractorInvalidHeightTest() {
        String randomUsername = generateRandomUsername();
        SignupInputData signupInputData = new SignupInputData(LocalDate.now(), 0.0F, 1.0F,
                new String[0], "None", randomUsername, "password", new ArrayList<>());

        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {

            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid height.", error);
            }

            @Override
            public void switchToLogin() {

            }
        };

        SignupInputBoundary signupInteractor = new SignupInteractor(signupPresenter);
        signupInteractor.execute(signupInputData);
    }

    @Test
    void signupInteractorInvalidWeightTest() {
        String randomUsername = generateRandomUsername();
        SignupInputData signupInputData = new SignupInputData(LocalDate.now(), 1.0F, 0.0F,
                new String[0], "None", randomUsername, "password", new ArrayList<>());

        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {

            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid weight.", error);
            }

            @Override
            public void switchToLogin() {

            }
        };

        SignupInputBoundary signupInteractor = new SignupInteractor(signupPresenter);
        signupInteractor.execute(signupInputData);
    }

    @Test
    void signupInteractorSwitchToLoginTest() {
        SignupOutputBoundary signupPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData signupOutputData) {

            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid weight.", error);
            }

            @Override
            public void switchToLogin() {

            }
        };

        SignupInputBoundary signupInteractor = new SignupInteractor(signupPresenter);
        signupInteractor.switchToLogin();
    }

    // Code adapted from StackOverflow
    // https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
    // 2024-12-01
    private String generateRandomUsername() {
        String charChoices = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder randomString = new StringBuilder();
        Random rnd = new Random();
        while (randomString.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * charChoices.length());
            randomString.append(charChoices.charAt(index));
        }
        return randomString.toString();
    }
}
