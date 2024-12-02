package data_access;

import api.GradeHelper;
import data.AccountInfo;
import data.DayInfo;
import data.Food;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import use_case.food_logging.LogFoodDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.logout.LogoutDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;

import java.time.LocalDate;
import java.util.List;

public class GradeAccountDAO implements SignupDataAccessInterface, LoginDataAccessInterface,
        LogoutDataAccessInterface, LogFoodDataAccessInterface {

    public void createAccount(String username, String password) {
        GradeHelper.postUser(username, password);
    }

    @Override
    public AccountInfo get(String username) {
        return GradeHelper.getUser(username);
    }

    @Override
    public void put(String username, String password, AccountInfo accountInfo) {
        GradeHelper.setUserInfo(username, password, accountInfo);
    }

    @Override
    public boolean existsByName(String username) {
        return GradeHelper.checkUsernameExists(username);
    }

    @Override
    public void save(AccountInfo account) {
        GradeHelper.setUserInfo(account.getUsername(), account.getPassword(), account);
    }

    @Override
    public String getCurrentUsername() {
        return "";
    }

    @Override
    public void setCurrentUsername(String username) {

    }
    //Searches for food based on food name
    public Integer FoodExists(String date, String username, String foodName) {
        if (this.DayExists(date, username) == true){
            JSONObject jsonFoodLog = GradeHelper.getSingleDayJSONFoodLog(username, date);
            String[] fdcIDs = JSONObject.getNames(jsonFoodLog);
            Integer id = getExistingID(foodName, fdcIDs, jsonFoodLog);
            if (id != null) return id;
        }
        return null;
            //JSONObject foodSearchResult = fdcSearchDAO.getFoodByFdcId(Integer.valueOf(fdcID));
            //Food foodItem = makeFood.createFood(foodSearchResult);
            //if (foodItem.getDescription().equals(foodName){
            //return Integer.valueOf(fdcID);
            //}
//            if(fdcID.equals(Integer.valueOf(id))){
//                return fdcID;
//            }
//        }
    }
    //Searches for food by fdcID
    public boolean FoodExistsByID(String date, String username, String fdcID) {
        if (this.DayExists(date, username) == true) {
            JSONObject jsonFoodLog = GradeHelper.getSingleDayJSONFoodLog(username, date);
            return jsonFoodLog.has(fdcID);
        }
        return false;
    }

    @Nullable
    private static Integer getExistingID(String foodName, String[] fdcIDs, JSONObject jsonFoodLog) {
        if (fdcIDs == null){
            return null;
        }
        for (String id : fdcIDs) {
            JSONObject foodInfo = jsonFoodLog.getJSONObject(id);
            if(foodInfo.get("name").equals(foodName)){
                return Integer.valueOf(id);
            }
        }
        return null;
    }

    //saves the food information for current day
    @Override
    public void saveFood(String date, String username, String password, Food foodIntake, Integer fdcID){
        AccountInfo user = GradeHelper.getUser(username);
        JSONObject currFoodLog = GradeHelper.getJSONFoodLog(username);
        JSONObject newFoodLog = GradeHelper.copyEntireFoodLog(currFoodLog);
        JSONObject newFoodInfo = new JSONObject();
        newFoodInfo.put("name", foodIntake.getDescription());
        JSONObject newDayFoodLog = new JSONObject();
        double newWeight = foodIntake.getWeight();

        if (this.DayExists(date, username)){
            JSONObject DayFoodLog = GradeHelper.getSingleDayJSONFoodLog(username, date.toString());
            newDayFoodLog = GradeHelper.copySingleDayJSONFoodLog(DayFoodLog);
            Integer foodExist = this.FoodExists(date, username, foodIntake.getDescription());
            if (foodExist != null){
                JSONObject currFoodInfo = DayFoodLog.getJSONObject(Integer.toString(foodExist));
                newWeight += currFoodInfo.getFloat("weight");
            }
        }
        newFoodInfo.put("weight", newWeight);
        Food newFood = new Food(foodIntake.getDescription(), newWeight, foodIntake.getCalories());
        newFood.setNutritionFacts(foodIntake.getMicroNutrients(), foodIntake.getMacroNutrients());
        newFoodInfo.put("totalCalories", newFood.getTotalCalories());
        newFoodInfo.put("totalFat", newFood.getTotalFat());
        newFoodInfo.put("totalCarb", newFood.getTotalCarb());
        newFoodInfo.put("totalProtein", newFood.getTotalProtein());
        newDayFoodLog.put(Integer.toString(fdcID), newFoodInfo);
        newFoodLog.put(date, newDayFoodLog);
        GradeHelper.addFoodUserInfo(username, password, user, newFoodLog, date);
    }

    //loads the food information for requested date
    @Override
    public JSONObject loadFoodInfo(String username, String date){
        if(this.DayExists(date, username)){
            JSONObject dayFoodLog = GradeHelper.getSingleDayJSONFoodLog(username, date);
            return dayFoodLog;
        }
        return null;
    }

    public boolean DayExists(String date, String username){
        JSONObject FoodLog = GradeHelper.getJSONFoodLog(username);
        return FoodLog.has(date);
    }

    public boolean removeFood(String date, String username, String password, String fdcID){
        if (this.FoodExistsByID(date,username, fdcID)){
            AccountInfo user = GradeHelper.getUser(username);
            JSONObject currFoodLog = GradeHelper.getJSONFoodLog(username);
            JSONObject newFoodLog = GradeHelper.copyEntireFoodLog(currFoodLog);
            JSONObject currDayFoodLog = GradeHelper.getSingleDayJSONFoodLog(username, date);
            JSONObject newDayFoodLog = GradeHelper.copySingleDayJSONFoodLog(currDayFoodLog);
            Object removedFood = newDayFoodLog.remove(fdcID);
            newFoodLog.put(date, newDayFoodLog);
            GradeHelper.addFoodUserInfo(username, password, user, newFoodLog, date);
            return true;
        }
        return false;
    }
}
