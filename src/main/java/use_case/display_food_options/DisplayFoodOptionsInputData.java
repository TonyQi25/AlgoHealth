package use_case.display_food_options;


public class DisplayFoodOptionsInputData {

    private String userFoodSearchInput;
    // private String userBrandInput;

    public DisplayFoodOptionsInputData(String inputFood) {

        userFoodSearchInput = inputFood;
        //this.userBrandInput = userBrandInput;
    }

    public String getUserFoodSearchInput() {
        return userFoodSearchInput;
    }

    public void setUserFoodSearchInput(String userFoodSearchInput) {
        this.userFoodSearchInput = userFoodSearchInput;
    }

/*    public String getUserBrandInput() {
        return userBrandInput;
    }

    public void setUserBrandInput(String userBrandInput) {
        this.userBrandInput = userBrandInput;
    }*/
}
