package interface_adapter.signup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SignupState {

    private LocalDate dateOfBirth = LocalDate.of(2024, 1, 1);
    private float height = 0;
    private float weight = 0;
    private String[] diet = new String[0];
    private String goal = "";
    private String username = "";
    private String password = "";
    private List<String> dietaryRestrictions = new ArrayList<>();
    private String signupError;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String[] getDiet() {
        return diet;
    }

    public void setDiet(String[] diet) {
        this.diet = diet;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public String getSignupError() {
        return signupError;
    }

    public void setSignupError(String signupError) {
        this.signupError = signupError;
    }
}
