package interface_adapter.remove_food;

import use_case.history.HistoryInputData;
import use_case.removeFood.RemoveFoodInputBoundary;
import use_case.removeFood.RemoveFoodInputData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * controller for remove food use case
 */
public class RemoveFoodController {

    private final RemoveFoodInputBoundary removeFoodInputBoundary;

    public RemoveFoodController(RemoveFoodInputBoundary removeFoodInteractor) {
        this.removeFoodInputBoundary = removeFoodInteractor;
    }

    /**
     * calling the interactor for remove food use case
     * @param foodName name of food
     * @param weight weight of food
     * @param username usename
     * @param date date to remove from
     * @param password password
     */
    public void execute(String foodName, double weight, String username, String date, String password) {
        RemoveFoodInputData input = new RemoveFoodInputData(foodName, weight, username, date, password);
        removeFoodInputBoundary.execute(input);
    }

    /**
     * to return back to the history view
     * @param date date to view
     * @param username username
     * @param password password
     */
    public void returnToHistory(String date, String username, String password) {

        HistoryInputData input = new HistoryInputData(LocalDate.parse(date), username, password);
        removeFoodInputBoundary.returnToHistory(input);
    }
}
