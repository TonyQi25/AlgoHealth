package interface_adapter.remove_food;

import interface_adapter.ViewManagerModel;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;
import use_case.history.HistoryInputData;
import use_case.removeFood.RemoveFoodOutputBoundary;
import use_case.removeFood.RemoveFoodOutputData;

/**
 * presenter for remove food use case
 */
public class RemoveFoodPresenter implements RemoveFoodOutputBoundary {

    private RemoveFoodViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private HistoryViewModel historyViewModel;

    public RemoveFoodPresenter(RemoveFoodViewModel viewModel, ViewManagerModel viewManagerModel, HistoryViewModel historyViewModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.historyViewModel = historyViewModel;
    }

    /**
     * prepare success view for remove food use case
     * @param data input data for use case
     */
    @Override
    public void prepareSuccessView(RemoveFoodOutputData data) {
        RemoveFoodState state = viewModel.getState();
        state.setRemoveFoodError("");
        state.setOutputMessage(data.getResultMessage());
        state.setUsername(data.getUsername());
        state.setViewingDate(data.getViewingDate());
        state.setCompleted(true);

        System.out.println("preparing remove success view");

        this.viewModel.setState(state);
        this.viewModel.firePropertyChanged();

        this.viewManagerModel.setState(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * prepare fail view for use case
     * @param errorMessage error message from interactor
     */
    @Override
    public void prepareFailView(String errorMessage) {
        RemoveFoodState state = viewModel.getState();
        state.setRemoveFoodError(errorMessage);
        viewModel.firePropertyChanged();
    }

    /**
     * prepare history view for returning to history
     * @param data history input data
     */
    @Override
    public void prepareHistoryView(HistoryInputData data) {
        System.out.println("transition from remove to history called");
        final HistoryState historyState = historyViewModel.getState();

        historyState.setHistoryError("");
        historyState.setUsername(data.getUsername());
        historyState.setDayDetails(null);
        historyState.setDate(data.getDate().toString());
        historyState.setViewingDate(data.getDate());
        historyState.setCompleted(false);

        this.historyViewModel.setState(historyState);
        this.historyViewModel.firePropertyChanged();

        this.viewManagerModel.setState(historyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
