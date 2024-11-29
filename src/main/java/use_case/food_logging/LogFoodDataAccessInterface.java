package use_case.food_logging;
import data.AccountInfo;
import data.Food;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;

public interface LogFoodDataAccessInterface {
    void saveFood (String userName, String password, Food foodIntake);

    List<Food> loadFoodInfo(String userName, LocalDate date);
}
