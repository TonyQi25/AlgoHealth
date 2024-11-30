package interface_adapter.select_from_food_options;

import use_case.select_from_food_options.SelectFromFoodOptionsInputBoundary;
import use_case.select_from_food_options.SelectFromFoodOptionsInputData;

public class SelectFromFoodOptionsController {
    private final SelectFromFoodOptionsInputBoundary selectFromFoodOptionsInteractor;

    public SelectFromFoodOptionsController(SelectFromFoodOptionsInputBoundary selectFromFoodOptionsInteractor) {
        this.selectFromFoodOptionsInteractor = selectFromFoodOptionsInteractor;
    }

    public void execute(String selection) {
        SelectFromFoodOptionsInputData selectFromFoodOptionsInputData = new SelectFromFoodOptionsInputData(selection);
        selectFromFoodOptionsInteractor.execute(selectFromFoodOptionsInputData);
    }
}
