package data;

import api.FoodDataCentralSearchDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AccountBuilder {

    private AccountInfo accountInfo;
    private final FoodDataCentralSearchDAO fdcSearchDAO = new FoodDataCentralSearchDAO(
            FoodDataCentralSearchDAO.genMyApiKey("myFDCApiKey.txt"));
    //private final PopulateUtility makeFood = new PopulateUtility();


    public AccountBuilder(JSONObject accountInfo) {
        String accountUsername = accountInfo.getString("username");
        String accountPassword = accountInfo.getString("password");
        LocalDate accountDOB =  LocalDate.parse(accountInfo.getString("date of birth"));
        float accountHeight = accountInfo.getFloat("height");
        float accountWeight = accountInfo.getFloat("weight");
        String[] accountDiet = this.JSONArraytoList((JSONArray)accountInfo.get("diet"));
        List<String> accountRestrictions = this.JSONArraytoStringList((JSONArray)accountInfo.get("restrictions"));
        String accountGoal = accountInfo.getString("goal");
        AccountInfo newAccountInfo = new AccountInfo(accountDOB, accountHeight, accountWeight, accountDiet, accountGoal,
                accountUsername, accountPassword, accountRestrictions);
        this.accountInfo = newAccountInfo;
    }

    public void addFoodLog(JSONObject accountInfo){
        JSONObject currDayFoodLog = accountInfo.getJSONObject("currentDayFoodLog");
        String[] fdcIDs = JSONObject.getNames(currDayFoodLog);
        List<Food> foodLog = new ArrayList<>();
        //Commented out for now, have to combine with Matt's recent version of fdcsearch
        //for (String fdcID: fdcIDs){
            //JSONObject foodSearchResult = fdcSearchDAO.getFoodByFdcId(Integer.valueOf(fdcID));
            //Food foodItem = makeFood.createFood(foodSearchResult);
            //foodItem.setWeight(currDayFoodLog.getFloat(fdcID));
            //foodItem.setTotalCarb();
            //foodItem.setTotalProtein();
            //foodItem.setTotalFat();
            //foodItem.setTotalCalories();
            //foodLog.add(foodItem);
        //}
        DayInfo newDay = new DayInfo(LocalDate.now());
        newDay.setFoodLog(foodLog);
        newDay.setTotalCalories();
        List<DayInfo> dayInfos = new ArrayList<>();
        dayInfos.add(newDay);
        this.accountInfo.setDays(dayInfos);
    }
    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public String[] JSONArraytoList(JSONArray jsonArray){
        String[] dietList = new String[jsonArray.length()];
        int i = 0;
        for (Object diet: jsonArray){
            dietList[i] = (String) diet;
            i++;
        }
        return  dietList;
    }

    public List<String> JSONArraytoStringList(JSONArray jsonArray){
        List<String> restrictions = new ArrayList<>();
        for (Object restriction: jsonArray){
            restrictions.add((String) restriction);
        }
        return  restrictions;
    }
}
