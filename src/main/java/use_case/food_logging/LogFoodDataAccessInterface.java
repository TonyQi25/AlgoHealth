package use_case.food_logging;
import data.AccountInfo;
import data.Food;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;

public interface LogFoodDataAccessInterface {
    void saveFood (LocalDate date, String username, String password, Food foodIntake, Integer fdcID);

    JSONObject loadFoodInfo(String userName, String date);
}
