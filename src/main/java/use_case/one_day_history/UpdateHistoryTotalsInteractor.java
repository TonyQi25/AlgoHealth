package use_case.one_day_history;

import data.Food;
//import use_case.daily_value_recs.DailyValueCalculationStrategy;
import data_access.GradeAccountDAO;
import use_case.select_from_food_options.SelectSearchDataAccessInterface;
import org.json.JSONObject;

import java.util.HashMap;

import static api.PopulateUtility.createFood;

/**
 * use case interactor for view one day usecase
 */
public class UpdateHistoryTotalsInteractor implements UpdateHistoryTotalsInputBoundary {


    // constants for DVs based on https://www.canada.ca/en/health-canada/services/food-nutrition/
    // healthy-eating/dietary-reference-intakes/tables/reference-values-macronutrients.html
    // and assumed body weight of 80kg and age 19-30 and assumed recommended calories 2000 Kcal.
    // fat intake is just a randomly selected number right now as there is no indication of its value in the link.

    final static double DVcals = 2000;
    final static double DVprot = 80 * 0.66;
    final static double DVcarbs = 100;
    final static double DVfat = 200 ;
    // 203 is the specifier for "protein", 204 is the specifier for "total lipid (fat)",
    // 205 is the specifier for "carbohydrate, by difference", and 208 is the specifier
    // for "energy". 957, 958 for other "energies" (atwater general, atwater specific)"
    private final Integer[] MACRO_SPECIFIER_1 = {203, 204, 205, 208, 268, 957, 958};

    private UpdateHistoryTotalsOutputBoundary updateHistoryTotalsPresenter;
    private SelectSearchDataAccessInterface foodDataCentralSearchDAO;
    private GradeAccountDAO gradeAccountDAO;
    //private DailyValueCalculationStrategy dailyValueCalculationStrategy;

    public UpdateHistoryTotalsInteractor(UpdateHistoryTotalsOutputBoundary updateHistoryTotalsPresenter,
                                         SelectSearchDataAccessInterface foodDataCentralSearchDAO,
                                         GradeAccountDAO gradeAccountDAO) {
        this.updateHistoryTotalsPresenter = updateHistoryTotalsPresenter;
        this.foodDataCentralSearchDAO = foodDataCentralSearchDAO;
        this.gradeAccountDAO = gradeAccountDAO;
        //this.dailyValueCalculationStrategy = dailyValueCalculationStrategy;
    }

    /**
     * executes the use case
     * @param updateHistoryTotalsInputData input
     */
    @Override
    public void execute(UpdateHistoryTotalsInputData updateHistoryTotalsInputData) {
        // assuming UpdateHistoryTotalsInputData will provide me with a list of fdcIds mapped to weight in some form.
        // Probably will be a HashMap<String, Double> so I'm using that here. Will update as necessary.
        // Implementation in mind for UpdateHistoryTotalsOutputData is that
        // it has an instance variable which can store a list of Foods generated in execute.

        // layout:  {ids: {"name": name, "weight": double ... }

        System.out.println("OneDay interator called");
        JSONObject info = gradeAccountDAO.loadFoodInfo(updateHistoryTotalsInputData.getUsername(),
                updateHistoryTotalsInputData.getDate());

        HashMap<Integer, Double> idToWeight = new HashMap<>();

        for (String key : info.keySet()) {
            idToWeight.put(Integer.parseInt(key), info.getJSONObject(key).getDouble("weight"));
        }

        System.out.println("HERE IS THE HASHMAP REQUESTED"+idToWeight);

        Food[] outputFoodList = new Food[idToWeight.keySet().size()];
        int i = 0;
        for (Integer fdcId: idToWeight.keySet()) {
            Food freshFood = createFood(foodDataCentralSearchDAO.getFoodByFdcId(fdcId, MACRO_SPECIFIER_1));
            freshFood.setWeight(idToWeight.get(fdcId));
            outputFoodList[i] = freshFood;
            i += 1;
            System.out.println("#1 "+ freshFood.getDescription());
            System.out.println("#1.5 "+ freshFood.getWeight());
        }


        // Total the macronutrients of all Foods in outputFoodList.
        // Currently calculating DV values in same way as in daily value recs use case. Another option for
        // both is put DV calculation directly in Food entity.
        double grandTotalCalories = 0;
        double grandTotalCarbs = 0;
        double grandTotalProtein = 0;
        double grandTotalFat = 0;

        for (Food food: outputFoodList) {
            grandTotalCalories += food.getTotalCalories();
            grandTotalCarbs += food.getTotalCarb();
            grandTotalProtein += food.getTotalCarb();
            grandTotalFat += food.getTotalFat();
            System.out.println("#2 "+ grandTotalCalories);
            System.out.println("#2 "+ grandTotalCarbs);
            System.out.println("#2 "+ grandTotalProtein);
            System.out.println("#2 "+ grandTotalFat);
        }
        double grandTotalDVCalories = (grandTotalCalories / DVcals) * 100;
        double grandTotalDVProtein = (grandTotalProtein / DVprot) * 100;
        double grandTotalDVCarbs = (grandTotalCarbs / DVcarbs) * 100;
        double grandTotalDVFat = (grandTotalFat / DVfat) * 100;

        UpdateHistoryTotalsOutputData outputData = new UpdateHistoryTotalsOutputData(grandTotalCalories,
                grandTotalCarbs,
                grandTotalDVProtein,
                grandTotalFat,
                grandTotalDVCalories,
                grandTotalDVCarbs,
                grandTotalDVProtein,
                grandTotalDVFat);

        System.out.println("HERE IS CALROIES CALCUALTED:" + grandTotalCalories);

        updateHistoryTotalsPresenter.prepareSuccessView(outputData);

    }

    /**
     * goes back to history view
     */
    public void switchToHistory() {
        updateHistoryTotalsPresenter.switchToHistory();
    }
}

