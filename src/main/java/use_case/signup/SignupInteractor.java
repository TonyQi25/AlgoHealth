package use_case.signup;

import data_access.GradeAccountDAO;
import data.AccountInfo;
import data.DayInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SignupInteractor implements SignupInputBoundary {

    private final SignupDataAccessInterface signupDataAccessObject = new GradeAccountDAO();
    private final SignupOutputBoundary signupPresenter;

    public SignupInteractor(SignupOutputBoundary signupPresenter) {
        this.signupPresenter = signupPresenter;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (signupDataAccessObject.existsByName(signupInputData.getUsername())) {
            signupPresenter.prepareFailView("Username is taken.");
        } else if (signupInputData.getUsername().isEmpty()) {
            signupPresenter.prepareFailView("Username cannot be empty.");
        } else if (signupInputData.getPassword().isEmpty()) {
            signupPresenter.prepareFailView("Password cannot be empty.");
        } else if (signupInputData.getHeight() <= 0) {
            signupPresenter.prepareFailView("Invalid height.");
        } else if (signupInputData.getWeight() <= 0) {
            signupPresenter.prepareFailView("Invalid weight.");
        } else {
            AccountInfo accountInfo = new AccountInfo(signupInputData.getDateOfBirth(),
                    signupInputData.getHeight(), signupInputData.getWeight(), signupInputData.getDiet(),
                    signupInputData.getGoal(), signupInputData.getUsername(), signupInputData.getPassword(),
                    signupInputData.getDietaryRestrictions());

            final List<DayInfo> newDays = new ArrayList<>();
            newDays.add(new DayInfo(LocalDate.now()));
            accountInfo.setDays(newDays);

            signupDataAccessObject.createAccount(accountInfo.getUsername(), accountInfo.getPassword());
            signupDataAccessObject.put(accountInfo.getUsername(), accountInfo.getPassword(), accountInfo);
            signupDataAccessObject.setCurrentUsername(accountInfo.getUsername());

            SignupOutputData signupOutputData = new SignupOutputData(
                    accountInfo.getUsername(), accountInfo.getPassword());
            signupPresenter.prepareSuccessView(signupOutputData);
        }
    }

    public void switchToLogin() {
        signupPresenter.switchToLogin();
    }
}
