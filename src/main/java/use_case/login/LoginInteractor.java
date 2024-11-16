package use_case.login;

import data.AccountInfo;
import data.DayInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginDataAccessInterface loginDataAccessInterface;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.loginDataAccessInterface = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (false) {   //(!loginDataAccessInterface.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = "password";   //loginDataAccessInterface.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final AccountInfo account = new AccountInfo(LocalDate.now(), 0, 0 , new String[]{""}, "", "", "", new ArrayList<>());// loginDataAccessInterface.get(loginInputData.getUsername());
                System.out.println("reached interactor");
                loginDataAccessInterface.setCurrentUsername(account.getUsername());
                //final LoginOutputData loginOutputData = new LoginOutputData(account.getUsername(),
                //        account.getDays());
                List<DayInfo> days = new ArrayList<>();
                days.add(new DayInfo(LocalDate.now()));

                loginInputData.getLoginFrame().dispose();
                final LoginOutputData loginOutputData = new LoginOutputData(username, days);
                loginPresenter.prepareSuccessView(loginOutputData);
                System.out.println("past presenter");
            }
        }
    }
}
