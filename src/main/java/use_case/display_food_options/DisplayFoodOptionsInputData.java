package use_case.display_food_options;


public class DisplayFoodOptionsInputData {

    private String userFoodSearchInput;

    public DisplayFoodOptionsInputData(String inputFood) {
        userFoodSearchInput = inputFood;
    }

    public String getUserFoodSearchInput() {
        return userFoodSearchInput;
    }

    public void setUserFoodSearchInput(String userFoodSearchInput) {
        this.userFoodSearchInput = userFoodSearchInput;
    }
}
