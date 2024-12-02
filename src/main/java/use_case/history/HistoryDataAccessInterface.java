package use_case.history;

import data.DayInfo;
import org.json.JSONObject;

import java.time.LocalDate;

public interface HistoryDataAccessInterface {

    boolean DayExists(String date, String username);

    JSONObject loadFoodInfo(String username, String date);

}
