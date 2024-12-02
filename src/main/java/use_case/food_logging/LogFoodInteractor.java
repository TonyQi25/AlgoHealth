package use_case.food_logging;
import api.FoodDataCentralPopulateDAO;
import api.FoodDataCentralSearchDAO;
import data.Food;
import data_access.GradeAccountDAO;
import org.json.JSONObject;
import use_case.display_food_options.InMemoryFoodSelectionDataAccessInterface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class LogFoodInteractor implements LogFoodInputBoundary {
    private final LogFoodDataAccessInterface foodLoggingDAO;
    private final LogFoodOutputBoundary logFoodPresenter;
    private InMemoryFoodSelectionDataAccessInterface inMemoryFoodSelectionDAO;


    public LogFoodInteractor(InMemoryFoodSelectionDataAccessInterface inMemoryFoodSelectionDAO, LogFoodDataAccessInterface foodLoggingDAO ,LogFoodOutputBoundary logFoodPresenter) {
        this.logFoodPresenter = logFoodPresenter;
        this.inMemoryFoodSelectionDAO = inMemoryFoodSelectionDAO;
        this.foodLoggingDAO = foodLoggingDAO;
    }

   //@Override
    public void execute(LogFoodInputData logFoodInputData) {
        /*final FoodDataCentralSearchDAO usdaObject = new FoodDataCentralSearchDAO(FoodDataCentralSearchDAO
                .genMyApiKey("myFDCApiKey.txt"));*/
        //JSONObject result = usdaObject.getFoodByFdcId(logFoodInputData.getFdcId());
        // final Food food = FoodDataCentralPopulateDAO.foodFromFirstResultUsda(
                // logFoodInputData.getFoodName(), usdaObject);
        /*final Food food = FoodDataCentralPopulateDAO.foodFromResultUsda(
                result, usdaObject);*/
        Food food = inMemoryFoodSelectionDAO.getCurrFoodEntity();
        Integer fdcID = inMemoryFoodSelectionDAO.getCurrOptionsMap().get(food.getDescription());
        food.setWeight(logFoodInputData.getFoodWeight());
        food.setTotalCarb();
        food.setTotalProtein();
        food.setTotalFat();
        food.setTotalCalories();
        final ArrayList<Object> calWUnit = new ArrayList<>();
        calWUnit.add(String.valueOf(food.getTotalCalories()));
        calWUnit.add("Kcal");
        final ArrayList<Object> proteinWUnit = new ArrayList<>();
        proteinWUnit.add(String.valueOf(food.getTotalProtein()));
        proteinWUnit.add("g");
        final ArrayList<Object> carbsWUnit = new ArrayList<>();
        carbsWUnit.add(String.valueOf(food.getTotalCarb()));
        carbsWUnit.add("g");
        final ArrayList<Object> fatWUnit = new ArrayList<>();
        fatWUnit.add(String.valueOf(food.getTotalFat()));
        proteinWUnit.add("g");
        foodLoggingDAO.saveFood(LocalDate.now().toString(), logFoodInputData.getUsername(),
                logFoodInputData.getPassword(), food, fdcID);
        final LogFoodOutputData logFoodOutputData = new LogFoodOutputData(food.getDescription(),
                (float) food.getWeight(),
                food.getStandardUnit(), calWUnit, proteinWUnit, carbsWUnit, fatWUnit);
        logFoodPresenter.prepareLogFoodView(logFoodOutputData);
    }
}
