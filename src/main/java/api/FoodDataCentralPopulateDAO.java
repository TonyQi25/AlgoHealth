package api;

import data.Food;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class FoodDataCentralPopulateDAO {

    public static Food foodFromFirstResultUsda(String food, FoodDataCentralSearchDAO usdaObj) {
            JSONObject firstResult = usdaObj.callUsdaSearch(food);
            String standardUnit = firstResult.getString("servingSizeUnit");
            String description = firstResult.getString("description");
            JSONArray fresNutrients = firstResult.getJSONArray("foodNutrients");
            // do some initial instance variable assignment for the Food object we're creating.
            // foodTbc is short for foodToBeCreated.
            Food foodTbc = new Food();
            foodTbc.setStandardUnit(standardUnit);
            foodTbc.setDescription(description);
            // Loop through until we've found protein, carbs, fat, and calories amounts per 100 standard units.
            // Record the units of these as well.
            int i = 0;
            int j = 0;
            HashMap<String, HashMap<String, Object>> macros = new HashMap<>();
            while (i < fresNutrients.length() && j < 4) {
                String nutrientName = fresNutrients.getJSONObject(i).getString("nutrientName");
                if (nutrientName.equals("Protein")) {
                    HashMap<String, Object> temp = tempWithAmountAndUnits(standardUnit, fresNutrients, i);
                    macros.put("Protein", temp);
                    i += 1;
                    j += 1;
                }
                else if (nutrientName.equals("Carbohydrate, by difference") || nutrientName.equals("Starch")) {
                    HashMap<String, Object> temp = tempWithAmountAndUnits(standardUnit, fresNutrients, i);
                    macros.put("Carbohydrate", temp);
                i += 1;
                j += 1;
                }
                else if (nutrientName.equals("Energy")) {
                    HashMap<String, Object> temp = tempWithAmountAndUnits(standardUnit, fresNutrients, i);
                    foodTbc.setCalories(temp);
                i += 1;
                j += 1;
                }
                else if (nutrientName.equals("Total lipid (fat)")) {
                    HashMap<String, Object> temp = tempWithAmountAndUnits(standardUnit, fresNutrients, i);
                    macros.put("Fat", temp);
                    i += 1;
                    j += 1;
                }
                else {
                    i += 1;
                }
            }
            foodTbc.setMacroNutrients(macros);
            return foodTbc;
    }
    public static Food foodFromResultUsda(JSONObject result, FoodDataCentralSearchDAO usdaObj) {
        String description = result.getString("description");
        JSONArray fresNutrients = result.getJSONArray("foodNutrients");
        // foodTbc is short for foodToBeCreated.
        Food foodTbc = new Food();
        foodTbc.setDescription(description);
        // Loop through until we've found protein, carbs, fat, and calories amounts per 100 standard units.
        // Record the units of these as well.
        int i = 0;
        int j = 0;
        HashMap<String, HashMap<String, Object>> macros = new HashMap<>();
        while (i < fresNutrients.length() && j < 4) {
            String nutrientName = fresNutrients.getJSONObject(i).getJSONObject("nutrient").getString("name");
            if (nutrientName.equals("Protein")) {
                HashMap<String, Object> temp = tempGenerator(fresNutrients, i);
                macros.put("Protein", temp);
                i += 1;
                j += 1;
            }
            else if (nutrientName.equals("Carbohydrate, by difference")) {
                HashMap<String, Object> temp = tempGenerator(fresNutrients, i);
                macros.put("Carbohydrate", temp);
                i += 1;
                j += 1;
            }
            else if (nutrientName.equals("Energy") || nutrientName.contains("Energy")) {
                HashMap<String, Object> temp = tempGenerator(fresNutrients, i);
                foodTbc.setCalories(temp);
                i += 1;
                j += 1;
            }
            else if (nutrientName.equals("Total lipid (fat)")) {
                HashMap<String, Object> temp = tempGenerator(fresNutrients, i);
                macros.put("Fat", temp);
                i += 1;
                j += 1;
            }
            else {
                i += 1;
            }


        }
        foodTbc.setMacroNutrients(macros);
        return foodTbc;
    }

    /**
     * Helper function for foodFromResultUsda.
     * @param fresNutrients JSONArray we get from result.
     * @param i Indexing variable. Function is called in while loop.
     * @return amount per 100 and unitName in a HashMap. Intermediate results in building
     * the macroNutrient instance variable for the Food entity we are creating in
     * foodFromResultUsda.
     */
    @NotNull
    public static HashMap<String, Object> tempGenerator(JSONArray fresNutrients, int i) {
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("amount per 100", fresNutrients.getJSONObject(i).getDouble("amount"));
        //temp.put("unitName", fresNutrients.getJSONObject(i).getString("unitName"));
        return temp;
    }

    @NotNull
    public static HashMap<String, Object> tempWithAmountAndUnits(String standardUnit, JSONArray fresNutrients, int i) {
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("amount per 100" + standardUnit, fresNutrients.getJSONObject(i).getDouble("value"));
        temp.put("unitName", fresNutrients.getJSONObject(i).getString("unitName"));
        return temp;
    }
}

