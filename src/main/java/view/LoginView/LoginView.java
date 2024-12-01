/*package view.LoginView;

import javax.swing.*;

public class LoginView {

    private final LoginViewBuilder loginViewBuilder;

    public LoginView(LoginViewBuilder loginViewBuilder) {
        this.loginViewBuilder = loginViewBuilder;
    }

    public void generateLoginView() {
        JFrame loginView = loginViewBuilder.addWelcomeLabel().
                addUsernamePanel().
                addPasswordPanel().
                addButtonPanel().
                build();

        loginView.pack();
        loginView.setVisible(true);
    }

    public static void main(String[] args) {    // test for the gui
        LoginView loginView = new LoginView(new LoginViewBuilder(new JPanel(), new JFrame("Log In Page")));

        loginView.generateLoginView();
    }
}*/
