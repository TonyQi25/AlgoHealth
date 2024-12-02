package interface_adapter.history;

import interface_adapter.ViewManagerModel;
import interface_adapter.daily_value_recs.MainViewModel;
import interface_adapter.remove_food.RemoveFoodState;
import interface_adapter.remove_food.RemoveFoodViewModel;
import use_case.history.HistoryOutputBoundary;
import use_case.history.HistoryOutputData;
import use_case.removeFood.RemoveFoodInputData;


public class HistoryPresenter implements HistoryOutputBoundary {
    private final HistoryViewModel historyViewModel;
    private final RemoveFoodViewModel removeFoodViewModel;
    private final MainViewModel mainViewModel;
    private final ViewManagerModel viewManagerModel;

    public HistoryPresenter(HistoryViewModel viewModel,
                            RemoveFoodViewModel removeFoodViewModel,
                            ViewManagerModel viewManagerModel,
                            MainViewModel mainViewModel) {
        this.historyViewModel = viewModel;
        this.removeFoodViewModel = removeFoodViewModel;
        this.viewManagerModel = viewManagerModel;
        this.mainViewModel = mainViewModel;
    }

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

        System.out.println("firePropertyChanged called");
        this.viewManagerModel.firePropertyChanged();


    }

    @Override
    public void prepareFailView(String errorMessage) {
        final HistoryState historyState = historyViewModel.getState();
        historyState.setHistoryError(errorMessage);
        historyViewModel.firePropertyChanged();

        System.out.println("history fail view called");

        this.viewManagerModel.setState(historyViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareRemoveFoodView(RemoveFoodInputData inputData) {
        System.out.println("transition from history to remove called");
        final RemoveFoodState removeFoodState = removeFoodViewModel.getState();

        removeFoodState.setUsername(inputData.getUsername());

        System.out.println("In HistoryPresenter: " + inputData.getUsername());
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

    public void prepareMainView() {
        this.viewManagerModel.setState(mainViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
