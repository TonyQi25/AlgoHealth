/*package interface_adapter.signup;

import data.DayInfo;
import java.util.List;

import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import view.MainView.mainView;

public class SignupPresenter implements SignupOutputBoundary {

    private final mainView homePage;

    public SignupPresenter(mainView homePage) {
        this.homePage = homePage;
    }

    public void prepareSuccessView(SignupOutputData signupOutputData) {
        List<DayInfo> days = signupOutputData.getDays();
        homePage.genMvGUI(days.get(0));
    }

    public void prepareFailView(String error) {

    }
}*/
