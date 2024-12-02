
package view.SignupView;

import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import view.Helpers.DOBSelectorListener;
import view.Helpers.NumericFilter;
import view.Helpers.ViewHelpers;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Objects;

public class SignupView extends JPanel implements PropertyChangeListener {

    private final SignupViewModel signupViewModel;
    private final SignupController signupController;
    private String viewName = "sign up";

    private final JPanel signupPanel = new JPanel();
    private JPanel welcomePanel;
    private JPanel usernamePanel;
    private JPanel passwordPanel;
    private JPanel dobPanel;
    private JPanel heightPanel;
    private JPanel weightPanel;
    private JPanel dietPanel;
    private JPanel goalPanel;
    private JPanel restrictionPanel;
    private JPanel buttonPanel;

    private final String[] dietOptions;
    private final String[] restrictionOptions;
    private final String[] goalOptions;

    public SignupView(SignupViewModel signupViewModel, SignupController signupController, String[] dietOptions,
                      String[] restrictionOptions, String[] goalOptions) {
        this.signupViewModel = signupViewModel;
        this.signupController = signupController;

        this.dietOptions = dietOptions;
        this.restrictionOptions = restrictionOptions;
        this.goalOptions = goalOptions;

        this.signupViewModel.addPropertyChangeListener(this);

        // welcome label panel
        final String LABEL = "Enter your information to create a new AlgoHealth Nutrition Tracker account!";

        this.welcomePanel = new JPanel();
        JLabel welcomeLabel = new JLabel(LABEL);

        this.welcomePanel.add(welcomeLabel);

        // username panel
        this.usernamePanel = new JPanel();
        JLabel usernameText = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                signupViewModel.getState().setUsername(usernameField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                signupViewModel.getState().setUsername(usernameField.getText());
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
        JButton togglePasswordButton = ViewHelpers.getPasswordToggleButton(this.passwordPanel, passwordShowField,
                passwordHideField);

        passwordShowField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                signupViewModel.getState().setPassword(passwordShowField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                signupViewModel.getState().setPassword(passwordShowField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        passwordHideField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                signupViewModel.getState().setPassword(new String(passwordHideField.getPassword()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                signupViewModel.getState().setPassword(new String(passwordHideField.getPassword()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        this.passwordPanel.add(passwordLabel);
        this.passwordPanel.add(passwordHideField);
        this.passwordPanel.add(togglePasswordButton);

        // DOB panel
        this.dobPanel = new JPanel();
        JLabel dobLabel = new JLabel("Date of Birth (MM/DD/YYYY):");

        JComboBox<String> monthField = ViewHelpers.getDobBox(1, 12, true);
        JComboBox<String> dayField = ViewHelpers.getDobBox(
                1, LocalDate.of(2000, 1, 1).lengthOfMonth(), true);
        JComboBox<String> yearField = ViewHelpers.getDobBox(LocalDate.now().getYear() - 150,
                LocalDate.now().getYear(), false);     // arbitrarily picked 150, any similar number works

        monthField.addActionListener(new DOBSelectorListener(this.signupViewModel,
                yearField, monthField, dayField));
        yearField.addActionListener(new DOBSelectorListener(this.signupViewModel,
                yearField, monthField, dayField));

        this.dobPanel.add(dobLabel);
        this.dobPanel.add(monthField);
        this.dobPanel.add(dayField);
        this.dobPanel.add(yearField);

        // height panel
        this.heightPanel = new JPanel();
        JLabel heightLabel = new JLabel("Height:");
        JLabel decimalLabel1 = new JLabel(".");
        JLabel unitHeightLabel = new JLabel("cm");
        JTextField wholeHeightField = new JTextField(3);
        JTextField decHeightField = new JTextField(2);

        wholeHeightField.getDocument().addDocumentListener(new NumericFilter(
                this.signupViewModel, "whole height", wholeHeightField, 3));
        decHeightField.getDocument().addDocumentListener(new NumericFilter(
                this.signupViewModel, "decimal height", decHeightField, 2));

        this.heightPanel.add(heightLabel);
        this.heightPanel.add(wholeHeightField);
        this.heightPanel.add(decimalLabel1);
        this.heightPanel.add(decHeightField);
        this.heightPanel.add(unitHeightLabel);

        // weight panel
        this.weightPanel = new JPanel();
        JLabel weightLabel = new JLabel("Weight:");
        JLabel decimalLabel2 = new JLabel(".");
        JLabel unitWeightLabel = new JLabel("kg");
        JTextField wholeWeightField = new JTextField(3);
        JTextField decWeightField = new JTextField(2);

        wholeWeightField.getDocument().addDocumentListener(new NumericFilter(
                this.signupViewModel, "whole weight", wholeWeightField, 3));
        decWeightField.getDocument().addDocumentListener(new NumericFilter(
                this.signupViewModel, "decimal weight", decWeightField, 2));

        this.weightPanel.add(weightLabel);
        this.weightPanel.add(wholeWeightField);
        this.weightPanel.add(decimalLabel2);     // from height panel
        this.weightPanel.add(decWeightField);
        this.weightPanel.add(unitWeightLabel);

        // diet panel
        this.dietPanel = new JPanel();
        JLabel dietLabel = new JLabel("Diet(s):");

        JList<String> dietList = new JList<>(this.dietOptions);
        dietList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton dietDropDownButton = ViewHelpers.getDietDropDownButton(this.signupViewModel, dietList);

        this.dietPanel.add(dietLabel);
        this.dietPanel.add(dietDropDownButton);

        // restriction panel
        this.restrictionPanel = new JPanel();
        JLabel restrictionLabel = new JLabel("Dietary Restriction(s):");

        JList<String> restrictionList = new JList<>(this.restrictionOptions);
        restrictionList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton restrictionDropDownButton = ViewHelpers.getRestrictionDropDownButton(
                this.signupViewModel, restrictionList);

        this.restrictionPanel.add(restrictionLabel);
        this.restrictionPanel.add(restrictionDropDownButton);

        // goal panel
        this.goalPanel = new JPanel();
        JLabel goalLabel = new JLabel("Please input your main nutrition goal:");
        JComboBox<String> goalList = new JComboBox<>(this.goalOptions);

        signupViewModel.getState().setGoal(goalList.getItemAt(0));
        goalList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signupViewModel.getState().setGoal(Objects.requireNonNull(goalList.getSelectedItem()).toString());
            }
        });

        this.goalPanel.add(goalLabel);
        this.goalPanel.add(goalList);

        // button panel
        this.buttonPanel = new JPanel();
        JButton signupButton = ViewHelpers.getSignupButton(this.signupViewModel, this.signupController);
        JButton loginButton = ViewHelpers.getGoToLoginButton(this.signupController);

        this.buttonPanel.add(signupButton);
        this.buttonPanel.add(loginButton);

        // putting it all together
        this.signupPanel.setLayout(new BoxLayout(this.signupPanel, BoxLayout.Y_AXIS));
        this.signupPanel.add(this.welcomePanel);
        this.signupPanel.add(this.usernamePanel);
        this.signupPanel.add(this.passwordPanel);
        this.signupPanel.add(this.dobPanel);
        this.signupPanel.add(this.heightPanel);
        this.signupPanel.add(this.weightPanel);
        this.signupPanel.add(this.dietPanel);
        this.signupPanel.add(this.restrictionPanel);
        this.signupPanel.add(this.goalPanel);
        this.signupPanel.add(this.buttonPanel);

        this.add(this.signupPanel);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final SignupState signupState = (SignupState) evt.getNewValue();

        if (signupState.getSignupError() != null) {
            JDialog failedSignupFrame = ViewHelpers.getFailedDialog(signupState.getSignupError());
            failedSignupFrame.setVisible(true);
        }
    }

    public String getViewName() {
        return this.viewName;
    }
}

