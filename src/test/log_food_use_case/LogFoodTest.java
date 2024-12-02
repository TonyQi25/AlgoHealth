package log_food_use_case;

import data.Food;
import data_access.GradeAccountDAO;
import data_access.InMemoryFoodSelectionDAO;
import org.junit.jupiter.api.Test;
import use_case.display_food_options.InMemoryFoodSelectionDataAccessInterface;
import use_case.food_logging.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class LogFoodTest {

    @Test
    void LogFoodTest(){
        InMemoryFoodSelectionDAO inMemoryFoodSelectionDAO = new InMemoryFoodSelectionDAO();
        HashMap<String, HashMap<String, Object>> micros = new HashMap<>();
        HashMap<String, Object> calories = new HashMap<>();
        calories.put("amount per 100", 0.1);
        HashMap<String, Object> proteins = new HashMap<>();
        proteins.put("amount per 100", 0.2);
        HashMap<String, Object> carbs = new HashMap<>();
        carbs.put("amount per 100", 0.4);
        HashMap<String, Object> fat = new HashMap<>();
        fat.put("amount per 100", 0.9);
        Food chicken = new Food("chicken", 200, calories);
        HashMap<String, HashMap<String, Object>> macros = new HashMap<>();
        macros.put("Protein", proteins);
        macros.put("Carbohydrate", carbs);
        macros.put("Fat", fat);
        chicken.setNutritionFacts(micros, macros);
        inMemoryFoodSelectionDAO.setCurrFoodEntity(chicken);
        inMemoryFoodSelectionDAO.setCurrSelection("chicken");
        HashMap<String, Integer> optionsMap = new HashMap<>();
        optionsMap.put("chicken", 567892);
        inMemoryFoodSelectionDAO.setCurrOptionsMap(optionsMap);

        GradeAccountDAO foodLoggingDAO = new GradeAccountDAO();
        Integer fdcId = 567892;
        double foodWeight = 200;
        String weightUnit = "g";
        String username = "testSaveAccount";
        String password = "12345";
        LogFoodInputData logFoodInputData = new LogFoodInputData(fdcId, foodWeight, weightUnit, username, password);
        LogFoodOutputBoundary logFoodPresenter = new LogFoodOutputBoundary() {
            @Override
            public void prepareLogFoodView(LogFoodOutputData outputData) {
                ArrayList<Object> carbs = outputData.getCarbs();
                ArrayList<Object> protein = outputData.getProtein();
                ArrayList<Object> calories = outputData.getCalories();
                ArrayList<Object> fat = outputData.getFat();
                assertEquals("chicken", outputData.getFoodName());
                assertEquals(200, outputData.getFoodWeight(), 0.0001);
                assertEquals("g", outputData.getWeightUnit());
                assertEquals(0.2, (double)outputData.getCalories().get(0), 0.0001);
                assertEquals(0.8, (double)outputData.getCarbs().get(0), 0.0001);
                assertEquals(0.4, (double)outputData.getProtein().get(0), 0.0001);
                assertEquals(1.8, (double)outputData.getFat().get(0), 0.0001);
                assertEquals("kCal", outputData.getCalories().get(1));
                assertEquals("g", outputData.getCarbs().get(1));
                assertEquals("g", outputData.getProtein().get(1));
                assertEquals("g", outputData.getFat().get(1));
            }
        };
        LogFoodInputBoundary interactor = new LogFoodInteractor(inMemoryFoodSelectionDAO,
                foodLoggingDAO, logFoodPresenter);
        interactor.execute(logFoodInputData);
        }
    }
