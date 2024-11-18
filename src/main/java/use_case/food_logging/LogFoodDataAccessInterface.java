package use_case.food_logging;
import data.AccountInfo;
import data.Food;
import org.json.JSONObject;

public interface LogFoodDataAccessInterface {
    JSONObject saveFood (AccountInfo user, Food foodIntake) throws DataAccessException;

    JSONObject loadFoodInfo(AccountInfo user) throws DataAccessException;
}
