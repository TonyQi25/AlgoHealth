package use_case.food_logging;

import data.AccountInfo;
import data.Food;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.List;

/**
 * The interface of the DAO for the Log Food Use Case.
 */

public interface LogFoodDataAccessInterface {
    /**
     * Updates the user information in the grade api.
     * @param username the username of the user currently logged in
     * @param password the password of the user currently logged in
     * @param foodIntake the food being recorded
     * @param date the date for which the food is being recorded for
     * @param fdcID the fdcID associated with the food being recorded
     */
    void saveFood(String date, String username, String password, Food foodIntake, Integer fdcID);

    /**
     * Returns the food information from the grade api.
     * @param userName the username of the user currently logged in
     * @param date the date for which the food information is being loaded for
     * @return the food log for the user and date
     */
    JSONObject loadFoodInfo(String userName, String date);
}
