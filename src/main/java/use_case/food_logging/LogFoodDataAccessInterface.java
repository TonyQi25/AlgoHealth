package use_case.food_logging;
import data.AccountInfo;
import data.Food;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;

public interface LogFoodDataAccessInterface {
    void saveFood (String username, String password, Food foodIntake, Integer fdcID);

    List<Food> loadFoodInfo(String userName, LocalDate date);
}
