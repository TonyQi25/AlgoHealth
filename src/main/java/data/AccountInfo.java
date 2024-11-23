package data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountInfo {

    public static final String USERNAME_PREFIX = "ALGOHEALTH_";

    private LocalDate dateOfBirth;  // change this to date of birth
    private float height;
    private float weight;
    private String[] diet;
    private String goal;
    private String username;
    private String password;
    private List<DayInfo> days;
    private List<String> dietaryRestrictions;

    public AccountInfo(LocalDate dateOfBirth, float height, float weight, String[] diet, String goal,
                       String username, String password, List<String> dietaryRestrictions) {
        this.dateOfBirth = dateOfBirth;
        this.height = height;
        this.weight = weight;
        this.diet = diet;
        this.goal = goal;
        this.username = username;
        this.password = password;
        this.dietaryRestrictions = dietaryRestrictions;

        this.days = new ArrayList<>();
        this.days.add(new DayInfo(LocalDate.now()));
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

    public List<DayInfo> getDays() {
        return days;
    }

    public List<String> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setDiet(String[] diet) {
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

    public String toString() {
        JSONObject accountStringObject = new JSONObject();

        accountStringObject.put("date of birth", this.getDateOfBirth());
        accountStringObject.put("height", this.getHeight());
        accountStringObject.put("weight", this.getWeight());
        accountStringObject.put("diet", this.getDiet());
        accountStringObject.put("goal", this.getGoal());
        accountStringObject.put("username", this.getUsername());
        accountStringObject.put("password", this.getPassword());
        accountStringObject.put("restrictions", this.getDietaryRestrictions());

        return accountStringObject.toString();
    }

    public static AccountInfo fromJSONString(String accountString) {
        System.out.println(accountString);

        AccountInfo newAccount = new AccountInfo(LocalDate.now(), 0, 0, new String[0],
                "", "", "", new ArrayList<>());
        JSONObject accountObject = new JSONObject(accountString);

        String dobString = (String) accountObject.get("date of birth");
        String[] dobStrings = dobString.split("-");
        int yearOfBirth = Integer.parseInt(dobStrings[0]);
        int monthOfBirth = Integer.parseInt(dobStrings[1]);
        int dayOfBirth = Integer.parseInt(dobStrings[2]);
        newAccount.setDateOfBirth(LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth));

        float height = ((Integer) accountObject.get("height")).floatValue();
        newAccount.setHeight(height);

        float weight = ((Integer) accountObject.get("weight")).floatValue();
        newAccount.setWeight(weight);

        JSONArray dietJSONArray = (JSONArray) accountObject.get("diet");
        String[] diet = dietJSONArray.toList().toArray(new String[0]);       // I do not like the yellow squiggle!!
        newAccount.setDiet(diet);

        newAccount.setGoal((String) accountObject.get("goal"));
        newAccount.setUsername((String) accountObject.get("username"));
        newAccount.setPassword((String) accountObject.get("password"));

        // converting from list to array to list??? probably bad
        JSONArray restrictionsJSONArray = (JSONArray) accountObject.get("restrictions");
        List<String> restrictions = List.of(restrictionsJSONArray.toList().toArray(new String[0]));
        newAccount.setDietaryRestrictions(restrictions);

        System.out.println(newAccount);

        return newAccount;
    }
}