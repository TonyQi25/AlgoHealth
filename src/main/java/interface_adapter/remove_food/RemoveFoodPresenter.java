package interface_adapter.remove_food;

import interface_adapter.ViewManagerModel;
import interface_adapter.history.HistoryViewModel;
import use_case.removeFood.RemoveFoodOutputBoundary;
import use_case.removeFood.RemoveFoodOutputData;

public class RemoveFoodPresenter implements RemoveFoodOutputBoundary {

    private RemoveFoodViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    //private HistoryViewModel historyViewModel;

    public RemoveFoodPresenter(RemoveFoodViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        //this.historyViewModel = historyViewModel;
    }

    @Override
    public void prepareSuccessView(RemoveFoodOutputData data) {

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}
