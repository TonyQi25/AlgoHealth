package interface_adapter.display_food_options;

import use_case.daily_value_recs.DailyValueRecsInputBoundary;
import use_case.daily_value_recs.DailyValueRecsIntakeData;
import use_case.display_food_options.DisplayFoodOptionsInputBoundary;
import use_case.display_food_options.DisplayFoodOptionsInputData;

public class DisplayFoodOptionsController {
    private final DisplayFoodOptionsInputBoundary displayFoodOptionsInteractor;

    public DisplayFoodOptionsController(DisplayFoodOptionsInputBoundary displayFoodOptionsInteractor) {
        this.displayFoodOptionsInteractor = displayFoodOptionsInteractor;
    }

    public void execute(String foodInput) {
        DisplayFoodOptionsInputData displayFoodOptionsInputData = new DisplayFoodOptionsInputData(foodInput);
        displayFoodOptionsInteractor.execute(displayFoodOptionsInputData);
    }
}
