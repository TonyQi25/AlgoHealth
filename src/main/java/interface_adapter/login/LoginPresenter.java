package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.daily_value_recs.MainViewModel;
import interface_adapter.daily_value_recs.MainViewState;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final MainViewModel mainViewModel;
    private final SignupViewModel signupViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(LoginViewModel loginState, MainViewModel mainViewState,
                          SignupViewModel signupViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginState;
        this.mainViewModel = mainViewState;
        this.signupViewModel = signupViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {

        final MainViewState mainViewState = mainViewModel.getState();
        mainViewState.setUsername(loginOutputData.getUsername());
        mainViewState.setPassword(loginOutputData.getPassword());
        mainViewState.setCalories(loginOutputData.getCalories());
        mainViewState.setProtein(loginOutputData.getProtein());
        mainViewState.setCarbs(loginOutputData.getCarbs());
        mainViewState.setFat(loginOutputData.getFat());

        this.mainViewModel.setState(mainViewState);
        this.mainViewModel.firePropertyChanged();

        this.viewManagerModel.setState(mainViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {

        final LoginState loginState = loginViewModel.getState();    // def bad practice, mutating the internal state
        loginState.setLoginError(error);
        this.loginViewModel.firePropertyChanged();

    }

    public void switchToSignup() {
        System.out.println("finally, presenter");

        this.viewManagerModel.setState(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
