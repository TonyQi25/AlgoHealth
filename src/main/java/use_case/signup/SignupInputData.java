package use_case.signup;

import java.time.LocalDate;
import java.util.List;

public class SignupInputData {

    private final LocalDate dateOfBirth;
    private final float height;
    private final float weight;
    private final String[] diet;
    private final String goal;
    private final String username;
    private final String password;
    private final List<String> dietaryRestrictions;

    public SignupInputData(LocalDate dateOfBirth, float height, float weight,
                           String[] diet, String goal, String username, String password,
                           List<String> dietaryRestrictions) {
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.diet = diet;
        this.goal = goal;
        this.username = username;
        this.password = password;
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public String[] getDiet() {
        return diet;
    }

    public String getGoal() {
        return goal;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }
}
