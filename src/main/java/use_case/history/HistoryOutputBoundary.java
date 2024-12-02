package use_case.history;

import use_case.removeFood.RemoveFoodInputData;
import use_case.removeFood.RemoveFoodInteractor;

public interface HistoryOutputBoundary {

    void prepareSuccessView(HistoryOutputData data);

    void prepareFailView(String errorMessage);

    void prepareRemoveFoodView(RemoveFoodInputData response);

    void prepareMainView();
}
