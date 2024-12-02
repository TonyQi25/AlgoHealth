package use_case.login;

import data.DayInfo;

import java.util.List;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String username;
    private final String password;
    private final double calories;
    private final double protein;
    private final double carbs;
    private final double fat;

    public LoginOutputData(String username, String password, double calories,
                           double protein, double carbs, double fat) {
        this.username = username;
        this.password = password;
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFat() {
        return fat;
    }
}
