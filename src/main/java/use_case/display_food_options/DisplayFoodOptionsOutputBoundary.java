package use_case.display_food_options;

public interface DisplayFoodOptionsOutputBoundary {

    void prepareSuccessView(DisplayOptionsOutputData displayOptionsOutputData);

    void prepareFailView(DisplayOptionsOutputData displayOptionsOutputData);
}
