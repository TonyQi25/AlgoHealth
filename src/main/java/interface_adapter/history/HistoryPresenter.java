package interface_adapter.history;

import interface_adapter.ViewManagerModel;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;


public class HistoryPresenter implements HistoryOutputBoundary {
    private final HistoryViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public HistoryPresenter(HistoryViewModel viewModel,
                            ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(HistoryOutputData response) {
        final HistoryState historyState = viewModel.getState();
        historyState.setDate(response.getDate());
        historyState.setDayDetails(response.getFoodList());

        System.out.println(response.getFoodList());

        this.viewModel.setState(historyState);
        this.viewModel.firePropertyChanged();

        this.viewManagerModel.setState(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final HistoryState historyState = viewModel.getState();
        historyState.setHistoryError(errorMessage);
        viewModel.firePropertyChanged();
    }


}
