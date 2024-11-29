package data_access;

import api.GradeHelper;
import data.AccountInfo;
import data.DayInfo;
import data.Food;
import okhttp3.*;
import org.json.JSONObject;
import use_case.food_logging.DataAccessException;
import use_case.food_logging.LogFoodDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.logout.LogoutDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public Integer FoodExists(String date, String username, String foodName, Integer fdcID) {
        JSONObject jsonFoodLog = GradeHelper.getJSONFoodLog(username);
        String[] fdcIDs = JSONObject.getNames(jsonFoodLog);
        //for (String id : fdcIDs) {
            //JSONObject foodSearchResult = fdcSearchDAO.getFoodByFdcId(Integer.valueOf(fdcID));
            //Food foodItem = makeFood.createFood(foodSearchResult);
            //if (foodItem.getDescription().equals(foodName){
            //return Integer.valueOf(fdcID);
            //}
//            if(fdcID.equals(Integer.valueOf(id))){
//                return fdcID;
//            }
//        }
        return null;
    }

    //saves the food information for current day
    @Override
    public void saveFood(String username, String password, Food foodIntake, Integer fdcID){
        AccountInfo user = GradeHelper.getUser(username);
        List<DayInfo> dayInfos = user.getDays();
        DayInfo currDay = dayInfos.get(0);
        JSONObject currFoodLog = GradeHelper.getJSONFoodLog(username);
        JSONObject newFoodLog = GradeHelper.copyJSONFoodLog(currFoodLog);
        Integer foodExist = this.FoodExists(LocalDate.now().toString(), username, foodIntake.getDescription(), fdcID);
        float newWeight = foodIntake.getWeight();
        if (foodExist != null){
            newWeight = currFoodLog.getFloat(Integer.toString(foodExist));
        }
        newFoodLog.put(Integer.toString(fdcID), newWeight);
        GradeHelper.setUserInfo(username, password, user, newFoodLog);
    }

    //loads the food information for requested date
    @Override
    public List<Food> loadFoodInfo(String username, LocalDate date){
        AccountInfo user = GradeHelper.getUser(username);
        List<DayInfo> days = user.getDays();
        DayInfo currDay = days.get(0);
        return currDay.getFoodLog();
    }
}
