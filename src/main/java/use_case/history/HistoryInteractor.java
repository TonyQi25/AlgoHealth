package use_case.history;

import org.json.JSONObject;
import use_case.one_day_history.UpdateHistoryTotalsInputData;
import use_case.removeFood.RemoveFoodInputData;

import java.util.ArrayList;
import java.util.List;

public class HistoryInteractor implements HistoryInputBoundary{

    private final HistoryOutputBoundary historyOutputBoundary;
    private final HistoryDataAccessInterface historyDataAccessInterface;

    public HistoryInteractor(HistoryOutputBoundary historyOutputBoundary,
                             HistoryDataAccessInterface dataAccessInterface) {

        this.historyOutputBoundary = historyOutputBoundary;
        this.historyDataAccessInterface = dataAccessInterface;
    }

    public void execute(HistoryInputData input) {

        if (historyDataAccessInterface.DayExists(input.getDate().toString(), input.getUsername())) {
            JSONObject idToInfo = historyDataAccessInterface.loadFoodInfo(input.getUsername(), input.getDate().toString());

            List<String> returning = new ArrayList<>();

            for (String id : idToInfo.keySet()) {
                String info = "";

                info += idToInfo.getJSONObject(id).getString("name") + ": ";
                info += idToInfo.getJSONObject(id).getDouble("weight") + "(g/ml)";

                returning.add(info);
            }

            final HistoryOutputData output = new HistoryOutputData(returning, input.getDate().toString(), input.getDate(), false);
            historyOutputBoundary.prepareSuccessView(output);
        }   else {
            historyOutputBoundary.prepareFailView("Day Not Found");
        }
    }

    public void removeHighlightedFood (RemoveFoodInputData input) {
        historyOutputBoundary.prepareRemoveFoodView(input);
    }

    public void goBack() {
        historyOutputBoundary.prepareMainView();
    }

    public void viewOneDay(UpdateHistoryTotalsInputData input) {
        historyOutputBoundary.viewOneDay(input);
    }
}
