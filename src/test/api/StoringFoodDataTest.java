//package api;
//package helpers;
//import data.AccountInfo;
//import data.Food;
//import data_access.GradeAccountDAO;
//import org.json.JSONObject;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import static junit.framework.TestCase.assertEquals;
//
//public class StoringFoodDataTest {
//    private final GradeHelper helper = new GradeHelper();
//    private final GradeAccountDAO gradeAccountDAO = new GradeAccountDAO();
//    @Test
//    void testSingup() {
//        String[] diet = new String[1];
//        diet[0] = "none";
//        List<String> dietaryRestrictions = new ArrayList<String>();
//        dietaryRestrictions.add("vegan");
//        gradeAccountDAO.createAccount("testSaveAccount", "12345");
//        AccountInfo newAccount = new AccountInfo(LocalDate.parse("2001-01-10"), (float)168.5, (float) 50.3,
//                diet, "lose weight", "testSaveAccount", "12345", dietaryRestrictions);
//        gradeAccountDAO.save(newAccount);
//        AccountInfo savedAccount = gradeAccountDAO.get("testSaveAccount");
//        assertEquals("testSaveAccount", savedAccount.getUsername());
//        assertEquals(savedAccount.getGoal(), newAccount.getGoal());
//    }
//    @Test
//    void testChangingInfo() {
//        String[] diet = new String[1];
//        diet[0] = "none";
//        List<String> dietaryRestrictions = new ArrayList<String>();
//        dietaryRestrictions.add("vegan");
//        AccountInfo newAccountInfo = new AccountInfo(LocalDate.parse("2001-01-10"), (float)168.5, (float) 50.3,
//                diet, "gain weight", "testSaveAccount", "12345", dietaryRestrictions);
//        gradeAccountDAO.save(newAccountInfo);
//        AccountInfo savedAccount = gradeAccountDAO.get("testSaveAccount");
//        assertEquals("testSaveAccount", savedAccount.getUsername());
//        assertEquals(savedAccount.getGoal(), newAccountInfo.getGoal());
//    }
//    @Test
//    void testSavingFoodToAPI() {
//        HashMap<String, Object> calories = new HashMap<>();
//        calories.put("amount per 100", 0.06);
//        HashMap<String, Object> proteins = new HashMap<>();
//        proteins.put("amount per 100", 0.07);
//        HashMap<String, Object> carbs = new HashMap<>();
//        carbs.put("amount per 100", 0.08);
//        HashMap<String, Object> fat = new HashMap<>();
//        fat.put("amount per 100", 0.09);
//        Food pineapple = new Food("pineapple", 100, calories);
//        HashMap<String, HashMap<String, Object>> macros = new HashMap<>();
//        macros.put("Protein", proteins);
//        macros.put("Carbohydrate", carbs);
//        macros.put("Fat", fat);
//        pineapple.setMacroNutrients(macros);
//        pineapple.setTotalCarb();
//        pineapple.setTotalCalories();
//        pineapple.setTotalFat();
//        pineapple.setTotalProtein();
//        gradeAccountDAO.saveFood(LocalDate.now().toString(), "testSaveAccount","12345", pineapple, 12345);
//    }
//    @Test
//    void testSavingNewFoodToAPI() {
//        HashMap<String, Object> calories = new HashMap<>();
//        calories.put("amount per 100", 0.10);
//        HashMap<String, Object> proteins = new HashMap<>();
//        proteins.put("amount per 100", 0.93);
//        HashMap<String, Object> carbs = new HashMap<>();
//        carbs.put("amount per 100", 0.38);
//        HashMap<String, Object> fat = new HashMap<>();
//        fat.put("amount per 100", 0.19);
//        Food banana = new Food("banana", 120, calories);
//        HashMap<String, HashMap<String, Object>> macros = new HashMap<>();
//        macros.put("Protein", proteins);
//        macros.put("Carbohydrate", carbs);
//        macros.put("Fat", fat);
//        banana.setMacroNutrients(macros);
//        banana.setTotalCarb();
//        banana.setTotalCalories();
//        banana.setTotalFat();
//        banana.setTotalProtein();
//        gradeAccountDAO.saveFood(LocalDate.now().toString(), "testSaveAccount","12345", banana, 6789);
//    }
//    @Test
//    void testSavingNewWeightFoodToAPI() {
//        HashMap<String, Object> calories = new HashMap<>();
//        calories.put("amount per 100", 0.10);
//        HashMap<String, Object> proteins = new HashMap<>();
//        proteins.put("amount per 100", 0.93);
//        HashMap<String, Object> carbs = new HashMap<>();
//        carbs.put("amount per 100", 0.38);
//        HashMap<String, Object> fat = new HashMap<>();
//        fat.put("amount per 100", 0.19);
//        Food banana = new Food("banana", 320, calories);
//        HashMap<String, HashMap<String, Object>> macros = new HashMap<>();
//        macros.put("Protein", proteins);
//        macros.put("Carbohydrate", carbs);
//        macros.put("Fat", fat);
//        banana.setMacroNutrients(macros);
//        banana.setTotalCarb();
//        banana.setTotalCalories();
//        banana.setTotalFat();
//        banana.setTotalProtein();
//        gradeAccountDAO.saveFood(LocalDate.now().toString(), "testSaveAccount","12345", banana, 6789);
//    }
//    @Test
//    void testSavingNewDayFoodToAPI() {
//        HashMap<String, Object> calories = new HashMap<>();
//        calories.put("amount per 100", 0.89);
//        HashMap<String, Object> proteins = new HashMap<>();
//        proteins.put("amount per 100", 0.38);
//        HashMap<String, Object> carbs = new HashMap<>();
//        carbs.put("amount per 100", 0.28);
//        HashMap<String, Object> fat = new HashMap<>();
//        fat.put("amount per 100", 0.10);
//        Food potato = new Food("potato", 150, calories);
//        HashMap<String, HashMap<String, Object>> macros = new HashMap<>();
//        macros.put("Protein", proteins);
//        macros.put("Carbohydrate", carbs);
//        macros.put("Fat", fat);
//        potato.setMacroNutrients(macros);
//        potato.setTotalCarb();
//        potato.setTotalCalories();
//        potato.setTotalFat();
//        potato.setTotalProtein();
//        gradeAccountDAO.saveFood("2024-10-09", "testSaveAccount","12345", potato, 91234);
//    }
//    @Test
//    void loadingFoodTest() {
//        JSONObject dayFoodLog = gradeAccountDAO.loadFoodInfo("testSaveAccount", "2024-10-09");
//        JSONObject foodInfo = dayFoodLog.getJSONObject("91234");
//        assertEquals("potato", foodInfo.get("name"));
//
//        //JSONObject todayLog = GradeHelper.getJSONFoodLog("testSaveAccount", LocalDate.now().toString());
//        JSONObject OctLog = (JSONObject) GradeHelper.getJSONFoodLog("testSaveAccount");
//        //JSONObject OctLogFoodInfo = (JSONObject) OctLog.get("32380");
//        //assertEquals("apple", OctLog.get("name"));
//        //assertEquals(800, OctLog.get("weight"));
//        Integer foodExistBeforeDelete = gradeAccountDAO.FoodExists("2024-10-09", "testSaveAccount",
//                "potato");
//        assertEquals(91234, (int)foodExistBeforeDelete);
//    }
//
//    @Test
//    void DeletingFoodTest(){
//        boolean foodRemove = gradeAccountDAO.removeFood("2024-10-09", "testSaveAccount",
//                "12345", "91234");
//        assertEquals(true, foodRemove);
//        Integer foodExist = gradeAccountDAO.FoodExists("2024-10-09", "testSaveAccount",
//                "potato");
//        assertEquals(null, foodExist);
//    }
//}
