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

    // method taken from https://stackoverflow.com/questions/20536566/creating-a-random-string-with-a-z-and-0-9-in-java
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
