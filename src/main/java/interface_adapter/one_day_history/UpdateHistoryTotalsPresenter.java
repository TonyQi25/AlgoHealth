package interface_adapter.one_day_history;

import interface_adapter.ViewManagerModel;
import interface_adapter.history.HistoryViewModel;
import use_case.one_day_history.UpdateHistoryTotalsOutputBoundary;
import use_case.one_day_history.UpdateHistoryTotalsOutputData;

public class UpdateHistoryTotalsPresenter implements UpdateHistoryTotalsOutputBoundary {

    final private HistoryViewModel historyViewModel;
    private UpdateHistoryTotalsViewModel viewModel;
    private ViewManagerModel viewManagerModel;

    public UpdateHistoryTotalsPresenter(HistoryViewModel historyViewModel, UpdateHistoryTotalsViewModel viewModel,
                                        ViewManagerModel viewManagerModel) {
        this.historyViewModel = historyViewModel;
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(UpdateHistoryTotalsOutputData output) {
        final UpdateHistoryTotalsState state = viewModel.getState();

        state.setCarbs(output.getCarbs());
        state.setFat(output.getFat());
        state.setProtein(output.getProtein());
        state.setCarbs(output.getCarbs());
        state.setCalories(output.getCalories());
        state.setRecCalories(output.getRecCalories());
        state.setRecFat(output.getRecFat());
        state.setRecProtein(output.getRecProtein());
        state.setRecCarbs(output.getRecCarbs());
        state.setCompleted(true);

        this.viewModel.setState(state);
        this.viewModel.firePropertyChanged();

        this.viewManagerModel.setState(viewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    public void switchToHistory() {
        this.viewManagerModel.setState(this.historyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
