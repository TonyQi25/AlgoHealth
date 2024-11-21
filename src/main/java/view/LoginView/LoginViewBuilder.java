/*package view.LoginView;

import data_access.TempDAOInMemory;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import org.jetbrains.annotations.NotNull;
import use_case.login.LoginDataAccessInterface;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.MainView.mainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginViewBuilder {   // maybe more of the methods should have getters to generate for SRP in SOLID

    private final JFrame loginFrame;
    private final JPanel loginPanel;
    private JPanel welcomePanel;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    private JPanel buttonPanel;

    public LoginViewBuilder(JPanel loginPanel, JFrame loginFrame) {
        this.loginFrame = loginFrame;
        this.loginPanel = loginPanel;
        this.loginPanel.setLayout(new BoxLayout(this.loginPanel, BoxLayout.Y_AXIS));
    }

    public JPanel getWelcomePanel() {
        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome to AlgoHealth's Nutrition Tracker!");

        welcomePanel.add(welcomeLabel);

        return welcomePanel;
    }

    public void setWelcomeLabel() {
        this.welcomePanel = this.getWelcomePanel();
    }

    public LoginViewBuilder addWelcomeLabel() {
        this.setWelcomeLabel();
        this.loginPanel.add(welcomePanel);
        return this;
    }

    public JPanel getUsernamePanel() {
        JPanel usernamePanel = new JPanel();
        JLabel usernameText = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        usernamePanel.add(usernameText);
        usernamePanel.add(usernameField);

        return usernamePanel;
    }

    public void setUsernamePanel() {
        this.usernamePanel = this.getUsernamePanel();
    }

    public LoginViewBuilder addUsernamePanel() {
        this.setUsernamePanel();
        this.loginPanel.add(usernamePanel);
        return this;
    }

    public JButton getPasswordToggleButton(JTextField passwordShowField,
                                           JPasswordField passwordHideField) {
        String SHOW_MESSAGE = "Show";
        String HIDE_MESSAGE = "Hide";
        JButton togglePasswordButton = new JButton(SHOW_MESSAGE);

        togglePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordPanel.getComponent(1) instanceof JPasswordField) {
                    String hideText = new String(passwordHideField.getPassword());
                    passwordPanel.remove(1);
                    passwordShowField.setText(hideText);
                    passwordPanel.add(passwordShowField, 1);
                    togglePasswordButton.setText(HIDE_MESSAGE);
                } else {
                    String showText = passwordShowField.getText();
                    passwordPanel.remove(1);
                    passwordHideField.setText(showText);
                    passwordPanel.add(passwordHideField, 1);
                    togglePasswordButton.setText(SHOW_MESSAGE);
                }
            }
        });
        return togglePasswordButton;
    }

    public JPanel getPasswordPanel() {
        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordShowField = new JTextField(15);
        JPasswordField passwordHideField = new JPasswordField(15);
        JButton togglePasswordButton = this.getPasswordToggleButton(passwordShowField, passwordHideField);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordHideField);
        passwordPanel.add(togglePasswordButton);

        return passwordPanel;
    }

    public void setPasswordPanel() {
        this.passwordPanel = this.getPasswordPanel();
    }

    public LoginViewBuilder addPasswordPanel() {
        this.setPasswordPanel();
        this.loginPanel.add(passwordPanel);
        return this;
    }

    public JButton getLoginButton(JTextField usernameField, JPasswordField passwordField) {

        LoginOutputBoundary loginPresenter = new LoginPresenter(new mainView()); // does this work????
        LoginDataAccessInterface loginDataAccessObject = new TempDAOInMemory();
        LoginInputBoundary loginUseCaseInteractor = new LoginInteractor(loginDataAccessObject, loginPresenter);
        LoginController loginController = new LoginController(loginUseCaseInteractor);

        return createLoginButton(usernameField, passwordField, loginController, this.loginFrame);
    }

    @NotNull
    private static JButton createLoginButton(JTextField usernameField,
                                             JPasswordField passwordField,
                                             LoginController loginController,
                                             JFrame loginFrame) {

        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                loginController.execute(username, password, loginFrame);
                System.out.println(username);
                System.out.println(password);
            }
        });
        return loginButton;
    }

    public JButton getSignupButton() {
        JButton signupButton = new JButton("Go to Sign Up");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the signup view, close this one
            }
        });
        return signupButton;
    }

    public JPanel getButtonPanel() {
        JTextField usernameField = (JTextField) this.usernamePanel.getComponent(1); // maybe bad practice
        JPasswordField passwordField = (JPasswordField) this.passwordPanel.getComponent(1);

        JPanel buttonPanel = new JPanel();
        JButton loginButton = getLoginButton(usernameField, passwordField);
        JButton signupButton = getSignupButton();

        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        return buttonPanel;
    }

    public void setButtonPanel() {
        this.buttonPanel = this.getButtonPanel();
    }

    public LoginViewBuilder addButtonPanel() {
        this.setButtonPanel();
        this.loginPanel.add(buttonPanel);
        return this;
    }

    public JFrame build() {
        this.loginFrame.setContentPane(this.loginPanel);
        this.loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return loginFrame;
    }
}*/
