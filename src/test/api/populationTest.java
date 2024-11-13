package api;

import data.Food;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import static api.populateFromUsda.foodFromResultUsda;
import static api.callUsdaApi.genMyApiKey;
import static org.junit.Assert.assertEquals;

public class populationTest {

    // Use your USDA FDC API key. Using DEMO_KEY at any point causes problems to do with API rate limit.
    final String apiKey = genMyApiKey("myFDCApiKey.txt");
    int i = 0;
    // Running tests individually is recommended for understandability of System.out (printed) data.

    @Test
    void first10FoundationSpecificFDCidTest() {
        // List of 10 foods generated from FDC Api.
        callUsdaApi usdaObj = new callUsdaApi(apiKey);
        HashMap<String, Integer> descriptionToFDCid = usdaObj.first10FoundationFoods("pork");

        // User is shown list of 10 foundation foods and prompted to choose one by entering the corresponding fdcId.
        // Selection from dropdown list on a view will be how it is actually implemented.
        System.out.println(descriptionToFDCid);
        System.out.println("Enter fdcId:");
        // User enters fdcId.
        Integer fdcId = 746774;

        // Create the Food entity from selection.
        JSONObject jsonObj = usdaObj.getFoodByFdcId(fdcId);
        Food newFood = foodFromResultUsda(jsonObj, usdaObj);

        // Check Food entity has correct macronutrient values.
        // Check Protein entry in macroNutrients.
        Double newFoodProtein = (Double) newFood.getMacroNutrients().get("Protein").get("amount per 100");
        String newFoodUnits = (String) newFood.getMacroNutrients().get("Protein").get("unitName");
        assertEquals(Double.valueOf(newFoodProtein), Double.valueOf(8.88));
        assertEquals(newFoodUnits, "G");

        // Check Calories instance variable.
        Double newFoodCalories = (Double) newFood.getCalories().get("amount per 100");
        newFoodUnits = (String) newFood.getCalories().get("unitName");
        assertEquals(Double.valueOf(newFoodCalories), Double.valueOf(1090.0));
        assertEquals(newFoodUnits, "kJ");

        // Check carbohydrates in macroNutrients instance variable.
        Double newFoodCarbs = (Double) newFood.getMacroNutrients().get("Carbohydrate").get("amount per 100");
        newFoodUnits = (String) newFood.getMacroNutrients().get("Carbohydrate").get("unitName");
        assertEquals(Double.valueOf(newFoodCarbs), Double.valueOf(25.5));
        assertEquals(newFoodUnits, "G");

        //Check total fat in macroNutrients instance variable.
        Double newFoodFat = (Double) newFood.getMacroNutrients().get("Fat").get("amount per 100");
        newFoodUnits = (String) newFood.getMacroNutrients().get("Fat").get("unitName");
        assertEquals(Double.valueOf(newFoodFat), Double.valueOf(13.6));
        assertEquals(newFoodUnits, "G");

    }

    @Test
    void first10FoundationRandomFDCidFromListCeleryTest() {
        callUsdaApi usdaObj = new callUsdaApi(apiKey);
        HashMap<String, Integer> descriptionToFDCid = usdaObj.first10FoundationFoods("celery");

        // User is shown list of 10 foundation foods and prompted to choose one by entering the corresponding fdcId.
        // Selection from dropdown list on a view will be how it is actually implemented.
        System.out.println("List of options displayed to user:");
        System.out.println(descriptionToFDCid);

        int randInt = new Random().nextInt(descriptionToFDCid.size() + 1);
        Object[] keySet = descriptionToFDCid.keySet().toArray();

        int i = 0;
        String randomKey = (String) keySet[i];
        while ((i < descriptionToFDCid.size() + 1) && (i != randInt)) {
            randomKey = (String) keySet[i];
            i += 1;
        }
        Integer fdcId = descriptionToFDCid.get(randomKey);
        System.out.println(randomKey + "=" + descriptionToFDCid.get(randomKey) + " was selected at random.");

        // Create the Food entity from selection.
        JSONObject jsonObj = usdaObj.getFoodByFdcId(fdcId);
        Food newFood = foodFromResultUsda(jsonObj, usdaObj);
        System.out.println("Food entity calories and macronutrients displayed below.");
        System.out.println("Calories:" + newFood.getCalories());
        System.out.println(newFood.getMacroNutrients());
    }

    @Test
    void first10FoundationRandomFDCidFromListCarrotTest() {
        callUsdaApi usdaObj = new callUsdaApi(apiKey);
        HashMap<String, Integer> descriptionToFDCid = usdaObj.first10FoundationFoods("carrot");

        // User is shown list of 10 foundation foods and prompted to choose one by entering the corresponding fdcId.
        // Selection from dropdown list on a view will be how it is actually implemented.
        System.out.println("List of options displayed to user:");
        System.out.println(descriptionToFDCid);

        int randInt = new Random().nextInt(descriptionToFDCid.size() + 1);
        Object[] keySet = descriptionToFDCid.keySet().toArray();

        int i = 0;
        String randomKey = (String) keySet[i];
        while ((i < descriptionToFDCid.size() + 1) && (i != randInt)) {
            randomKey = (String) keySet[i];
            i += 1;
        }
        Integer fdcId = descriptionToFDCid.get(randomKey);
        System.out.println(randomKey + "=" + descriptionToFDCid.get(randomKey) + " was selected at random.");

        // Create the Food entity from selection.
        JSONObject jsonObj = usdaObj.getFoodByFdcId(fdcId);
        Food newFood = foodFromResultUsda(jsonObj, usdaObj);
        System.out.println("Food entity calories and macronutrients displayed below.");
        System.out.println("Calories: " + newFood.getCalories());
        System.out.println(newFood.getMacroNutrients());
    }

