package use_case.food_logging;
import api.FoodDataCentralPopulateDAO;
import api.FoodDataCentralSearchDAO;
import data.Food;

import java.util.ArrayList;
import java.util.HashMap;

public class LogFoodInteractor implements LogFoodInputBoundary {
    private final LogFoodDataAccessInterface logFoodDataAccessObject;
    private final LogFoodOutputBoundary logFoodPresenter;
    private final FoodDataCentralPopulateDAO foodConstruct;


    public LogFoodInteractor(LogFoodDataAccessInterface logFoodDataAccessObject, LogFoodOutputBoundary logFoodPresenter,
                             FoodDataCentralPopulateDAO foodConstruct) {
        this.logFoodDataAccessObject = logFoodDataAccessObject;
        this.logFoodPresenter = logFoodPresenter;
        this.foodConstruct = foodConstruct;
    }

   //@Override
    public void execute(LogFoodInputData logFoodInputData) {
        final FoodDataCentralSearchDAO usdaObject = new FoodDataCentralSearchDAO("DEMO_KEY");
        final Food food = FoodDataCentralPopulateDAO.foodFromFirstResultUsda(
                logFoodInputData.getFoodName(), usdaObject);
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

        final LogFoodOutputData logFoodOutputData = new LogFoodOutputData(food.getDescription(), food.getWeight(),
                food.getStandardUnit(), calWUnit, proteinWUnit, carbsWUnit, fatWUnit);
        logFoodPresenter.prepareLogFoodView(logFoodOutputData);
    }
}
