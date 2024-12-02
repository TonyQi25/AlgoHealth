package api;

import data.Food;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import static api.FoodDataCentralSearchDAO.genMyApiKey;

public class PopulateUtility {

    public static Food foodFromResultUsda(JSONObject result) {
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
            String unitName = fresNutrients.getJSONObject(i).getJSONObject("nutrient").getString("unitName");
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
            else if (unitName.equals("kcal") && (nutrientName.equals("Energy") || nutrientName.contains("Energy"))) {
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

    public static HashMap<String, Object> tempGenerator(JSONArray fresNutrients, int i, String microsYES) {
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("amount per 100", fresNutrients.getJSONObject(i).getDouble("amount"));
        temp.put("unitName", fresNutrients.getJSONObject(i).getString("unitName"));
        return temp;
    }

    public static Food createFood(JSONObject result) {
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
            String nutrientName = fresNutrients.getJSONObject(i).getString("name");
            String unitName = fresNutrients.getJSONObject(i).getString("unitName");
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
            else if (unitName.equals("KCAL") && (nutrientName.equals("Energy") || nutrientName.contains("Energy"))) {
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

    public static Food createFood(JSONObject result, String microsYES) {
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
        HashMap<String, HashMap<String, Object>> micros = new HashMap<>();
        while (i < fresNutrients.length() && j >= 0) {
            String nutrientName = fresNutrients.getJSONObject(i).getString("name");
            String unitName = fresNutrients.getJSONObject(i).getString("unitName");
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
            else if (unitName.equals("KCAL") && (nutrientName.equals("Energy") || nutrientName.contains("Energy"))) {
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
            else if (nutrientName.equals("Vitamin A IU")) {
                HashMap<String, Object> temp = tempGenerator(fresNutrients, i);
                micros.put("Vitamin A IU", temp);
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


    public static void main(String[] args) {
/*        FoodDataCentralSearchDAO usdaObj = new FoodDataCentralSearchDAO(genMyApiKey("myFDCApiKey.txt"));
        Integer[] array = {203,204,205,208, 268, 957, 958};
        //JSONObject result = usdaObj.getFoodByFdcId(173410, array);
        JSONObject result = usdaObj.getFoodByFdcId(2514744, array);
        Food newFood = createFood(result);
        int i7 = 7;*/
/*        FoodDataCentralSearchDAO usdaObj = new FoodDataCentralSearchDAO(genMyApiKey("myFDCApiKey.txt"));
        // Menaquinone-4=428
        Integer[] MACRO_AND_ESSENTIAL_MICROS = {203, 204, 205, 208, 268, 957, 318, 401, 324, 323, 430, 428};
        JSONObject result = usdaObj.getFoodByFdcId(2514744, MACRO_AND_ESSENTIAL_MICROS);
        int y = 0;
        HashMap<String, Integer> foodMap = usdaObj.searchComplexFood("kale");
        JSONObject result2 = usdaObj.getFoodByFdcId(174730, MACRO_AND_ESSENTIAL_MICROS);
        JSONObject result23 = usdaObj.getFoodByFdcId(168421, MACRO_AND_ESSENTIAL_MICROS);
        int yyyy = 6;*/
    }
    }

