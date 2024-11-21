package interface_adapter.signup;

import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

import java.time.LocalDate;
import java.util.List;

public class SignupController {

    private final SignupInputBoundary signupUseCaseInteractor;

    public SignupController(SignupInputBoundary signupUseCaseInteractor) {
        this.signupUseCaseInteractor = signupUseCaseInteractor;
    }

    public void execute(LocalDate dateOfBirth, float height, float weight,
                        String[] diet, String goal, String username, String password,
                        List<String> dietaryRestrictions) {
        SignupInputData signupInputData = new SignupInputData(dateOfBirth, height, weight, diet,
                goal, username, password, dietaryRestrictions);
        signupUseCaseInteractor.execute(signupInputData);
    }
}
