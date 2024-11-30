package interface_adapter.one_day_history;

import interface_adapter.ViewManagerModel;
import use_case.one_day_history.UpdateHistoryTotalsOutputBoundary;
import use_case.one_day_history.UpdateHistoryTotalsOutputData;

public class UpdateHistoryTotalsPresenter implements UpdateHistoryTotalsOutputBoundary {

    //final private HistoryViewModel historyViewModel;
    private UpdateHistoryTotalsViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    public UpdateHistoryTotalsPresenter(UpdateHistoryTotalsViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(UpdateHistoryTotalsOutputData output) {
        final UpdateHistoryTotalsState state = viewModel.getState();

        state.set
    }
}
