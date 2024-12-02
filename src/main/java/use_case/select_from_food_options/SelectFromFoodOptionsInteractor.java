package use_case.select_from_food_options;

import api.FoodDataCentralPopulateDAO;
import api.FoodDataCentralSearchDAO;
import api.PopulateUtility;
import data.Food;
import org.json.JSONObject;
import use_case.display_food_options.InMemoryFoodSelectionDataAccessInterface;

public class SelectFromFoodOptionsInteractor implements SelectFromFoodOptionsInputBoundary {

    // 203 is the specifier for "protein", 204 is the specifier for "total lipid (fat)",
    // 205 is the specifier for "carbohydrate, by difference", and 208 is the specifier
    // for "energy". 957, 958 for other "energies" (atwater general, atwater specific)"
    private final Integer[] MACRO_SPECIFIER_1 = {203, 204, 205, 208, 268, 957, 958};

    // Vitamin A, IU=318, Vitamin C, total ascorbic acid=401, Vitamin D=324, Vitamin E (alpha- 5900 tocopherol)=323,
    // Vitamin K (phylloquinone)=430.
    private final Integer[] MACROS_AND_MICROS = {203, 204, 205, 208, 268, 957, 318, 401, 324, 323, 430};

    //we need to update the inmemoryDAO, then get the fdcId from it.
    //we need to create the food by calling popluateAPI.
    //we need to store the created food in the inmemoryDAO
    //then we need to update mainview state so the selection
    //shows up in the search field.
    //then flip back to mainView (call presenter method) and that's it.

    private InMemoryFoodSelectionDataAccessInterface inMemoryFoodSelectionDAO;
    private SelectSearchDataAccessInterface foodDataCentralSearchDAO;
    private SelectFromFoodOptionsOutputBoundary selectFromFoodOptionsPresenter;

    public SelectFromFoodOptionsInteractor(InMemoryFoodSelectionDataAccessInterface inMemoryFoodSelectionDAO,
                                           SelectSearchDataAccessInterface foodDataCentralSearchDAO,
                                           SelectFromFoodOptionsOutputBoundary selectFromFoodOptionsPresenter) {
        this.inMemoryFoodSelectionDAO = inMemoryFoodSelectionDAO;
        this.foodDataCentralSearchDAO = foodDataCentralSearchDAO;
        this.selectFromFoodOptionsPresenter = selectFromFoodOptionsPresenter;
    }

    @Override
    public void execute(SelectFromFoodOptionsInputData selectFromFoodOptionsInputData) {
        // update inmemoryDAO and get fdcId. Update again with created Food.
        if (selectFromFoodOptionsInputData.getErrorMessage().equals("")) {
            inMemoryFoodSelectionDAO.setCurrSelection(selectFromFoodOptionsInputData.getCurrSelection());
            Integer fdcId = inMemoryFoodSelectionDAO.getCurrOptionsMap().get(selectFromFoodOptionsInputData
                    .getCurrSelection());
            JSONObject result = foodDataCentralSearchDAO.getFoodByFdcId(fdcId, MACRO_SPECIFIER_1);
            inMemoryFoodSelectionDAO.setCurrFoodEntity(PopulateUtility.createFood(result));
            // print calls for debugging/sanity check.
            System.out.println(inMemoryFoodSelectionDAO.getCurrFoodEntity().getDescription());
            System.out.println(inMemoryFoodSelectionDAO.getCurrFoodEntity().getMacroNutrients());
            System.out.println(inMemoryFoodSelectionDAO.getCurrFoodEntity().getCalories());
            // Now we flip back to mainView. Make output data for updating mainView as well.
        }
        SelectFromFoodOptionsOutputData selectFromFoodOptionsOutputData = new SelectFromFoodOptionsOutputData(
                selectFromFoodOptionsInputData.getCurrSelection());
        selectFromFoodOptionsPresenter.prepareSuccessView(selectFromFoodOptionsOutputData);
    }
}
