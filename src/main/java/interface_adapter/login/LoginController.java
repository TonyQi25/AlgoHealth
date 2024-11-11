package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import javax.swing.*;

/**
 * The controller for the Login Use Case.
 */
public class LoginController {

    private final LoginInputBoundary loginUseCaseInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String username, String password, JFrame loginFrame) {
        System.out.println("reached controller");
        final LoginInputData loginInputData = new LoginInputData(username, password, loginFrame);
        loginUseCaseInteractor.execute(loginInputData);
        System.out.println("past controller execute");
    }
}
