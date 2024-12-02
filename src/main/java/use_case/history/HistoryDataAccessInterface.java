package use_case.history;

import data.DayInfo;
import org.json.JSONObject;

import java.time.LocalDate;

/**
 * data access interface for history use case
 */
public interface HistoryDataAccessInterface {
    /**
     * check if day exists in database
     * @param date date to look for
     * @param username username
     * @return boolean to signify if day exists
     */
    boolean DayExists(String date, String username);


    /**
     * load the info for the day
     * @param username username to look for
     * @param date date to look for
     * @return returns a hashmap that maps from id to info about the food
     */
    JSONObject loadFoodInfo(String username, String date);

}
