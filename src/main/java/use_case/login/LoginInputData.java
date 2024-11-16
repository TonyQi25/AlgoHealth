package use_case.login;

import javax.swing.*;

/**
 * The Input Data for the Login Use Case.
 */
public class LoginInputData {

    private final String username;
    private final String password;
    private final JFrame loginFrame;

    public LoginInputData(String username, String password, JFrame loginFrame) {
        this.username = username;
        this.password = password;
        this.loginFrame = loginFrame;
    }

    String getUsername() {
        return this.username;
    }

    String getPassword() {
        return this.password;
    }

    JFrame getLoginFrame() {
        return this.loginFrame;
    }
}
