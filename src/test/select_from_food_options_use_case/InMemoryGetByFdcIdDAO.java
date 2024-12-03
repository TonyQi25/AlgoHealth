package select_from_food_options_use_case;

import api.FoodDataCentralSearchDAO;
import api.PopulateUtility;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.select_from_food_options.SelectSearchDataAccessInterface;

import static api.FoodDataCentralSearchDAO.genMyApiKey;

public class InMemoryGetByFdcIdDAO implements SelectSearchDataAccessInterface {
    @Override
    public JSONObject getFoodByFdcId(Integer fdcId, Integer[] macroSpecifier) {
      /*  JSONObject testObj = new JSONObject();
        testObj.put("foodNutrients", array1);
        JSONArray array1 = new JSONArray();
        array1.put(0, fatObj);
        JSONObject fatObj = new JSONObject();
        fatObj.put("unitName", "G");
        fatObj.put("name", "Total lipid(fat)");
        fatObj.put("amount", 3.26);*/

        // reorodered
        JSONObject fatObj = new JSONObject();
        fatObj.put("unitName", "G");
        fatObj.put("name", "Total lipid(fat)");
        fatObj.put("amount", 3.26);

        JSONArray array1 = new JSONArray();
        array1.put(0, fatObj);

        JSONObject testObj = new JSONObject();
        testObj.put("foodNutrients", array1);
        testObj.put("description", "Beef, New Zealand, imported, manufacturing beef, cooked, boiled");

        return testObj;
    }
/*    @Override
    public JSONObject getFoodByFdcId(Integer fdcId, Integer[] macroSpecifier) {
        JSONObject testObj = new JSONObject();
        JSONObject afterMidJsonObject = new JSONObject();
        afterMidJsonObject.put()
        JSONObject nutrientJsonObj = new JSONObject();
        JSONObject midJsonObj = new JSONObject();
        midJsonObj.put("Calories", afterMidJsonObject);
        nutrientJsonObj.put("name", midJsonObj);
        JSONObject objWithArrayVal = new JSONObject();
        objWithArrayVal.put("nutrients", nutrientJsonObj);
        testObj.put("description", objWithArrayVal);


    }*/
    public static void main(String[] args) {
        final Integer[] MACRO_SPECIFIER_1 = {203, 204, 205, 208, 268, 957, 958};
        SelectSearchDataAccessInterface dao = new FoodDataCentralSearchDAO(genMyApiKey("myFDCApiKey.txt"));
        JSONObject result = dao.getFoodByFdcId(174730, MACRO_SPECIFIER_1);
        //inMemoryFoodSelectionDAO.setCurrFoodEntity(PopulateUtility.createFood(result));
        PopulateUtility.createFood(result);
    }
}