    @Test
    void first10FoundationRandomFDCidFromListRiceTest() {
        callUsdaApi usdaObj = new callUsdaApi(apiKey);
        HashMap<String, Integer> descriptionToFDCid = usdaObj.first10FoundationFoods("rice");

        // User is shown list of 10 foundation foods and prompted to choose one by entering the corresponding fdcId.
        // Selection from dropdown list on a view will be how it is actually implemented.
        System.out.println("List of options displayed to user:");
        System.out.println(descriptionToFDCid);

        int randInt = new Random().nextInt(descriptionToFDCid.size() + 1);
        Object[] keySet = descriptionToFDCid.keySet().toArray();

        int i = 0;
        String randomKey = (String) keySet[i];
        while ((i < descriptionToFDCid.size() + 1) && (i != randInt)) {
            randomKey = (String) keySet[i];
            i += 1;
        }
        Integer fdcId = descriptionToFDCid.get(randomKey);
        System.out.println(randomKey + "=" + descriptionToFDCid.get(randomKey) + " was selected at random.");

        // Create the Food entity from selection.
        JSONObject jsonObj = usdaObj.getFoodByFdcId(fdcId);
        Food newFood = foodFromResultUsda(jsonObj, usdaObj);
        System.out.println("Food entity calories and macronutrients displayed below.");
        System.out.println("Calories: " + newFood.getCalories());
        System.out.println(newFood.getMacroNutrients());
    }

    @Test
    /**
     * General test. Giving salient results from queries that are basic foods. For example, beef, rice, celery etc.
     * Not meant to function on complex foods such as granola bar, Kellog's Frosted Flakes, bulgogi etc.
     */
    void first10FoundationRandomFDCidFromListCustomTest() {
        callUsdaApi usdaObj = new callUsdaApi(apiKey);

        HashMap<String, Integer> descriptionToFDCid = usdaObj.first10FoundationFoods("YOUR FAVOURITE SIMPLE FOOD HERE");

        // User is shown list of 10 foundation foods and prompted to choose one by entering the corresponding fdcId.
        // Selection from dropdown list on a view will be how it is actually implemented.
        System.out.println("List of options displayed to user:");
        System.out.println(descriptionToFDCid);

        int randInt = new Random().nextInt(descriptionToFDCid.size() + 1);
        Object[] keySet = descriptionToFDCid.keySet().toArray();

        int i = 0;
        String randomKey = (String) keySet[i];
        while ((i < descriptionToFDCid.size() + 1) && (i != randInt)) {
            randomKey = (String) keySet[i];
            i += 1;
        }
        Integer fdcId = descriptionToFDCid.get(randomKey);
        System.out.println(randomKey + "=" + descriptionToFDCid.get(randomKey) + " was selected at random.");

        // Create the Food entity from selection.
        JSONObject jsonObj = usdaObj.getFoodByFdcId(fdcId);
        Food newFood = foodFromResultUsda(jsonObj, usdaObj);
        System.out.println("Food entity calories and macronutrients displayed below.");
        System.out.println("Calories: " + newFood.getCalories());
        System.out.println(newFood.getMacroNutrients());
    }

    @Test
    /**
     * General test. Giving salient results from queries that are basic foods. For example, beef, rice, celery, pork.
     * Not meant to function on complex foods such as Nature Valley granola bar, Kellog's Frosted Flakes, bulgogi etc.
     */
    void first10FoundationRandomFDCidFromListCustomTest2() {
        callUsdaApi usdaObj = new callUsdaApi(apiKey);

        HashMap<String, Integer> descriptionToFDCid = usdaObj.first10FoundationFoods("grapes");

        // User is shown list of 10 foundation foods and prompted to choose one by entering the corresponding fdcId.
        // Selection from dropdown list on a view will be how it is actually implemented.
        System.out.println("List of options displayed to user:");
        System.out.println(descriptionToFDCid);

        int randInt = new Random().nextInt(descriptionToFDCid.size() + 1);
        Object[] keySet = descriptionToFDCid.keySet().toArray();

        int i = 0;
        String randomKey = (String) keySet[i];
        while ((i < descriptionToFDCid.size() + 1) && (i != randInt)) {
            randomKey = (String) keySet[i];
            i += 1;
        }
        Integer fdcId = descriptionToFDCid.get(randomKey);
        System.out.println(randomKey + "=" + descriptionToFDCid.get(randomKey) + " was selected at random.");

        // Create the Food entity from selection.
        JSONObject jsonObj = usdaObj.getFoodByFdcId(fdcId);
        Food newFood = foodFromResultUsda(jsonObj, usdaObj);
        System.out.println("Food entity calories and macronutrients displayed below.");
        System.out.println("Calories: " + newFood.getCalories());
        System.out.println(newFood.getMacroNutrients());
    }
    // Random change to test authorship 123.
}
