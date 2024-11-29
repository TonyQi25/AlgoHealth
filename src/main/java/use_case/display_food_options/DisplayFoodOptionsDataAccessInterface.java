package use_case.display_food_options;

import java.util.HashMap;

public interface DisplayFoodOptionsDataAccessInterface {

    HashMap<String, Integer> first10FoundationFoods(String food);

    HashMap<String, Integer> searchBrandedFood(String food, String brand);

    HashMap<String, Integer> searchComplexFood(String food);
}
