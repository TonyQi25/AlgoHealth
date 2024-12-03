package display_options_use_case;

import api.DataAccessException;
import use_case.display_food_options.DisplayFoodOptionsDataAccessInterface;

import java.util.HashMap;

/**
 * Used for testing the display food options use case success view.
 */
public class inMemoryFdaSearchFailDataAccessObject implements DisplayFoodOptionsDataAccessInterface {
    @Override
    public HashMap<String, Integer> first10FoundationFoods(String food) {
        return null;
    }

    @Override
    public HashMap<String, Integer> searchBrandedFood(String food, String brand) {
        return null;
    }

    /**
     * Returns a HashMap that would correspond in form to expected return from searchComplexFood
     * in current program's implementation.
     * @param food
     * @return
     * @throws DataAccessException
     */
    @Override
    public HashMap<String, Integer> searchComplexFood(String food) throws DataAccessException {
        throw new DataAccessException("event");
    }
}
