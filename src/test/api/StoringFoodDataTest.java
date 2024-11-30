package api;

import data.AccountInfo;
import data.Food;
import data_access.GradeAccountDAO;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class StoringFoodDataTest {
    private final GradeHelper helper = new GradeHelper();
    private final GradeAccountDAO gradeAccountDAO = new GradeAccountDAO();
    @Test
    void testSingup() {
        String[] diet = new String[1];
        diet[0] = "none";
        List<String> dietaryRestrictions = new ArrayList<String>();
        dietaryRestrictions.add("vegan");
        gradeAccountDAO.createAccount("testSaveAccount", "12345");
        AccountInfo newAccount = new AccountInfo(LocalDate.parse("2001-01-10"), (float)168.5, (float) 50.3,
                diet, "lose weight", "testSaveAccount", "12345", dietaryRestrictions);
        gradeAccountDAO.save(newAccount);
        AccountInfo savedAccount = gradeAccountDAO.get("testSaveAccount");
        assertEquals("testSaveAccount", savedAccount.getUsername());
        assertEquals(savedAccount.getGoal(), newAccount.getGoal());
    }
    @Test
    void testChangingInfo() {
        String[] diet = new String[1];
        diet[0] = "none";
        List<String> dietaryRestrictions = new ArrayList<String>();
        dietaryRestrictions.add("vegan");
        AccountInfo newAccountInfo = new AccountInfo(LocalDate.parse("2001-01-10"), (float)168.5, (float) 50.3,
                diet, "gain weight", "testSaveAccount", "12345", dietaryRestrictions);
        gradeAccountDAO.save(newAccountInfo);
        AccountInfo savedAccount = gradeAccountDAO.get("testSaveAccount");
        assertEquals("testSaveAccount", savedAccount.getUsername());
        assertEquals(savedAccount.getGoal(), newAccountInfo.getGoal());
    }
    @Test
    void testSavingFoodToAPI() {
        HashMap<String, Object> calories = new HashMap<>();
        calories.put("amount per 100", 0.06);
        HashMap<String, Object> proteins = new HashMap<>();
        proteins.put("amount per 100", 0.07);
        HashMap<String, Object> carbs = new HashMap<>();
        carbs.put("amount per 100", 0.08);
        HashMap<String, Object> fat = new HashMap<>();
        fat.put("amount per 100", 0.09);
        Food pineapple = new Food("pineapple", 100, calories);
        HashMap<String, HashMap<String, Object>> macros = new HashMap<>();
        macros.put("Protein", proteins);
        macros.put("Carbohydrate", carbs);
        macros.put("Fat", fat);
        pineapple.setMacroNutrients(macros);
        pineapple.setTotalCarb();
        pineapple.setTotalCalories();
        pineapple.setTotalFat();
        pineapple.setTotalProtein();
        gradeAccountDAO.saveFood(LocalDate.now(), "testSaveAccount","12345", pineapple, 12345);
    }
    @Test
    void testSavingNewFoodToAPI() {
        HashMap<String, Object> calories = new HashMap<>();
        calories.put("amount per 100", 0.10);
        HashMap<String, Object> proteins = new HashMap<>();
        proteins.put("amount per 100", 0.93);
        HashMap<String, Object> carbs = new HashMap<>();
        carbs.put("amount per 100", 0.38);
        HashMap<String, Object> fat = new HashMap<>();
        fat.put("amount per 100", 0.19);
        Food banana = new Food("banana", 120, calories);
        HashMap<String, HashMap<String, Object>> macros = new HashMap<>();
        macros.put("Protein", proteins);
        macros.put("Carbohydrate", carbs);
        macros.put("Fat", fat);
        banana.setMacroNutrients(macros);
        banana.setTotalCarb();
        banana.setTotalCalories();
        banana.setTotalFat();
        banana.setTotalProtein();
        gradeAccountDAO.saveFood(LocalDate.now(), "testSaveAccount","12345", banana, 6789);
    }
    @Test
    void testSavingNewWeightFoodToAPI() {
        HashMap<String, Object> calories = new HashMap<>();
        calories.put("amount per 100", 0.10);
        HashMap<String, Object> proteins = new HashMap<>();
        proteins.put("amount per 100", 0.93);
        HashMap<String, Object> carbs = new HashMap<>();
        carbs.put("amount per 100", 0.38);
        HashMap<String, Object> fat = new HashMap<>();
        fat.put("amount per 100", 0.19);
        Food banana = new Food("banana", 320, calories);
        HashMap<String, HashMap<String, Object>> macros = new HashMap<>();
        macros.put("Protein", proteins);
        macros.put("Carbohydrate", carbs);
        macros.put("Fat", fat);
        banana.setMacroNutrients(macros);
        banana.setTotalCarb();
        banana.setTotalCalories();
        banana.setTotalFat();
        banana.setTotalProtein();
        gradeAccountDAO.saveFood(LocalDate.now(), "testSaveAccount","12345", banana, 6789);
    }
    @Test
    void testSavingNewDayFoodToAPI() {
        HashMap<String, Object> calories = new HashMap<>();
        calories.put("amount per 100", 0.10);
        HashMap<String, Object> proteins = new HashMap<>();
        proteins.put("amount per 100", 0.93);
        HashMap<String, Object> carbs = new HashMap<>();
        carbs.put("amount per 100", 0.38);
        HashMap<String, Object> fat = new HashMap<>();
        fat.put("amount per 100", 0.19);
        Food banana = new Food("banana", 120, calories);
        HashMap<String, HashMap<String, Object>> macros = new HashMap<>();
        macros.put("Protein", proteins);
        macros.put("Carbohydrate", carbs);
        macros.put("Fat", fat);
        banana.setMacroNutrients(macros);
        banana.setTotalCarb();
        banana.setTotalCalories();
        banana.setTotalFat();
        banana.setTotalProtein();
        gradeAccountDAO.saveFood(LocalDate.now(), "testSaveAccount","12345", banana, 6789);

        HashMap<String, Object> applecalories = new HashMap<>();
        applecalories.put("amount per 100", 0.10);
        HashMap<String, Object> appleproteins = new HashMap<>();
        appleproteins.put("amount per 100", 0.93);
        HashMap<String, Object> applecarbs = new HashMap<>();
        applecarbs.put("amount per 100", 0.38);
        HashMap<String, Object> applefat = new HashMap<>();
        applefat.put("amount per 100", 0.19);
        Food apple = new Food("apple", 800, applecalories);
        HashMap<String, HashMap<String, Object>> applemacros = new HashMap<>();
        applemacros.put("Protein", appleproteins);
        applemacros.put("Carbohydrate", applecarbs);
        applemacros.put("Fat", applefat);
        apple.setMacroNutrients(applemacros);
        apple.setTotalCarb();
        apple.setTotalCalories();
        apple.setTotalFat();
        apple.setTotalProtein();
        gradeAccountDAO.saveFood(LocalDate.parse("2024-10-30"), "testSaveAccount","12345", apple, 32380);
        JSONObject newFoodLog = GradeHelper.getUserInfo("testSaveAccount");
    }
    @Test
    void loadingFoodTest() {
        String date = LocalDate.now().toString();
        JSONObject userInfo = GradeHelper.getUserInfo("testSaveAccount");

        //JSONObject todayLog = GradeHelper.getJSONFoodLog("testSaveAccount", LocalDate.now().toString());
        JSONObject OctLog = (JSONObject) GradeHelper.getJSONFoodLog("testSaveAccount");
        //JSONObject OctLogFoodInfo = (JSONObject) OctLog.get("32380");
        //assertEquals("apple", OctLog.get("name"));
        //assertEquals(800, OctLog.get("weight"));
    }
}
