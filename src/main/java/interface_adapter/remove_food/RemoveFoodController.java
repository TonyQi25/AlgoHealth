package interface_adapter.remove_food;

import use_case.removeFood.RemoveFoodInputBoundary;
import use_case.removeFood.RemoveFoodInputData;

public class RemoveFoodController {

    private final RemoveFoodInputBoundary removeFoodInputBoundary;

    public RemoveFoodController(RemoveFoodInputBoundary removeFoodInteractor) {
        this.removeFoodInputBoundary = removeFoodInteractor;
    }

    public void execute(String foodName, double weight, String username, String date) {
        System.out.println("controller called");
        RemoveFoodInputData input = new RemoveFoodInputData(foodName, weight, username, date);
        removeFoodInputBoundary.execute(input);
    }
}
