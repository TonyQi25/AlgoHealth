package use_case.one_day_history;

public interface UpdateHistoryTotalsOutputBoundary {

    void prepareSuccessView(UpdateHistoryTotalsOutputData output);

    void switchToHistory();
}
