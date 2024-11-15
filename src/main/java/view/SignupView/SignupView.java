/*
package view.SignupView;

import javax.swing.*;

public class SignupView {

    private final SignupViewBuilder signupViewBuilder;

    public SignupView(SignupViewBuilder signupViewBuilder) {
        this.signupViewBuilder = signupViewBuilder;
    }

    public void generateSignupView() {
        JFrame signupView = signupViewBuilder.addWelcomePanel().
                addUsernamePanel().
                addPasswordPanel().
                addDobPanel().
                addHeightPanel().
                addWeightPanel().
                addDietPanel(new String[] {"None", "Vegan"}).               // placeholder options
                addRestrictionPanel(new String[] {"Lactose Intolerant", "Nuts", "Eggs"}).
                addGoalPanel(new String[] {"Maintain Weight", "Gain Weight", "Lose Weight"}).
                addButtonPanel().
                build();

        signupView.pack();
        signupView.setVisible(true);
    }

    public static void main(String[] args) {
        SignupView signupView = new SignupView(new SignupViewBuilder(new JPanel(), new JFrame("Sign Up")));

        signupView.generateSignupView();
    }

}
*/
