package interface_adapter.display_food_options;

import interface_adapter.ViewManagerModel;
import interface_adapter.daily_value_recs.MainViewModel;
import use_case.display_food_options.DisplayFoodOptionsOutputBoundary;
import use_case.display_food_options.DisplayOptionsOutputData;
import view.DisplayOptionsView;

public class DisplayFoodOptionsPresenter implements DisplayFoodOptionsOutputBoundary {

    private final DisplayOptionsViewModel displayOptionsViewModel;
    private final ViewManagerModel viewManagerModel;

    public DisplayFoodOptionsPresenter(ViewManagerModel viewManagerModel,
                                       DisplayOptionsViewModel displayOptionsViewModel) {
        this.displayOptionsViewModel = displayOptionsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DisplayOptionsOutputData displayOptionsOutputData) {
        DisplayOptionsViewState currState = displayOptionsViewModel.getState();
    /*    String[] selectionList = currState.getSelectionList();
        int i = 0;
        while (i < displayOptionsOutputData.getFoodList().length) {
            selectionList[i] = displayOptionsOutputData.getFoodList()[i];
            i += 1;
        }*/
        currState.setSelectionList(displayOptionsOutputData.getFoodList());
        //currState.setSelectionMap(displayOptionsOutputData.getFoodMap());
        currState.setErrorMessage("");

        displayOptionsViewModel.setState(currState);
        displayOptionsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(displayOptionsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(DisplayOptionsOutputData displayOptionsOutputData) {
        final DisplayOptionsViewState currState = displayOptionsViewModel.getState();
        currState.setSelectionList(displayOptionsOutputData.getFoodList());
        currState.setErrorMessage("Food not found. Press select and return to return to"
                + " previous screen.");
        displayOptionsViewModel.setState(currState);
        displayOptionsViewModel.firePropertyChanged();

        this.viewManagerModel.setState(displayOptionsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
