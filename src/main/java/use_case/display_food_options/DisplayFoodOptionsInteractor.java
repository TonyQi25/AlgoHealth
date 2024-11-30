package use_case.display_food_options;

import api.ApiKeyReader;
import api.FoodDataCentralSearchDAO;

import java.util.Collection;
import java.util.HashMap;

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
        /*HashMap<String, Integer> foodMap = foodDataCentralSearchDAO.first10FoundationFoods(displayFoodOptionsInputData
                    .getUserFoodSearchInput());*/

        HashMap<String, Integer> foodMap = foodDataCentralSearchDAO.searchComplexFood(displayFoodOptionsInputData
                    .getUserFoodSearchInput());

       /* else if (!displayFoodOptionsInputData.equals("")) {
            foodMap = foodDataCentralSearchDAO.searchBrandedFood(displayFoodOptionsInputData
                    .getUserFoodSearchInput(), displayFoodOptionsInputData.getUserBrandInput());
        }*/
        if (foodMap.keySet().isEmpty()) {
            foodMap = foodDataCentralSearchDAO.searchComplexFood(displayFoodOptionsInputData
                    .getUserFoodSearchInput());
        }
        Collection<String> foodMapKeys = foodMap.keySet();
        String[] foodList = new String[foodMap.values().size()];
        int i = 0;
        for (String item : foodMapKeys) {
            foodList[i] = item;
            i += 1;
        }
        inMemoryFoodSelectionDAO.setCurrOptionsMap(foodMap);
        DisplayOptionsOutputData displayOptionsOutputData = new DisplayOptionsOutputData(foodList);
        displayFoodOptionsPresenter.prepareSuccessView(displayOptionsOutputData);
    }
}
