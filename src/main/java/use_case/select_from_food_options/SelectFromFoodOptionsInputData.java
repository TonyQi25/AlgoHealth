package use_case.select_from_food_options;

public class SelectFromFoodOptionsInputData {

    private String currSelection;
    private String errorMessage;

    public SelectFromFoodOptionsInputData(String selection, String errorMessage) {

        currSelection = selection;
        this.errorMessage = errorMessage;
    }

    public String getCurrSelection() {
        return currSelection;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
