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

    public void execute(ListModel<String> list, String username, String date) {
        HashMap<String, Double> idToWeight = new HashMap<>();
        for (int i = 0; i < list.getSize(); i++) {

            // e.g. "Apple(123123): 323.43(g/ml)"
            String[] temp = list.getElementAt(i).split("(:)|(,)|(\\()|(\\))");

            idToWeight.put(temp[1], Double.parseDouble(temp[2]));
        }

        UpdateHistoryTotalsInputData input = new UpdateHistoryTotalsInputData(idToWeight, username, date);
        inputBoundary.execute(input);

    }
}
