package use_case.removeFood;

import use_case.history.HistoryInputData;

public interface RemoveFoodOutputBoundary {

    void prepareSuccessView(RemoveFoodOutputData data);

    void prepareFailView(String errorMessage);

    void prepareHistoryView(HistoryInputData data);
}
