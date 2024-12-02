package interface_adapter.signup;

import data.DayInfo;
import java.util.List;

import interface_adapter.ViewManagerModel;
import interface_adapter.daily_value_recs.MainViewModel;
import interface_adapter.daily_value_recs.MainViewState;
import interface_adapter.login.LoginViewModel;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import view.MainView.mainView;

public class SignupPresenter implements SignupOutputBoundary {

    private final SignupViewModel signupViewModel;
    private final MainViewModel mainViewModel;
    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignupPresenter(SignupViewModel signupViewModel, MainViewModel mainViewModel, LoginViewModel loginViewModel,
                           ViewManagerModel viewManagerModel) {

        this.signupViewModel = signupViewModel;
        this.mainViewModel = mainViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(SignupOutputData signupOutputData) {
        final MainViewState mainViewState = mainViewModel.getState();
        mainViewState.setUsername(signupOutputData.getUsername());
        mainViewState.setPassword(signupOutputData.getPassword());
        mainViewState.setCalories(0.0);
        mainViewState.setProtein(0.0);
        mainViewState.setCarbs(0.0);
        mainViewState.setFat(0.0);

        this.mainViewModel.setState(mainViewState);
        this.mainViewModel.firePropertyChanged();

        this.viewManagerModel.setState(mainViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void prepareFailView(String error) {
        final SignupState signupState = signupViewModel.getState();
        signupState.setSignupError(error);
        this.signupViewModel.firePropertyChanged();
    }

    public void switchToLogin() {
        this.viewManagerModel.setState(this.loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
