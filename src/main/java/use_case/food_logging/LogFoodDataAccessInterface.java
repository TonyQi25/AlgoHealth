package use_case.food_logging;
import data.AccountInfo;
import data.Food;
import org.json.JSONObject;

public interface LogFoodDataAccessInterface {
    JSONObject saveFood (String userName, String password, Food foodIntake) throws DataAccessException;

    JSONObject loadFoodInfo(String userName) throws DataAccessException;
}
