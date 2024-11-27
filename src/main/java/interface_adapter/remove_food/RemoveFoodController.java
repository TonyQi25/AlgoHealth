package interface_adapter.remove_food;

import use_case.removeFood.RemoveFoodInputBoundary;

public class RemoveFoodController {

    private RemoveFoodInputBoundary removeFoodInputBoundary;

    public RemoveFoodController(RemoveFoodInputBoundary removeFoodInteractor) {
        this.removeFoodInputBoundary = removeFoodInteractor;
    }

    public void execute() {

    }
}
