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
        gradeAccountDAO.saveFood("testSaveAccount", "12345", pineapple, 12345);
    }
    @Test
    void loadingFoodTest() {
        JSONObject food = GradeHelper.getJSONFoodLog ("testSaveAccount");
        assertEquals("12345", JSONObject.getNames(food)[0]);
        assertEquals(100, food.get("12345"));
    }
}
