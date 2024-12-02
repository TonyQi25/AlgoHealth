package interface_adapter.history;

import interface_adapter.ViewManagerModel;
import interface_adapter.daily_value_recs.MainViewModel;
import interface_adapter.one_day_history.UpdateHistoryTotalsState;
import interface_adapter.one_day_history.UpdateHistoryTotalsViewModel;
import interface_adapter.remove_food.RemoveFoodState;
import interface_adapter.remove_food.RemoveFoodViewModel;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;
import use_case.one_day_history.UpdateHistoryTotalsInputData;
import use_case.removeFood.RemoveFoodInputData;

/**
 * presenter for history use case
 */
public class HistoryPresenter implements HistoryOutputBoundary {
    private final HistoryViewModel historyViewModel;
    private final RemoveFoodViewModel removeFoodViewModel;
    private final MainViewModel mainViewModel;
    private final UpdateHistoryTotalsViewModel updateHistoryTotalsViewModel;
    private final ViewManagerModel viewManagerModel;

    public HistoryPresenter(HistoryViewModel viewModel,
                            RemoveFoodViewModel removeFoodViewModel,
                            ViewManagerModel viewManagerModel,
                            MainViewModel mainViewModel,
                            UpdateHistoryTotalsViewModel updateHistoryTotalsViewModel) {
        this.historyViewModel = viewModel;
        this.removeFoodViewModel = removeFoodViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mainViewModel = mainViewModel;
        this.updateHistoryTotalsViewModel = updateHistoryTotalsViewModel;
    }

    /**
     * prepares the success view
     * @param response the output data from the interactor
     */
    @Override
    public void prepareSuccessView(HistoryOutputData response) {
        final HistoryState historyState = historyViewModel.getState();
        historyState.setDate(response.getDate());
        historyState.setDayDetails(response.getFoodList());
        historyState.setViewingDate(response.getViewingDate());
        historyState.setHistoryError("");
        historyState.setCompleted(true);

        this.historyViewModel.setState(historyState);
        this.historyViewModel.firePropertyChanged();

        this.viewManagerModel.setState(historyViewModel.getViewName());

        this.viewManagerModel.firePropertyChanged();


    }

    /**
     * prepare the failing view
     * @param errorMessage the error message given by interactor
     */
    @Override
    public void prepareFailView(String errorMessage) {
        final HistoryState historyState = historyViewModel.getState();
        historyState.setHistoryError(errorMessage);
        historyViewModel.firePropertyChanged();


        this.viewManagerModel.setState(historyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    /**
     * process of switching to remove food use case
     * @param inputData input for the use case
     */
    @Override
    public void prepareRemoveFoodView(RemoveFoodInputData inputData) {
        final RemoveFoodState removeFoodState = removeFoodViewModel.getState();

        removeFoodState.setUsername(inputData.getUsername());
        removeFoodState.setPassword(inputData.getPassword());
        removeFoodState.setViewingDate(inputData.getViewingDate());
        removeFoodState.setFoodName(inputData.getFoodName());
        removeFoodState.setWeight(inputData.getWeight());
        removeFoodState.setRemoveFoodError("");
        removeFoodState.setOutputMessage("");
        removeFoodState.setCompleted(false);


        this.removeFoodViewModel.setState(removeFoodState);
        this.removeFoodViewModel.firePropertyChanged();

        this.viewManagerModel.setState(removeFoodViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * process of switching to main view use case
     */
    public void prepareMainView() {
        this.viewManagerModel.setState(mainViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * process of switching to the one-day history use case
     * @param input input for the one-day history
     */
    public void viewOneDay(UpdateHistoryTotalsInputData input) {
        final UpdateHistoryTotalsState updateHistoryTotalsState = updateHistoryTotalsViewModel.getState();

        updateHistoryTotalsState.setDate(input.getDate());
        updateHistoryTotalsState.setCompleted(false);
        updateHistoryTotalsState.setUsername(input.getUsername());
        updateHistoryTotalsState.setPassword(input.getPassword());

        this.updateHistoryTotalsViewModel.setState(updateHistoryTotalsState);
        this.updateHistoryTotalsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(updateHistoryTotalsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
