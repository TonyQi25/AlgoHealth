package use_case.display_food_options;

import java.util.HashMap;

public class DisplayOptionsOutputData {

    // private HashMap<String, Integer> foodMap;
    private String[] foodList;

    public DisplayOptionsOutputData(String[] foodList) {
        this.foodList = foodList;
    }

   /* public HashMap<String, Integer> getFoodMap() {
        return foodMap;
    }*/


    public String[] getFoodList() {
        return foodList;
    }

    public void setFoodList(String[] foodList) {
        this.foodList = foodList;
    }

   /* public void setFoodMap(HashMap<String, Integer> foodMap) {
        this.foodMap = foodMap;
    }*/
}
