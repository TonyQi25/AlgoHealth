package use_case.select_from_food_options;

public class SelectFromFoodOptionsInputData {

    private String currSelection;

    public SelectFromFoodOptionsInputData(String selection) {
        currSelection = selection;
    }

    public String getCurrSelection() {
        return currSelection;
    }
}
