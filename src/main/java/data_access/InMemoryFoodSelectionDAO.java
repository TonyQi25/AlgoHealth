package data_access;

import data.Food;
import use_case.display_food_options.InMemoryFoodSelectionDataAccessInterface;

import java.util.HashMap;


public class InMemoryFoodSelectionDAO implements InMemoryFoodSelectionDataAccessInterface {

    private String currSelection;
    private HashMap<String, Integer> currOptionsMap;
    private String[] currOptionsList;
    private Food currFoodEntity;

    @Override
    public String getCurrSelection() {
        return currSelection;
    }

    @Override
    public HashMap<String, Integer> getCurrOptionsMap() {
        return currOptionsMap;
    }

    @Override
    public void setCurrSelection(String selection) {
        this.currSelection = selection;
    }

    @Override
    public void setCurrOptionsMap(HashMap<String, Integer> optionsMap) {
        this.currOptionsMap = optionsMap;
    }

    @Override
    public Food getCurrSelectedFood() {
        return null;
    }

    @Override
    public void setCurrFoodEntity(Food food) {
        currFoodEntity = food;
    }

    public Food getCurrFoodEntity() {
        return currFoodEntity;
    }
}
