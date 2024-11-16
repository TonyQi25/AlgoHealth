package use_case.signup;

import data.AccountInfo;
import data.DayInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SignupInteractor implements SignupInputBoundary {

    private final SignupDataAccessInterface signupDataAccessObject;
    private final SignupOutputBoundary signupPresenter;

    public SignupInteractor(SignupDataAccessInterface signupDataAccessObject,
                            SignupOutputBoundary signupPresenter) {
        this.signupDataAccessObject = signupDataAccessObject;
        this.signupPresenter = signupPresenter;
    }

    @Override
    public void execute(SignupInputData signupInputData) {
        if (!signupDataAccessObject.existsByName(signupInputData.getUsername())) {
            signupPresenter.prepareFailView("Username is taken.");
        } else if (signupInputData.getHeight() <= 0) {
            signupPresenter.prepareFailView("Invalid height.");
        } else if (signupInputData.getWeight() <= 0) {
            signupPresenter.prepareFailView("Invalid weight.");
        } else {
            AccountInfo accountInfo = new AccountInfo(signupInputData.getDateOfBirth(),
                    signupInputData.getHeight(), signupInputData.getWeight(), signupInputData.getDiet(),
                    signupInputData.getGoal(), signupInputData.getUsername(), signupInputData.getPassword(),
                    signupInputData.getDietaryRestrictions());
            List<DayInfo> newDays = new ArrayList<>();
            newDays.add(new DayInfo(LocalDate.now()));
            accountInfo.setDays(newDays);
            signupDataAccessObject.put(accountInfo.getUsername(), accountInfo);
            signupDataAccessObject.setCurrentUsername(accountInfo.getUsername());

            SignupOutputData signupOutputData = new SignupOutputData(accountInfo.getUsername(), accountInfo.getDays());
            signupPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
