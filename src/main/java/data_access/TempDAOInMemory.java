package data_access;

import data.AccountInfo;
import data.DayInfo;
import data.Food;
import use_case.food_logging.LogFoodDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
import use_case.logout.LogoutDataAccessInterface;
import use_case.signup.SignupDataAccessInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempDAOInMemory implements LoginDataAccessInterface, LogoutDataAccessInterface, SignupDataAccessInterface
{

    private final Map<String, AccountInfo> accounts = new HashMap<>();
    private String currentUsername;

    public void save(Food food) {
        List<DayInfo> currentDays = accounts.get(currentUsername).getDays();
        int currentDayIndex = currentDays.size() - 1;
        currentDays.get(currentDayIndex).addToFoodLog(food);
        accounts.get(currentUsername).setDays(currentDays);
    }

    public void save(AccountInfo accountInfo) {

    }

    public AccountInfo get(String username) {
        return new AccountInfo(LocalDate.now(), 0, 0,
                new String[]{""}, "", "", "", new ArrayList<>());
    }

    public void put(String username, AccountInfo accountInfo) {

    }

    public boolean existsByName(String username) {
        return true;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

}
