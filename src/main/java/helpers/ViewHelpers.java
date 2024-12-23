package helpers;

import interface_adapter.delete_food.DeleteFoodController;
import interface_adapter.delete_food.DeleteFoodViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ViewHelpers {

    // general helpers
    public static JButton getPasswordToggleButton(JPanel passwordPanel, JTextField passwordShowField,
                                           JPasswordField passwordHideField, int fieldIndex) {
        String SHOW_MESSAGE = "Show";
        String HIDE_MESSAGE = "Hide";
        JButton togglePasswordButton = new JButton(SHOW_MESSAGE);

        togglePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordPanel.getComponent(fieldIndex) instanceof JPasswordField) {
                    String hideText = new String(passwordHideField.getPassword());
                    passwordPanel.remove(fieldIndex);
                    passwordShowField.setText(hideText);
                    passwordPanel.add(passwordShowField, fieldIndex);
                    togglePasswordButton.setText(HIDE_MESSAGE);
                } else {
                    String showText = passwordShowField.getText();
                    passwordPanel.remove(fieldIndex);
                    passwordHideField.setText(showText);
                    passwordPanel.add(passwordHideField, fieldIndex);
                    togglePasswordButton.setText(SHOW_MESSAGE);
                }
            }
        });
        return togglePasswordButton;
    }

    public static JDialog getFailedDialog(String error) {
        JDialog failedFrame = new JDialog();

        JPanel failedLabelPanel = new JPanel();
        JLabel failedLabel = new JLabel(error);
        failedLabelPanel.add(failedLabel);

        JPanel failedButtonPanel = new JPanel();
        JButton failedButton = new JButton("OK");
        failedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                failedFrame.dispose();
            }
        });
        failedButtonPanel.add(failedButton);

        JPanel failedPanel = new JPanel();
        failedPanel.setLayout(new BoxLayout(failedPanel, BoxLayout.Y_AXIS));
        failedPanel.add(failedLabelPanel);
        failedPanel.add(failedButtonPanel);

        failedFrame.setModal(true);
        failedFrame.setContentPane(failedPanel);
        failedFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        failedFrame.pack();

        return failedFrame;
    }
    // end of general helpers

    // sign up helpers
    public static String[] getDobOptions(int min, int max, boolean ascending) {
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

    public static JComboBox<String> getDobBox(int min, int max, boolean ascending) {
        String[] options = getDobOptions(min, max, ascending);
        return new JComboBox<>(options);
    }

    @NotNull
    public static JButton getDietDropDownButton(SignupViewModel signupViewModel, JList<String> dietList) {// why are we able to extract this

        dietList.setSelectedIndex(0);
        signupViewModel.getState().setDiet(dietList.getSelectedValuesList().toArray(new String[0]));

        JButton dietDropDownButton = new JButton("None");
        JScrollPane dietScrollList = new JScrollPane(dietList);
        JPopupMenu dietListMenu = new JPopupMenu();
        dietListMenu.add(dietScrollList);

        dietList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                List<String> selectedValues = dietList.getSelectedValuesList();
                if (isNoneInMultSelection(dietList)) {
                    selectedValues.remove("None");
                }
                String newText = String.join(", ", selectedValues);
                dietDropDownButton.setText(newText);
                signupViewModel.getState().setDiet(selectedValues.toArray(new String[0]));
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

    // maybe instead of manually adding "None" as an option, just adding None to the front of whatever list there is could be an option
    @NotNull
    public static JButton getRestrictionDropDownButton(SignupViewModel signupViewModel,
                                                       JList<String> restrictionList) {
        restrictionList.setSelectedIndex(0);
        signupViewModel.getState().setDietaryRestrictions(restrictionList.getSelectedValuesList());

        JButton restrictionDropDownButton = new JButton("None");
        JScrollPane restrictionScrollList = new JScrollPane(restrictionList);
        JPopupMenu restrictionListMenu = new JPopupMenu();
        restrictionListMenu.add(restrictionScrollList);

        restrictionList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                List<String> selectedValues = restrictionList.getSelectedValuesList();
                if (isNoneInMultSelection(restrictionList)) {
                    selectedValues.remove("None");
                }
                String newText = String.join(", ", selectedValues);
                restrictionDropDownButton.setText(newText);
                signupViewModel.getState().setDietaryRestrictions(selectedValues);
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

    // should this be private or public?
    private static boolean isNoneInMultSelection(JList<String> selectedList) {
        return selectedList.getSelectedIndices().length >= 2
                && selectedList.getSelectedValuesList().contains("None");
    }

    public static JButton getSignupButton(SignupViewModel signupViewModel, SignupController signupController) {

        JButton signupButton = new JButton("Sign Up");

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate dateOfBirth = signupViewModel.getState().getDateOfBirth();
                float height = signupViewModel.getState().getHeight();
                float weight = signupViewModel.getState().getWeight();
                String[] diet = signupViewModel.getState().getDiet();
                String goal = signupViewModel.getState().getGoal();
                String username = signupViewModel.getState().getUsername();
                String password = signupViewModel.getState().getPassword();
                List<String> dietaryRestrictions = signupViewModel.getState().getDietaryRestrictions();

                signupController.execute(dateOfBirth, height, weight, diet,
                        goal, username, password, dietaryRestrictions);
            }

        });

        return signupButton;
    }

    public static JButton getGoToLoginButton(SignupController signupController) {      // add functionality later
        JButton loginButton = new JButton("Go to Log In");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signupController.switchToLogin();
            }
        });
        return loginButton;
    }
    // end of sign up helpers

    // log in helpers
    @NotNull
    public static JButton getLoginButton(LoginViewModel loginViewModel, LoginController loginController) {

        JButton loginButton = new JButton("Log In");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginViewModel.getState().getUsername();
                String password = loginViewModel.getState().getPassword();

                loginController.execute(username, password);
                System.out.println(username);
                System.out.println(password);
            }
        });
        return loginButton;
    }

    public static JButton getGoToSignupButton(LoginController loginController) {
        JButton signupButton = new JButton("Go to Sign Up");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginController.switchToSignup();
                System.out.println("reached go to login");
            }
        });
        return signupButton;
    }
    // end of log in helpers

    // delete food helpers
    public static JButton getDeleteToHistoryButton(DeleteFoodController deleteFoodController) {
        JButton historyButton = new JButton("Go to History");
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFoodController.switchToHistory();
            }
        });
        return historyButton;
    }

    public static JButton getDeleteButton(DeleteFoodViewModel deleteFoodViewModel,
                                          DeleteFoodController deleteFoodController) {
        JButton deleteButton = new JButton("Delete Food");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (deleteFoodViewModel.getState().getSelectedFoods().isEmpty()) {
                    getFailedDialog("No foods selected.");
                } else {
                    JDialog confirmDialog = getDeleteConfirmDialog(deleteFoodViewModel, deleteFoodController);
                    confirmDialog.setVisible(true);
                }
            }
        });
        return deleteButton;
    }

    public static JDialog getDeleteConfirmDialog(DeleteFoodViewModel deleteFoodViewModel,
                                                 DeleteFoodController deleteFoodController) {
        JDialog confirmDeleteDialog = new JDialog();
        JPanel confirmPanel = new JPanel();
        JLabel deleteLabel = new JLabel("Deleting:");
        JList<String> selectedFoods = new JList<>(
                deleteFoodViewModel.getState().getSelectedFoods().toArray(new String[0]));

        JPanel buttonPanel = getDeleteButtonPanel(deleteFoodViewModel, deleteFoodController, confirmDeleteDialog);

        confirmPanel.setLayout(new BoxLayout(confirmPanel, BoxLayout.Y_AXIS));
        confirmPanel.add(deleteLabel);
        confirmPanel.add(selectedFoods);
        confirmPanel.add(buttonPanel);

        confirmDeleteDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        confirmDeleteDialog.setContentPane(confirmPanel);
        confirmDeleteDialog.setModal(true);
        confirmDeleteDialog.pack();

        return confirmDeleteDialog;
    }

    @NotNull
    private static JPanel getDeleteButtonPanel(DeleteFoodViewModel deleteFoodViewModel,
                                               DeleteFoodController deleteFoodController,
                                               JDialog confirmDeleteDialog) {
        JPanel buttonPanel = new JPanel();
        JButton confirmButton = new JButton("Confirm");
        JButton cancelButton = new JButton("Cancel");

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFoodController.execute(deleteFoodViewModel.getState().getSelectedFoods());
                confirmDeleteDialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmDeleteDialog.dispose();
            }
        });

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        return buttonPanel;
    }
}
