package use_case.display_food_options;

import data.Food;

import java.sql.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface InMemoryFoodSelectionDataAccessInterface {

    String getCurrSelection();

    HashMap<String, Integer> getCurrOptionsMap();

    void setCurrSelection(String selection);

    void setCurrOptionsMap(HashMap<String, Integer> foodMap);

    Food getCurrSelectedFood();

    void setCurrFoodEntity(Food food);

    Food getCurrFoodEntity();



}
