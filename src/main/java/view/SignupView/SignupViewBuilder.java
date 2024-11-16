/*
package view.SignupView;

import data_access.TempDAOInMemory;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import org.jetbrains.annotations.NotNull;
import use_case.signup.SignupDataAccessInterface;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.TextFieldProperties.NumericFilter;
import view.MainView.mainView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

public class SignupViewBuilder {

    private final JFrame signupFrame;
    private final JPanel signupPanel;
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

    public SignupViewBuilder(JPanel signupPanel, JFrame signupFrame) {
        this.signupFrame = signupFrame;
        this.signupPanel = signupPanel;
        this.signupPanel.setLayout(new BoxLayout(this.signupPanel, BoxLayout.Y_AXIS));
    }

    public JPanel getWelcomePanel() {
        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel =
                new JLabel("Enter your information to create a new AlgoHealth Nutrition Tracker account!");

        welcomePanel.add(welcomeLabel);

        return welcomePanel;
    }

    public void setWelcomePanel() {
        this.welcomePanel = this.getWelcomePanel();
    }

    public SignupViewBuilder addWelcomePanel() {
        this.setWelcomePanel();
        this.signupPanel.add(this.welcomePanel);
        return this;
    }

    public JPanel getUsernamePanel() {
        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        return usernamePanel;
    }

    public void setUsernamePanel() {
        this.usernamePanel = this.getUsernamePanel();
    }

    public SignupViewBuilder addUsernamePanel() {
        this.setUsernamePanel();
        this.signupPanel.add(this.usernamePanel);
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

    public SignupViewBuilder addPasswordPanel() {
        this.setPasswordPanel();
        this.signupPanel.add(this.passwordPanel);
        return this;
    }

    public String[] getDobOptions(int min, int max, boolean ascending) {
        int[] nums = IntStream.rangeClosed(min, max).toArray();

        if (!ascending) {
            int midpoint = (int) (nums.length / 2);
            for (int i = 0; i < midpoint; i++) {
                int newLastValue = nums[i];
                int newFirstValue = nums[nums.length - i - 1];
                nums[i] = newFirstValue;
                nums[nums.length - i - 1] = newLastValue;
            }
        }

        String options = Arrays.toString(nums);
        String formattedOptions = options.substring(1, options.length() - 1);     // bad practice?

        return formattedOptions.split(", ");
    }

    public JComboBox<String> getDobBox(int min, int max, boolean ascending) {
        String[] options = this.getDobOptions(min, max, ascending);
        return new JComboBox<>(options);
    }

    public JPanel getDobPanel() {
        JPanel dobPanel = new JPanel();
        JLabel dobLabel = new JLabel("Date of Birth:");

        JComboBox<String> monthField = this.getDobBox(1, 12, true);
        JComboBox<String> dayField = this.getDobBox(
                1, LocalDate.of(2000, 1, 1).lengthOfMonth(), true);
        JComboBox<String> yearField = this.getDobBox(LocalDate.now().getYear() - 150,
                LocalDate.now().getYear(), false);     // arbitrarily picked 150, any similar number works

        monthField.addActionListener(new DOBSelectorListener(yearField, monthField, dayField));
        yearField.addActionListener(new DOBSelectorListener(yearField, monthField, dayField));

        dobPanel.add(dobLabel);
        dobPanel.add(monthField);
        dobPanel.add(dayField);
        dobPanel.add(yearField);

        return dobPanel;
    }

    public void setDobPanel() {
        this.dobPanel = this.getDobPanel();
    }

    public SignupViewBuilder addDobPanel() {
        this.setDobPanel();
        this.signupPanel.add(this.dobPanel);
        return this;
    }

    public JPanel getHeightPanel() {             // do we want to set a bound on height?
        JPanel heightPanel = new JPanel();
        JLabel heightLabel = new JLabel("Height:");
        JLabel decimalLabel = new JLabel(".");
        JLabel unitLabel = new JLabel("cm");
        JTextField wholeHeightField = new JTextField(3);
        JTextField decHeightField = new JTextField(2);

        wholeHeightField.getDocument().addDocumentListener(new NumericFilter(wholeHeightField, 3));
        decHeightField.getDocument().addDocumentListener(new NumericFilter(decHeightField, 2));

        heightPanel.add(heightLabel);
        heightPanel.add(wholeHeightField);
        heightPanel.add(decimalLabel);
        heightPanel.add(decHeightField);
        heightPanel.add(unitLabel);

        return heightPanel;
    }

    public void setHeightPanel() {
        this.heightPanel = this.getHeightPanel();
    }

    public SignupViewBuilder addHeightPanel() {
        this.setHeightPanel();
        this.signupPanel.add(this.heightPanel);
        return this;
    }

    public JPanel getWeightPanel() {         // do we want to set a bound on weight?
        JPanel weightPanel = new JPanel();
        JLabel weightLabel = new JLabel("Weight:");
        JLabel decimalLabel = new JLabel(".");
        JLabel unitLabel = new JLabel("kg");
        JTextField wholeWeightField = new JTextField(3);
        JTextField decWeightField = new JTextField(2);

        wholeWeightField.getDocument().addDocumentListener(new NumericFilter(wholeWeightField, 3));
        decWeightField.getDocument().addDocumentListener(new NumericFilter(decWeightField, 2));

        weightPanel.add(weightLabel);
        weightPanel.add(wholeWeightField);
        weightPanel.add(decimalLabel);
        weightPanel.add(decWeightField);
        weightPanel.add(unitLabel);

        return weightPanel;
    }

    public void setWeightPanel() {
        this.weightPanel = this.getWeightPanel();
    }

    public SignupViewBuilder addWeightPanel() {
        this.setWeightPanel();
        this.signupPanel.add(this.weightPanel);
        return this;
    }

    public JPanel getDietPanel(String[] dietOptions) {
        JPanel dietPanel = new JPanel();
        JLabel dietLabel = new JLabel("Diet(s):");

        JList<String> dietList = new JList<>(dietOptions);
        dietList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton dietDropDownButton = getDietDropDownButton(dietList);

        dietPanel.add(dietLabel);
        dietPanel.add(dietDropDownButton);

        return dietPanel;
    }

    @NotNull
    private static JButton getDietDropDownButton(JList<String> dietList) {// why are we able to extract this
        JButton dietDropDownButton = new JButton("Click to see list of diets!");
        JScrollPane dietScrollList = new JScrollPane(dietList);
        JPopupMenu dietListMenu = new JPopupMenu();
        dietListMenu.add(dietScrollList);

        dietList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                List<String> selectedValues = dietList.getSelectedValuesList();
                String newText = String.join(", ", selectedValues);
                dietDropDownButton.setText(newText);
            }
        });

        dietDropDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dietListMenu.show(dietDropDownButton, 0, dietDropDownButton.getHeight()); // gimmicky behaviour
            }
        });
        return dietDropDownButton;
    }

    public void setDietPanel(String[] dietOptions) {
        this.dietPanel = this.getDietPanel(dietOptions);
    }

    public SignupViewBuilder addDietPanel(String[] dietOptions) {
        this.setDietPanel(dietOptions);
        this.signupPanel.add(this.dietPanel);
        return this;
    }

    public JPanel getRestrictionPanel(String[] restrictionOptions) {
        JPanel restrictionPanel = new JPanel();
        JLabel restrictionLabel = new JLabel("Dietary Restriction(s):");

        JList<String> restrictionList = new JList<>(restrictionOptions);
        restrictionList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton restrictionDropDownButton = getRestrictionDropDownButton(restrictionList);

        restrictionPanel.add(restrictionLabel);
        restrictionPanel.add(restrictionDropDownButton);

        return restrictionPanel;
    }

    @NotNull
    private static JButton getRestrictionDropDownButton(JList<String> restrictionList) {// why are we able to extract this
        JButton restrictionDropDownButton = new JButton("Click to see list of dietary restrictions!");
        JScrollPane restrictionScrollList = new JScrollPane(restrictionList);
        JPopupMenu restrictionListMenu = new JPopupMenu();
        restrictionListMenu.add(restrictionScrollList);

        restrictionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                List<String> selectedValues = restrictionList.getSelectedValuesList();
                String newText = String.join(", ", selectedValues);
                restrictionDropDownButton.setText(newText);
            }
        });

        restrictionDropDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restrictionListMenu.show(restrictionDropDownButton, 0, restrictionDropDownButton.getHeight()); // gimmicky behaviour
            }
        });
        return restrictionDropDownButton;
    }

    public void setRestrictionPanel(String[] restrictionOptions) {
        this.restrictionPanel = this.getRestrictionPanel(restrictionOptions);
    }

    public SignupViewBuilder addRestrictionPanel(String[] restrictionOptions) {
        this.setRestrictionPanel(restrictionOptions);
        this.signupPanel.add(this.restrictionPanel);
        return this;
    }

    public JPanel getGoalPanel(String[] goalOptions) {
        JPanel goalPanel = new JPanel();
        JLabel goalLabel = new JLabel("Please input your main nutrition goal:");
        JComboBox<String> goalList = new JComboBox<>(goalOptions);

        goalPanel.add(goalLabel);
        goalPanel.add(goalList);

        return goalPanel;
    }

    public void setGoalPanel(String[] goalOptions) {
        this.goalPanel = this.getGoalPanel(goalOptions);
    }

    public SignupViewBuilder addGoalPanel(String[] goalOptions) {
        this.setGoalPanel(goalOptions);
        this.signupPanel.add(this.goalPanel);
        return this;
    }

    public JButton getLoginButton() {      // add functionality later
        JButton loginButton = new JButton("Go to Log In");
        return loginButton;
    }

    // maybe do to this function like I did the loginButton in loginView
    public JButton getSignupButton(JComboBox<String> dayField, JComboBox<String> monthField,
                                   JComboBox<String> yearField, JTextField wholeHeightField, JTextField decHeightField,
                                   JTextField wholeWeightField, JTextField decWeightField, JList<String> dietField,
                                   JComboBox<String> goalField, JTextField usernameField, JTextField passwordField,
                                   JList<String> restrictionField) {

        JButton signupButton = new JButton("Sign Up");

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignupDataAccessInterface signupDataAccessObject = new TempDAOInMemory();
                SignupOutputBoundary signupPresenter = new SignupPresenter(new mainView());
                SignupInputBoundary signupInteractor = new SignupInteractor(signupDataAccessObject, signupPresenter);
                SignupController signupController = new SignupController(signupInteractor);

                int dayOfBirth = dayField.getSelectedIndex() + 1;       // works since the index corresponds with the info
                int monthOfBirth = monthField.getSelectedIndex() + 1;   // is this bad practice?
                int yearOfBirth = LocalDate.now().getYear() - yearField.getSelectedIndex();
                LocalDate dateOfBirth = LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth);

                float height = getFloatFromFields(wholeHeightField, decHeightField);
                float weight = getFloatFromFields(wholeWeightField, decWeightField);
                String[] diet = dietField.getSelectedValuesList().toArray(new String[0]);
                String goal = (String) goalField.getSelectedItem();   // casting is ok since it's JComboBox<String>
                String username = usernameField.getText();
                String password = passwordField.getText();
                List<String> dietaryRestrictions = restrictionField.getSelectedValuesList();

                signupController.execute(dateOfBirth, height, weight, diet,
                        goal, username, password, dietaryRestrictions);
            }

            public float getFloatFromFields(JTextField wholeField, JTextField decField) {
                int wholeValue = 0;
                if (!wholeField.getText().isEmpty()) {
                    wholeValue = Integer.parseInt(wholeField.getText());
                }

                int decValue = 0;
                if (!decField.getText().isEmpty()) {
                    decValue = Integer.parseInt(decField.getText()) / 100;
                }

                return wholeValue + decValue;
            }
        });

        return signupButton;
    }

    public JPanel getButtonPanel(JComboBox<String> dayField, JComboBox<String> monthField,
                                 JComboBox<String> yearField, JTextField wholeHeightField, JTextField decHeightField,
                                 JTextField wholeWeightField, JTextField decWeightField, JList<String> dietField,
                                 JComboBox<String> goalField, JTextField usernameField, JTextField passwordField,
                                 JList<String> restrictionField) {
        JPanel buttonPanel = new JPanel();
        JButton signupButton = getSignupButton(dayField, monthField, yearField, wholeHeightField,
                decHeightField, wholeWeightField, decWeightField,
                dietField, goalField, usernameField, passwordField,
                restrictionField);
        JButton loginButton = getLoginButton();

        buttonPanel.add(signupButton);
        buttonPanel.add(loginButton);

        return buttonPanel;
    }

    public void setButtonPanel() {
        List<JComboBox<String>> infoComboBoxes = this.getSignupInfoComboBoxes();
        List<JTextField> infoTextFields = this.getSignupInfoTextFields();
        List<JList<String>> infoLists = this.getSignupInfoLists();

        this.buttonPanel = this.getButtonPanel(infoComboBoxes.get(0), infoComboBoxes.get(1), infoComboBoxes.get(2),
                infoTextFields.get(0), infoTextFields.get(1), infoTextFields.get(2), infoTextFields.get(3),
                infoLists.get(0), infoComboBoxes.get(3), infoTextFields.get(4), infoTextFields.get(5),
                infoLists.get(1));     // unclear, bad practice?
    }

    public SignupViewBuilder addButtonPanel() {
        this.setButtonPanel();
        this.signupPanel.add(this.buttonPanel);
        return this;
    }

    private List<JComboBox<String>> getSignupInfoComboBoxes() {
        JComboBox<String> dayField = (JComboBox<String>) this.dobPanel.getComponent(2);    // maybe bad practice
        JComboBox<String> monthField = (JComboBox<String>) this.dobPanel.getComponent(1);
        JComboBox<String> yearField = (JComboBox<String>) this.dobPanel.getComponent(3);
        JComboBox<String> goalField = (JComboBox<String>) this.goalPanel.getComponent(1);

        return Arrays.asList(dayField, monthField, yearField, goalField);
    }

    private List<JTextField> getSignupInfoTextFields() {
        JTextField wholeHeightField = (JTextField) this.heightPanel.getComponent(1);
        JTextField decHeightField = (JTextField) this.heightPanel.getComponent(3);
        JTextField wholeWeightField = (JTextField) this.heightPanel.getComponent(1);
        JTextField decWeightField = (JTextField) this.heightPanel.getComponent(3);
        JTextField usernameField = (JTextField) this.usernamePanel.getComponent(1);

        JTextField passwordField = new JTextField();
        if (this.passwordPanel.getComponent(1) instanceof JPasswordField) {
            JPasswordField tempPasswordField = (JPasswordField) this.passwordPanel.getComponent(1);
            String password = new String(tempPasswordField.getPassword());
            passwordField.setText(password);
        } else {
            passwordField = (JTextField) this.passwordPanel.getComponent(1);
        }

        return Arrays.asList(wholeHeightField, decHeightField, wholeWeightField,
                decWeightField, usernameField, passwordField);
    }

    private List<JList<String>> getSignupInfoLists() {
        JButton dietButton = (JButton) this.dietPanel.getComponent(1);        // definitely doing too much
        String[] dietArray = dietButton.getText().split(", ");
        JList<String> dietField = new JList<>(dietArray);
        dietField.setSelectedIndices(IntStream.range(0, dietField.getModel().getSize()).toArray());

        // definitely doing too much again
        JButton restrictionButton = (JButton) this.restrictionPanel.getComponent(1);
        String[] restrictionArray = restrictionButton.getText().split(", ");
        JList<String> restrictionField = new JList<>(restrictionArray);
        restrictionField.setSelectedIndices(IntStream.range(0, restrictionField.getModel().getSize()).toArray());

        return Arrays.asList(dietField, restrictionField);
    }

    public JFrame build() {
        this.signupFrame.setContentPane(this.signupPanel);
        this.signupFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        return this.signupFrame;
    }
}*/
