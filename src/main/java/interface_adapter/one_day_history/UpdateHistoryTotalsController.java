package interface_adapter.one_day_history;

import use_case.one_day_history.UpdateHistoryTotalsInputBoundary;
import use_case.one_day_history.UpdateHistoryTotalsInputData;

import javax.swing.*;
import java.util.HashMap;

public class UpdateHistoryTotalsController {

    private final UpdateHistoryTotalsInputBoundary inputBoundary;

    public UpdateHistoryTotalsController(UpdateHistoryTotalsInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void execute(String username, String date, String password) {

        UpdateHistoryTotalsInputData input = new UpdateHistoryTotalsInputData(username, date, password);
        inputBoundary.execute(input);

    }

    public void switchToHistory() {
        inputBoundary.switchToHistory();
    }
}
