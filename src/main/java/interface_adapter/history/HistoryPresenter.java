package interface_adapter.history;

import interface_adapter.ViewManagerModel;
import interface_adapter.remove_food.RemoveFoodState;
import interface_adapter.remove_food.RemoveFoodViewModel;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;
import use_case.removeFood.RemoveFoodInputData;


public class HistoryPresenter implements HistoryOutputBoundary {
    private final HistoryViewModel historyViewModel;
    private final RemoveFoodViewModel removeFoodViewModel;
    private final ViewManagerModel viewManagerModel;

    public HistoryPresenter(HistoryViewModel viewModel,
                            RemoveFoodViewModel removeFoodViewModel,
                            ViewManagerModel viewManagerModel) {
        this.historyViewModel = viewModel;
        this.removeFoodViewModel = removeFoodViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(HistoryOutputData response) {
        final HistoryState historyState = historyViewModel.getState();
        historyState.setDate(response.getDate());
        historyState.setDayDetails(response.getFoodList());
        historyState.setViewingDate(response.getViewingDate());
        historyState.setHistoryError("");
        historyState.setCompleted(true);

        System.out.println(response.getFoodList());

        this.historyViewModel.setState(historyState);
        this.historyViewModel.firePropertyChanged();

        this.viewManagerModel.setState(historyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

        System.out.println("firePropertyChanged called");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final HistoryState historyState = historyViewModel.getState();
        historyState.setHistoryError(errorMessage);
        historyViewModel.firePropertyChanged();
    }

    @Override
    public void prepareRemoveFoodView(RemoveFoodInputData inputData) {
        System.out.println("transition from history to remove called");
        final RemoveFoodState removeFoodState = removeFoodViewModel.getState();

        removeFoodState.setUsername(inputData.getUsername());
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
}
