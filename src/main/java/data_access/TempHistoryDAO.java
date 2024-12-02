package data_access;

import org.json.JSONObject;
import use_case.history.HistoryDataAccessInterface;
import use_case.removeFood.RemoveFoodDataAccessInterface;

public class TempHistoryDAO implements HistoryDataAccessInterface, RemoveFoodDataAccessInterface {

    @Override
    public boolean DayExists(String date, String username) {
        return false;
    }

    @Override
    public JSONObject loadFoodInfo(String username, String date) {
        return null;
    }

    @Override
    public Integer foodExists(String viewingDate, String username, String foodName) {
        return 123123;
    }

    @Override
    public boolean removeFood(String viewingDate, String username, String password, String fdcID) {
        return false;
    }

}
