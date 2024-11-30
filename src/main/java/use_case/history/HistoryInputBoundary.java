package use_case.history;

import use_case.removeFood.RemoveFoodInputData;

public interface HistoryInputBoundary {

    void execute(HistoryInputData input);

    void removeHighlightedFood(RemoveFoodInputData input);

}
