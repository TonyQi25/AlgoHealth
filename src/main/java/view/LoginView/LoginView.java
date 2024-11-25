package view.LoginView;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import view.Helpers.ViewHelpers;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginView extends JPanel implements PropertyChangeListener {

    private final LoginViewModel loginViewModel;
    private final LoginController loginController;
    private final String viewName = "log in";

    private final JPanel loginPanel = new JPanel();
    private JPanel welcomePanel;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    private JPanel buttonPanel;

    public LoginView(LoginViewModel loginViewModel, LoginController loginController) {
        this.loginViewModel = loginViewModel;
        this.loginController = loginController;

        this.loginViewModel.addPropertyChangeListener(this);
        

        // welcome label panel
        this.welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome to AlgoHealth's Nutrition Tracker!");

        this.welcomePanel.add(welcomeLabel);

        // username panel
        this.usernamePanel = new JPanel();
        JLabel usernameText = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                loginViewModel.getState().setUsername(usernameField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                loginViewModel.getState().setUsername(usernameField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        this.usernamePanel.add(usernameText);
        this.usernamePanel.add(usernameField);

        // password panel
        this.passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordShowField = new JTextField(15);
        JPasswordField passwordHideField = new JPasswordField(15);
        JButton togglePasswordButton = ViewHelpers.getPasswordToggleButton(
                this.passwordPanel, passwordShowField, passwordHideField);

        passwordShowField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                loginViewModel.getState().setPassword(passwordShowField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                loginViewModel.getState().setPassword(passwordShowField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        passwordHideField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                loginViewModel.getState().setPassword(new String(passwordHideField.getPassword()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                loginViewModel.getState().setPassword(new String(passwordHideField.getPassword()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        this.passwordPanel.add(passwordLabel);
        this.passwordPanel.add(passwordHideField);
        this.passwordPanel.add(togglePasswordButton);

        // buttons panel
        this.buttonPanel = new JPanel();
        JButton loginButton = ViewHelpers.getLoginButton(this.loginViewModel, this.loginController);
        JButton signupButton = ViewHelpers.getGoToSignupButton(this.loginController);

        this.buttonPanel.add(loginButton);
        this.buttonPanel.add(signupButton);

        // putting it all together
        this.loginPanel.setLayout(new BoxLayout(this.loginPanel, BoxLayout.Y_AXIS));
        this.loginPanel.add(welcomePanel);
        this.loginPanel.add(usernamePanel);
        this.loginPanel.add(passwordPanel);
        this.loginPanel.add(buttonPanel);

        this.add(loginPanel);
    }

    public String getViewName() {
        return this.viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        final LoginState loginState = (LoginState) evt.getNewValue();
        this.setFields(loginState);

        if (loginState.getLoginError() != null) {
            JDialog failedLoginFrame = ViewHelpers.getFailedDialog(loginState.getLoginError());
            failedLoginFrame.setVisible(true);
        }
    }

    public void setFields(LoginState loginState) {
        String setUsername = loginState.getUsername();
        String setPassword = loginState.getPassword();

        JTextField usernameField = (JTextField) this.usernamePanel.getComponent(1);
        usernameField.setText(setUsername);

        JButton passwordToggleButton = (JButton) this.passwordPanel.getComponent(2);
        if (!(this.passwordPanel.getComponent(1) instanceof JPasswordField)) {
            passwordToggleButton.doClick();
        }
        JPasswordField passwordField = (JPasswordField) this.passwordPanel.getComponent(1);
        passwordField.setText(setPassword);
    }
}
