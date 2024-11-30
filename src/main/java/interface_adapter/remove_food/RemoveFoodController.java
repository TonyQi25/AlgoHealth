package interface_adapter.remove_food;

import use_case.history.HistoryInputData;
import use_case.removeFood.RemoveFoodInputBoundary;
import use_case.removeFood.RemoveFoodInputData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public void returnToHistory(String date, String username) {

        HistoryInputData input = new HistoryInputData(LocalDate.parse(date), username);
        removeFoodInputBoundary.returnToHistory(input);
    }
}
