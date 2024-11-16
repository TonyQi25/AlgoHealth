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
        final Food food = FoodDataCentralPopulateDAO.foodFromFirstResultUsda(logFoodInputData.getFood(), usdaObject);
        final HashMap<String, Object> calories = food.getCalories();
        final HashMap<String, HashMap<String, Object>> macroNutrients = food.getMacroNutrients();
        final HashMap<String, Object> protein = macroNutrients.get("Protein");
        final HashMap<String, Object> carbs = macroNutrients.get("Carbohydrate");
        final HashMap<String, Object> fat = macroNutrients.get("Fat");
        final float foodAmount = food.getWeight();
        final float calAmount = (Integer) calories.get("amount per 100" + food.getStandardUnit())/100*foodAmount;
        final float proteinAmount = (Integer) protein.get("amount per 100" + food.getStandardUnit())/100*foodAmount;
        final float carbAmount = (Integer) carbs.get("amount per 100" + food.getStandardUnit())/100*foodAmount;
        final float fatAmount = (Integer) fat.get("amount per 100" + food.getStandardUnit())/100*foodAmount;
        final ArrayList<Object> calWUnit = new ArrayList<>();
        calWUnit.add(String.valueOf(calAmount));
        calWUnit.add("Kcal");
        final ArrayList<Object> proteinWUnit = new ArrayList<>();
        proteinWUnit.add(String.valueOf(proteinAmount));
        proteinWUnit.add("g");
        final ArrayList<Object> carbsWUnit = new ArrayList<>();
        carbsWUnit.add(String.valueOf(carbAmount));
        carbsWUnit.add("g");
        final ArrayList<Object> fatWUnit = new ArrayList<>();
        fatWUnit.add(String.valueOf(fatAmount));
        proteinWUnit.add("g");

        final LogFoodOutputData logFoodOutputData = new LogFoodOutputData(food.getDescription(), calWUnit, proteinWUnit,
                carbsWUnit, fatWUnit);
        logFoodPresenter.prepareLogFoodView(logFoodOutputData);
    }
}
