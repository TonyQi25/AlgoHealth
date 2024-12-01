package use_case.select_from_food_options;

import org.json.JSONObject;

public interface SelectSearchDataAccessInterface {

    JSONObject getFoodByFdcId(Integer fdcId, Integer[] macroSpecifier);


}
