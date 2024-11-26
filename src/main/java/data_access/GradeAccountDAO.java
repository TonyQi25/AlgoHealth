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

    //saves the food information for current day
    @Override
    public void saveFood(String username, String password, Food foodIntake){
        AccountInfo user = GradeHelper.getUser(username);
        List<DayInfo> days = user.getDays();
        boolean dayInMemory = false;
        for (DayInfo day: days){
            if (day.getDate().equals(LocalDate.now())){
                List<Food> currFoodLog = day.getFoodLog();
                List<Food> newFoodLog = new ArrayList<>();
                for (Food dayFood: currFoodLog){
                    newFoodLog.add(dayFood);
                }
                newFoodLog.add(foodIntake);
                day.setFoodLog(newFoodLog);
                dayInMemory = true;
            }
        }
        if (dayInMemory == false){
            DayInfo newDay = new DayInfo(LocalDate.now());
            ArrayList<Food> newDayFoodLog = new ArrayList<>();
            newDayFoodLog.add(foodIntake);
            newDay.setFoodLog(newDayFoodLog);
        }
        GradeHelper.setUserInfo(user.getUsername(), user.getPassword(), user);
    }

    //loads the food information for requested date
    @Override
    public List<Food> loadFoodInfo(String username, LocalDate date){
        AccountInfo user = GradeHelper.getUser(username);
        List<DayInfo> days = user.getDays();
        boolean dayInMemory = false;
        for (DayInfo day: days){
            if (day.getDate().equals(LocalDate.now())){
                return day.getFoodLog();
            }
        }
        return null;
    }
}
