package use_case.removeFood;

import use_case.history.HistoryInputData;

public interface RemoveFoodInputBoundary {

    void execute(RemoveFoodInputData input);

    void returnToHistory(HistoryInputData input);
}
