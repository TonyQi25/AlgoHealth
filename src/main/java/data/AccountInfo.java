package data;

import java.util.List;

class AccountInfo {
    private int age;  // change this to date of birth
    private final float height;
    private float weight;
    private String diet;
    private String goal;
    private String username;
    private String password;
    private List<DayInfo> days;
    private List<String> dietaryRestrictions;

    public AccountInfo(int age, float height, float weight, String diet, String goal,
                       String username, String password, List<Food> dietaryRestrictions) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.diet = diet;
        this.goal = goal;
        this.username = username;
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public String getDiet() {
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

    public List<DayInfo> getDays() {
        return days;
    }

    public List<Food> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDays(List<DayInfo> days) {
        this.days = days;
    }

    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public void increaseAge() {  // change this along with the DOB
        this.age++;
    }
}