package use_case.display_food_options;

import java.util.HashMap;

public interface DisplayFoodOptionsDataAccessInterface {

    HashMap<String, Integer> first10FoundationFoods(String food);
}
