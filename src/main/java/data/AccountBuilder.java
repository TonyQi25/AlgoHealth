package data;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;

public class AccountBuilder {
    private AccountInfo accountInfo;


    public AccountBuilder(JSONObject accountInfo) {
        String accountUsername = accountInfo.getString("username");
        String accountPassword = accountInfo.getString("password");
        LocalDate accountDOB =  LocalDate.parse(accountInfo.getString("date of birth"));
        float accountHeight = accountInfo.getFloat("height");
        float accountWeight = accountInfo.getFloat("weight");
        String[] accountDiet = (String[])accountInfo.get("diet");
        List<String> accountRestrictions = (List<String>) accountInfo.get("restrictions");
        String accountGoal = accountInfo.getString("goal");
        AccountInfo newAccountInfo = new AccountInfo(accountDOB, accountHeight, accountWeight, accountDiet, accountGoal,
                accountUsername, accountPassword, accountRestrictions);
        this.accountInfo = newAccountInfo;
    }

    public void addFoodLog(JSONObject accountInfo){
        String[] fdcIDs = JSONObject.getNames(accountInfo.getJSONObject("currentDayFoodLog"));


    }
}
