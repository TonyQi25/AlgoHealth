package data_access;

import java.util.HashMap;

public class UserFoodSearchInMemoryDAO {

    private HashMap<String, Integer> foodOptionsMap;
    private int fdcIDofSelection;
    private String foodSearchInput;


    public HashMap<String, Integer> getFoodOptionsMap() {
        return foodOptionsMap;
    }

    public void setFoodOptionsMap(HashMap<String, Integer> foodOptionsMap) {
        this.foodOptionsMap = foodOptionsMap;
    }

    public int getFdcIDofSelection() {
        return fdcIDofSelection;
    }

    public void setFdcIDofSelection(int fdcIDofSelection) {
        this.fdcIDofSelection = fdcIDofSelection;
    }

    public String getFoodSearchInput() {
        return foodSearchInput;
    }

    public void setFoodSearchInput(String foodSearchInput) {
        this.foodSearchInput = foodSearchInput;
    }
}
