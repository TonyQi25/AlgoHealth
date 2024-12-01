package use_case.daily_value_recs;

public interface DailyValueRecsOutputBoundary {

    /**
     * Prepares updated main view.
     * @param dailyValueRecsOutputData All of the percents we need to update the mainView state.
     */
    void prepareSuccessView(DailyValueRecsOutputData dailyValueRecsOutputData);
}
