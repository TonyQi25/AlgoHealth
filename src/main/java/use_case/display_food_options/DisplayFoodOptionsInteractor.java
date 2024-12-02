package use_case.display_food_options;

import api.ApiKeyReader;
import api.DataAccessException;
import api.FoodDataCentralSearchDAO;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DisplayFoodOptionsInteractor implements DisplayFoodOptionsInputBoundary {

    private DisplayFoodOptionsOutputBoundary displayFoodOptionsPresenter;
    private DisplayFoodOptionsDataAccessInterface foodDataCentralSearchDAO;
    private InMemoryFoodSelectionDataAccessInterface inMemoryFoodSelectionDAO;

    /*private final DisplayFoodOptionsDataAccessInterface foodDataCentralSearchDAO =
            new FoodDataCentralSearchDAO(ApiKeyReader.genMyApiKey("myFDCApiKey.txt"));*/

    public DisplayFoodOptionsInteractor(DisplayFoodOptionsOutputBoundary displayFoodOptionsPresenter,
                                        DisplayFoodOptionsDataAccessInterface foodDataCentralSearchDAO,
                                        InMemoryFoodSelectionDataAccessInterface inMemoryFoodSelectionDAO) {
        this.displayFoodOptionsPresenter = displayFoodOptionsPresenter;
        this.foodDataCentralSearchDAO = foodDataCentralSearchDAO;
        this.inMemoryFoodSelectionDAO = inMemoryFoodSelectionDAO;
    }

    @Override
    public void execute(DisplayFoodOptionsInputData displayFoodOptionsInputData) {

        try {
            HashMap<String, Integer> foodMap = foodDataCentralSearchDAO.searchComplexFood(displayFoodOptionsInputData
                    .getUserFoodSearchInput());

            Collection<String> foodMapKeys = foodMap.keySet();
            String[] foodList = new String[foodMap.values().size()];
            int i = 0;
            for (String item : foodMapKeys) {
                foodList[i] = item;
                i += 1;
            }
            inMemoryFoodSelectionDAO.setCurrOptionsMap(foodMap);
            final DisplayOptionsOutputData displayOptionsOutputData = new DisplayOptionsOutputData(foodList);
            displayFoodOptionsPresenter.prepareSuccessView(displayOptionsOutputData);
        }
        catch (DataAccessException event) {
  /*          final String message = "No food found in database. Please try again.";
            final DisplayOptionsOutputData displayOptionsOutputData = new DisplayOptionsOutputData(message);*/
            final String[] foodList = {};
            final DisplayOptionsOutputData displayOptionsOutputData = new DisplayOptionsOutputData(foodList);
            displayFoodOptionsPresenter.prepareFailView(displayOptionsOutputData);
        }
    }
}
